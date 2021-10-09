package Hibernate.service;

import Hibernate.model.Developer;
import Hibernate.repository.CustomRepository;

import java.util.List;

public class CustomService {

    private final CustomRepository REPOSITORY;

    public CustomService() {

        this.REPOSITORY = new CustomRepository();
    }

    public int getProjectsDevsSalary(String projectName) {

        return REPOSITORY.getProjectsDevsSalary(projectName);
    }

    public List<Developer> getDevsByProject(String projectName) {

        return REPOSITORY.getDevsByProject(projectName);
    }

    public List<Developer> getDevsByLanguage(String languageName) {

        return REPOSITORY.getDevsByLanguage(languageName);
    }

    public List<Developer> getDevsByLevel(String levelName) {

        return REPOSITORY.getDevsByLevel(levelName);
    }

    public List<String> getProjects() {

        return REPOSITORY.getProjectsFormatted();
    }
}