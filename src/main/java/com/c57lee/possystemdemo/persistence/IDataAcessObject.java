package com.c57lee.possystemdemo.persistence;

import java.util.List;
import java.util.Optional;

public interface IDataAcessObject<T,ID> {
    public void save(T t);
    public Optional<T> findByID(ID id);

    public List<T> findAll();
    public void update(T t);

}
