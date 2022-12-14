package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Profesor;

public class ProfesorDAOImp extends GenericDAOImpl<Profesor> implements ProfesorDAO{
    public ProfesorDAOImp() {
        super(Profesor.class);
    }
}
