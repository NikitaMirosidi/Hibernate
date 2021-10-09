package Hibernate.controller.handler;

import Hibernate.controller.BaseControllerImpl;
import Hibernate.model.Company;

import java.util.Scanner;

public class CompanyChoice extends UserChoiceHandler {

    public CompanyChoice(UserChoiceHandler handler) {
        super(handler);
    }

    @Override
    protected void apply(Scanner scanner) {

        String simpleName = Company.class.getSimpleName();

        if(!controllers.containsKey(simpleName)) {
            controllers.put(simpleName,new BaseControllerImpl<>(Company.class, scanner));
        }
        controllers.get(simpleName).general();
    }

    @Override
    protected boolean isApplicable(String choice) {
        return "1".equals(choice);
    }
}