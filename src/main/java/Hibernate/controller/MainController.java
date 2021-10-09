package Hibernate.controller;

import Hibernate.controller.handler.UserChoiceHandler;

import java.util.Scanner;

public class MainController {

    public void userInterface() {

        boolean a = true;
        Scanner scanner = new Scanner(System.in);
        UserChoiceHandler handler = UserChoiceHandler.of();

        while (a) {

            System.out.println("\nГлавное меню:\n" +
                    "1 - стандартная работа с Companies\n" +
                    "2 - стандартная работа с Customers\n" +
                    "3 - стандартная работа с Developers\n" +
                    "4 - стандартная работа с Projects\n" +
                    "5 - стандартная работа с Skills\n" +
                    "6 - нестандартные запросы\n" +
                    "0 - завершение приложения\n");

            String choice = scanner.nextLine();
            a = handler.handle(choice, scanner);
        }

        scanner.close();
    }
}