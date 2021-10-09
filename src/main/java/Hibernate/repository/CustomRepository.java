package Hibernate.repository;

import Hibernate.config.HibernateSessionFactory;
import Hibernate.model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

public class CustomRepository implements Closeable {

    private final SessionFactory SESSION_FACTORY;

    public CustomRepository() {
        this.SESSION_FACTORY = HibernateSessionFactory.getSessionFactory();
    }

    public int getProjectsDevsSalary(String projectName) { //зарплата (сумма) всех разработчиков отдельного проекта

        Session session = beginSession();

        String hql = "SELECT SUM(d.salary) FROM Developer d " +
                "JOIN d.projects p " +
                "WHERE p.name = :projectName";

        int sum = session.createQuery(hql, Integer.class)
                .setParameter("projectName", projectName)
                .getSingleResult();

        closeSession(session);

        return sum;
    }

    public List<Developer> getDevsByProject(String projectName) {//список разрабов определенного проекта

        Session session = beginSession();

        String hql = "SELECT d FROM Developer d " +
                "JOIN d.projects p " +
                "WHERE p.name = :projectName";

        List<Developer> developers = session.createQuery(hql, Developer.class)
                .setParameter("projectName", projectName)
                .getResultList();

        closeSession(session);

        return developers;
    }

    public List<Developer> getDevsByLanguage(String languageName) {//список всех разрабов определенного языка

        Session session = beginSession();

        String hql = "SELECT d FROM Developer d " +
                "JOIN d.skills p " +
                "WHERE p.name = :languageName";

        List<Developer> developers = session.createQuery(hql, Developer.class)
                .setParameter("languageName", languageName)
                .getResultList();

        closeSession(session);

        return developers;
    }

    public List<Developer> getDevsByLevel(String levelName) {//список всех разрабов определенного уровня

        Session session = beginSession();

        String hql = "SELECT d FROM Developer d " +
                "JOIN d.skills p " +
                "WHERE p.level = :levelName";

        List<Developer> developers = session.createQuery(hql, Developer.class)
                .setParameter("levelName", levelName)
                .getResultList();

        closeSession(session);

        return developers;
    }

    public List<String> getProjectsFormatted() {//список проектов в формате: "дата создания - название проекта - количество разработчиков на этом проекте"

        Session session = beginSession();
        List<String> result = new ArrayList<>();

        String hql = "SELECT p.creationDate, p.name, COUNT(*) FROM Project p, Developer d " +
                "JOIN d.projects dp " +
                "WHERE dp.name = p.name " +
                "GROUP BY p.name";

        List projects = session.createQuery(hql).getResultList();

        for(Object project : projects) {

            result.add(((Object[])project)[0] + " - "
                    + ((Object[])project)[1] + " - "
                    + ((Object[])project)[2]);
        }

        closeSession(session);

        return result;
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