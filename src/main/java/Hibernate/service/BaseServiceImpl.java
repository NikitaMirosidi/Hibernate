package Hibernate.service;

import Hibernate.model.BaseModel;
import Hibernate.repository.BaseRepository;
import Hibernate.repository.RepositoryCreator;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

import java.util.*;

public class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    private final BaseRepository<T> REPOSITORY;
    private final Class<T> MODEL_CLASS;

    public BaseServiceImpl(Class<T> modelClass) {
        this.MODEL_CLASS = modelClass;
        this.REPOSITORY = RepositoryCreator.of(modelClass);
    }

    @SneakyThrows
    @Override
    public String save(T model) {

        String answer = "";

        if(isUniqueFieldDuplicate(model)) {
            answer += "Запись с указанным значением 'name' уже есть в базе.\n";
        }

        if(answer.length() != 0) {
            answer += "Сохранение невозможно!";
            return answer;
        }
        else {
            int id = REPOSITORY.save(model);
            return "Сохранено c ID = " + id + ".\n";
        }
    }

    @SneakyThrows
    @Override
    public Optional<T> getById(int id) {

        return REPOSITORY.getById(id);
    }

    @SneakyThrows
    @Override
    public List<T> getAll() {

        return REPOSITORY.getAll();
    }

    @SneakyThrows
    @Override
    public String update(T model) {

        String answer = "";

        if (!isIdInBase(model)) {
            answer += "В базе отсутствует запись с таким ID.\n";
        }

        if(answer.length() != 0) {
            return answer;
        }
        else {
            REPOSITORY.update(model);
            return "Обновлено.\n";
        }
    }

    @SneakyThrows
    @Override
    public String delete(int id) {

        Optional<T> container = getById(id);

        if (container.isEmpty()) {
            return "Запись с указанным ID отсутствует в базе.";
        }
        else {
            REPOSITORY.delete(container.get());
            return "Удалено.";
        }
    }

    private boolean isIdInBase(T model) {

        return getById(model.getId()).isPresent();
    }

    @SneakyThrows
    private boolean isUniqueFieldDuplicate(T model) {

        String name = "company, customer, project";
        String email = "developer";

        if(name.contains(MODEL_CLASS.getSimpleName().toLowerCase())) {

            return isPresent(model,"name");
        }
        if(email.contains(MODEL_CLASS.getSimpleName().toLowerCase())) {

            return isPresent(model, "email");
        }
        else {
            return false;
        }
    }

    @SneakyThrows
    private boolean isPresent(T model, String fieldName) {

        Field field = MODEL_CLASS.getDeclaredField(fieldName);
        field.setAccessible(true);
        String value = (String) field.get(model);

        return REPOSITORY.isDuplicate(fieldName, value);
    }
}