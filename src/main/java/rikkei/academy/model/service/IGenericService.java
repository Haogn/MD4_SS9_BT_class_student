package rikkei.academy.model.service;

import java.util.List;

public interface IGenericService<T,ID> {
    List<T> findAll();
    Boolean create(T t);
    Boolean update(T t , ID id);
    void delete(ID id);
    T findById(ID id) ;
}
