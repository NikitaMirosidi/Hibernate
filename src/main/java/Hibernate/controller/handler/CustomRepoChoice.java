package Hibernate.controller.handler;

import Hibernate.controller.CustomController;

import java.util.Scanner;

public class CustomRepoChoice extends UserChoiceHandler {

    public CustomRepoChoice(UserChoiceHandler handler) {
        super(handler);
    }

    @Override
    protected void apply(Scanner scanner) {

        if(customController == null) {
            customController = new CustomController(scanner);
        }
        customController.general();
    }

    @Override
    protected boolean isApplicable(String choice) {
        return "6".equals(choice);
    }
}