package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Asignatura;

import java.util.List;

public interface AsignaturaDAO extends GenericDAO<Asignatura> {
    void update(Asignatura asig);
    List<Asignatura> findByNombre(String nombre);
    Asignatura findByName(String name);
}
