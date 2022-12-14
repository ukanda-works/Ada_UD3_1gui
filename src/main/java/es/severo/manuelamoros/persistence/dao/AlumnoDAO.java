package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;

import java.util.List;

public interface AlumnoDAO extends GenericDAO<Alumno> {


    List<Asignatura> getAsignaturasById(Long id);
}
