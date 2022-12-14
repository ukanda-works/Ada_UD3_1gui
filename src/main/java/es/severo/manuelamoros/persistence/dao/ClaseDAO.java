package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Clase;

public interface ClaseDAO extends GenericDAO<Clase> {
    Clase findByName(String name);
}
