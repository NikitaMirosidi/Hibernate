package Hibernate.controller.handler;

import Hibernate.controller.BaseController;
import Hibernate.controller.CustomController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class UserChoiceHandler {

    protected Map<String,BaseController> controllers = new HashMap<>();
    protected CustomController customController;
    private final UserChoiceHandler HANDLER;

    public UserChoiceHandler(UserChoiceHandler handler) {

        this.HANDLER = handler;
    }

    protected abstract void apply(Scanner scanner);

    protected abstract boolean isApplicable(String choice);

    public final boolean handle(String choice, Scanner scanner) {

        if (isApplicable(choice)) {
            apply(scanner);
            return true;
        }
        else if(choice.equals("0")) {
            System.out.println("До скорых встреч\n");
            return false;
        }
        else {
            HANDLER.handle(choice, scanner);
            return true;
        }
    }

    public static UserChoiceHandler of() {
        return new CompanyChoice(new CustomerChoice(new DeveloperChoice(
                new ProjectChoice(new SkillChoice(new CustomRepoChoice(new UnsupportedChoice()))))));
    }
}