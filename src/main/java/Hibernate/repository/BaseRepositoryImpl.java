package Hibernate.repository;

import Hibernate.config.HibernateSessionFactory;
import Hibernate.model.BaseModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.Closeable;
import java.util.*;

public class BaseRepositoryImpl<T extends BaseModel> implements BaseRepository<T>, Closeable {

    private final Class<T> MODEL_CLASS;
    private final SessionFactory SESSION_FACTORY;

    public BaseRepositoryImpl(Class<T> modelClass) {
        this.MODEL_CLASS = modelClass;
        this.SESSION_FACTORY = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public int save(T model) {

        Session session = beginSession();
        int id = (Integer)session.save(model);
        closeSession(session);
        return id;
    }

    @Override
    public void saveAll(Iterable<T> models) {

        for (T model : models) {
            save(model);
        }
    }

    @Override
    public Optional<T> getById(int id) {

        Session session = beginSession();
        Optional<T> model = Optional.ofNullable(session.get(MODEL_CLASS, id));
        closeSession(session);

        return model;
    }

    @Override
    public List<T> getAll() {

        Session session = beginSession();

        CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(MODEL_CLASS);
        List<T> resultList = session.createQuery(criteriaQuery.select(criteriaQuery.from(MODEL_CLASS))).getResultList();

        closeSession(session);

        return resultList;
    }

    @Override
    public void update(T model) {

        Session session = beginSession();
        session.update(model);
        closeSession(session);
    }

    @Override
    public void delete(T model) {

        Session session = beginSession();
        session.remove(model);
        closeSession(session);
    }

    @Override
    public boolean isDuplicate(String fieldName, String value) {

        Session session = beginSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(MODEL_CLASS);
        Root<T> root = criteriaQuery.from(MODEL_CLASS);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(fieldName), value));

        Query<T> query = session.createQuery(criteriaQuery);
        List<T> resultList = query.getResultList();

        closeSession(session);

        return !resultList.isEmpty();
    }

    private Session beginSession() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        return session;
    }

    private void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public final void close() {
        HibernateSessionFactory.close();
    }
}