package Hibernate.repository;

import Hibernate.model.BaseModel;

import java.util.HashMap;
import java.util.Map;

public class RepositoryCreator {

    private static final Map<String, BaseRepository> REPOSITORIES = new HashMap<>();

    public static <T extends BaseModel> BaseRepository<T> of(Class<T> modelClass) {

        if(!REPOSITORIES.containsKey(modelClass.getSimpleName())) {
            REPOSITORIES.put(modelClass.getSimpleName(), new BaseRepositoryImpl<>(modelClass));
        }

        return REPOSITORIES.get(modelClass.getSimpleName());
    }
}