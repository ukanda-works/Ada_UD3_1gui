package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Asignatura;

import java.util.List;

public interface AsignaturaDAO extends GenericDAO<Asignatura> {

    List<Asignatura> findByNombre(String nombre);
    Asignatura findByName(String name);
}
