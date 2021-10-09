package Hibernate.controller;

import Hibernate.model.*;
import Hibernate.service.BaseService;
import Hibernate.service.BaseServiceImpl;
import Hibernate.util.ModelCreator;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class BaseControllerImpl<T extends BaseModel> implements BaseController<T> {

    private final Class<T> MODEL_CLASS;
    private final Scanner SCANNER;
    private final BaseService<T> SERVICE;

    public BaseControllerImpl(Class<T> modelClass, Scanner scanner) {

        this.MODEL_CLASS = modelClass;
        this.SCANNER = scanner;
        this.SERVICE = new BaseServiceImpl<>(MODEL_CLASS);
    }

    @SneakyThrows
    @Override
    public void general() {

        boolean a = true;

        while (a) {

            System.out.println("\nМеню " + MODEL_CLASS.getSimpleName() + ":\n" +
                    "1 - сохранить\n" +
                    "2 - получить по ID\n" +
                    "3 - получить всё\n" +
                    "4 - обновить\n" +
                    "5 - удалить\n" +
                    "0 - возврат в предыдущее меню\n");

            String i = SCANNER.nextLine();
            T model;
            int id;

            switch (i) {
                case "1":

                    model = ModelCreator.create(MODEL_CLASS, SCANNER, "save", false);
                    save(model);
                    break;
                case "2":

                    id = ModelCreator.getInt("ID",SCANNER);
                    Optional<T> opt = get(id);

                    if(opt.isEmpty()) {
                        System.out.println("В базе отсутствует запись с таким ID.");
                    }
                    else {
                        System.out.println(opt.get());
                    }
                    break;
                case "3":

                    System.out.println(getAll());
                    break;
                case "4":

                    model = ModelCreator.create(MODEL_CLASS, SCANNER, "update", false);
                    update(model);
                    break;
                case "5":

                    id = ModelCreator.getInt("ID",SCANNER);
                    delete(id);
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

    @SneakyThrows
    @Override
    public void save(T model) {

        System.out.println(SERVICE.save(model));
    }

    @SneakyThrows
    @Override
    public Optional<T> get(int id) {

        return SERVICE.getById(id);
    }

    @SneakyThrows
    @Override
    public List<T> getAll() {

        return SERVICE.getAll();
    }

    @SneakyThrows
    @Override
    public void update(T model) {

        System.out.println(SERVICE.update(model));
    }

    @SneakyThrows
    @Override
    public void delete(int id) {

        System.out.println(SERVICE.delete(id));
    }
}