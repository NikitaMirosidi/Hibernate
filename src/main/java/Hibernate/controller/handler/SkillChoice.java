package Hibernate.controller.handler;

import Hibernate.controller.BaseControllerImpl;
import Hibernate.model.Skill;

import java.util.Scanner;

public class SkillChoice extends UserChoiceHandler {

    public SkillChoice(UserChoiceHandler handler) {
        super(handler);
    }

    @Override
    protected void apply(Scanner scanner) {

        String simpleName = Skill.class.getSimpleName();

        if(!controllers.containsKey(simpleName)) {
            controllers.put(simpleName,new BaseControllerImpl<>(Skill.class, scanner));
        }
        controllers.get(simpleName).general();
    }

    @Override
    protected boolean isApplicable(String choice) {
        return "5".equals(choice);
    }
}