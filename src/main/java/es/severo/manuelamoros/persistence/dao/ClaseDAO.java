package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;

import java.util.List;

public interface ClaseDAO extends GenericDAO<Clase> {
    Clase findByName(String name);
    List<Profesor> getProfes();
}
