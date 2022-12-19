package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Profesor;

import java.util.List;

public interface ProfesorDAO extends GenericDAO<Profesor> {
    Profesor findByDni(String dni);
    void deleteByDni(String dni);
    void update(Profesor profesor);
    List<Profesor> findByNombre(String nombre);
    List<Profesor> findByApellido(String apellido);
    List<Profesor> findByDireccion(String direccion);
}
