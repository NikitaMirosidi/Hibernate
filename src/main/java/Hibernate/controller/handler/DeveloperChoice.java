package Hibernate.controller.handler;

import Hibernate.controller.BaseControllerImpl;
import Hibernate.model.Developer;

import java.util.Scanner;

public class DeveloperChoice extends UserChoiceHandler {

    public DeveloperChoice(UserChoiceHandler handler) {
        super(handler);
    }

    @Override
    protected void apply(Scanner scanner) {

        String simpleName = Developer.class.getSimpleName();

        if(!controllers.containsKey(simpleName)) {
            controllers.put(simpleName,new BaseControllerImpl<>(Developer.class, scanner));
        }
        controllers.get(simpleName).general();
    }

    @Override
    protected boolean isApplicable(String choice) {
        return "3".equals(choice);
    }
}