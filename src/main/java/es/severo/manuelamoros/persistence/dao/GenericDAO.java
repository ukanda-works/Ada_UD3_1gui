package es.severo.manuelamoros.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    void create(T t);
    void update(T t);
    void save(T entity);
    void deleteById(Long id);
    void delete(T t);
    List<T> findAll();
    Optional<T> findById(Long id);
}
