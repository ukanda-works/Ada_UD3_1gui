package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;

import java.util.List;

public class AlumnoDAOImp extends GenericDAOImpl<Alumno> implements AlumnoDAO{

    public AlumnoDAOImp() {super(Alumno.class);}


    @Override
    public List<Asignatura> getAsignaturasById(Long id) {
        return null;
    }
}
