package Hibernate.controller.handler;

import Hibernate.controller.BaseControllerImpl;
import Hibernate.model.Project;

import java.util.Scanner;

public class ProjectChoice extends UserChoiceHandler {

    public ProjectChoice(UserChoiceHandler handler) {
        super(handler);
    }

    @Override
    protected void apply(Scanner scanner) {

        String simpleName = Project.class.getSimpleName();

        if(!controllers.containsKey(simpleName)) {
            controllers.put(simpleName,new BaseControllerImpl<>(Project.class, scanner));
        }
        controllers.get(simpleName).general();
    }

    @Override
    protected boolean isApplicable(String choice) {
        return "4".equals(choice);
    }
}