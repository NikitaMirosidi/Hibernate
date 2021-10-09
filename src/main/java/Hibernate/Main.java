package Hibernate;

import Hibernate.controller.MainController;
import Hibernate.model.*;
import Hibernate.repository.CustomRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        new MainController().userInterface();
        //SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        /*CustomRepository customRepository = new CustomRepository();
        int graphic_design_tools = customRepository.getProjectsDevsSalary("Graphic Design Tools");
        System.out.println(graphic_design_tools);

        List<Developer> graphic_design_tools1 = customRepository.getDevsByProject("Graphic Design Tools");
        System.out.println(graphic_design_tools1);

        List<Developer> java = customRepository.getDevsByLanguage("Java");
        System.out.println(java);

        List<Developer> middle = customRepository.getDevsByLevel("Middle");
        System.out.println(middle);

        List<String> projects = customRepository.getProjectsFormatted();
        System.out.println(projects);*/
    }
}