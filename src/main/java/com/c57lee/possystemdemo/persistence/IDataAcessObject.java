package com.c57lee.possystemdemo.persistence;

import java.util.List;
import java.util.Optional;

public interface IDataAcessObject {
    public <T> void save(T t);

    public <T,ID> Optional<T> findByID(Class<T> t, ID id);

    public <T> List<T> findAll(Class<T> tClass);

    public <T> void update(T t);
    public <T> void remove(T t);
    public <T> boolean contains(T t);

}
