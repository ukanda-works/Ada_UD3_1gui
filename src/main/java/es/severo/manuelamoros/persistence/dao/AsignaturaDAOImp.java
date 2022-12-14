package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Asignatura;

public class AsignaturaDAOImp extends GenericDAOImpl<Asignatura> implements AsignaturaDAO {

    public AsignaturaDAOImp() {
        super(Asignatura.class);
    }
}
