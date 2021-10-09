package Hibernate.service;

import Hibernate.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseModel>{

    String save(T model);
    Optional<T> getById(int id);
    List<T> getAll();
    String update(T model);
    String delete(int id);
}