package Hibernate.repository;

import Hibernate.model.BaseModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseModel> {

    int save(T model);

    void saveAll (Iterable<T> models);

    Optional<T> getById(int id);

    List<T> getAll() throws SQLException;

    void update(T model);

    void delete(T model);

    boolean isDuplicate(String fieldName, String value);
}