package Hibernate.controller.handler;

import Hibernate.controller.BaseControllerImpl;
import Hibernate.model.Customer;

import java.util.Scanner;

public class CustomerChoice extends UserChoiceHandler {

    public CustomerChoice(UserChoiceHandler handler) {
        super(handler);
    }

    @Override
    protected void apply(Scanner scanner) {

        String simpleName = Customer.class.getSimpleName();

        if(!controllers.containsKey(simpleName)) {
            controllers.put(simpleName,new BaseControllerImpl<>(Customer.class, scanner));
        }
        controllers.get(simpleName).general();
    }

    @Override
    protected boolean isApplicable(String choice) {
        return "2".equals(choice);
    }
}