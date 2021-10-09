package Hibernate.controller;

import Hibernate.service.CustomService;

import java.util.Scanner;

public class CustomController {

    private final CustomService SERVICE;
    private final Scanner SCANNER;

    public CustomController(Scanner scanner) {
        this.SERVICE = new CustomService();
        this.SCANNER = scanner;
    }

    public void general() {

        boolean a = true;
        String userAnswer;

        while (a) {

            System.out.println("\nМеню нестандартных запросов:\n" +
                    "1 - получить зарплату (сумма) всех разработчиков отдельного проекта\n" +
                    "2 - получить список разработчиков определенного проекта\n" +
                    "3 - получить список всех разрабов владеющих определенным языком разработки\n" +
                    "4 - получить список всех разрабов определенного уровня\n" +
                    "5 - получить список проектов в формате: дата создания - название проекта - количество разработчиков на этом проекте\n" +
                    "0 - возврат в предыдущее меню\n");

            String i = SCANNER.nextLine();

            switch (i) {
                case "1":

                    System.out.print("Укажите название проекта: ");
                    userAnswer = SCANNER.nextLine();
                    System.out.println(SERVICE.getProjectsDevsSalary(userAnswer) + "$");
                    break;
                case "2":

                    System.out.print("Укажите название проекта: ");
                    userAnswer = SCANNER.nextLine();
                    System.out.println(SERVICE.getDevsByProject(userAnswer));
                    break;
                case "3":

                    System.out.print("Укажите название языка: ");
                    userAnswer = SCANNER.nextLine();
                    System.out.println(SERVICE.getDevsByLanguage(userAnswer));
                    break;
                case "4":

                    System.out.print("Укажите название уровня: ");
                    userAnswer = SCANNER.nextLine();
                    System.out.println(SERVICE.getDevsByLevel(userAnswer));
                    break;
                case "5":
                    System.out.println(SERVICE.getProjects());
                    break;
                case "0":
                    a = false;
                    break;
                default:
                    System.out.println("Поддерживаемая функция не выбрана\n" +
                            "Попробуйте еще раз\n");
                    break;
            }
        }
    }
}