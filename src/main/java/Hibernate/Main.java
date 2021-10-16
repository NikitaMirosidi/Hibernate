package Hibernate;

import Hibernate.controller.MainController;
import Hibernate.model.Developer;
import Hibernate.model.Project;
import Hibernate.repository.BaseRepository;
import Hibernate.repository.RepositoryCreator;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        new MainController().userInterface();

        /*BaseRepository<Developer> of = RepositoryCreator.of(Developer.class);
        Optional<Developer> byId = of.getById(5);
        Developer developer = byId.get();
        System.out.println(developer);*/

    }
}