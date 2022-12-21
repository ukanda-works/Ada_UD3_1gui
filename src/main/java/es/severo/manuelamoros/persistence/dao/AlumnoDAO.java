package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;

import java.util.List;

public interface AlumnoDAO extends GenericDAO<Alumno> {


    List<Asignatura> getAsignaturasById(Long id);
    Alumno findByNia(String nia);
    void deleteByNia(String nia);

    List<Alumno> findByNombre(String nombre);
    List<Alumno> findByApellido(String apellido);
    List<Alumno> findByDireccion(String direccion);

    void updateAlu(Alumno alumno);
 }
