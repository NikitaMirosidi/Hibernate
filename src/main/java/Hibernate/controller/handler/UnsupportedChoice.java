package Hibernate.controller.handler;

import java.util.Scanner;

public class UnsupportedChoice extends UserChoiceHandler {

    public UnsupportedChoice() {
        super (null);
    }

    @Override
    protected void apply(Scanner scanner) {
        System.out.println("Поддерживаемая функция не выбрана!\n" +
                "Попробуйте еще раз.\n");
    }

    @Override
    protected boolean isApplicable(String choice) {
        return true;
    }
}