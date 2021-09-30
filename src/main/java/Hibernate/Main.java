package Hibernate;

import Hibernate.config.HibernateSessionFactory;
import Hibernate.model.Company;
import lombok.SneakyThrows;
import org.hibernate.Session;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        Company company1 = Company.builder()
                .id(1)
                .name("Company")
                .registrationCountry("Ukraine")
                .build();

        Company company2 = Company.builder()
                .id(3)
                .name("Company2")
                .registrationCountry("Ukraine")
                .build();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(company1);
        session.save(company2);



        session.getTransaction().commit();
        session.close();

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");




    }
}