package Hibernate;

import Hibernate.config.HibernateSessionFactory;
import Hibernate.model.Companies;
import lombok.SneakyThrows;
import org.hibernate.Session;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        Companies company = Companies.builder()
                .id(1)
                .name("Company")
                .registrationCountry("Ukraine")
                .build();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(company);

        session.getTransaction().commit();
        session.close();
    }
}