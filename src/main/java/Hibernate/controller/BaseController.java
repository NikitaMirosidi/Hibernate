package Hibernate.controller;

import Hibernate.model.BaseModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseController<T extends BaseModel> {

    void general();

    void save(T model);

    Optional<T> get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void update(T model) throws SQLException, NoSuchFieldException, IllegalAccessException;

    void delete(int id) throws SQLException;
}