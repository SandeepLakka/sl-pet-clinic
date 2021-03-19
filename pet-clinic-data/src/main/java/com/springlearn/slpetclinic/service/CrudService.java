package com.springlearn.slpetclinic.service;

import java.util.Set;

public interface CrudService<T,ID> {

    T findById(ID id);

    T save(T owner);

    void delete(T object);

    void deleteById(ID id);

    Set<T> findAll();
}
