package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.*;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class AsignaturaDAOImp extends GenericDAOImpl<Asignatura> implements AsignaturaDAO {

    public AsignaturaDAOImp() {
        super(Asignatura.class);
    }

    @Override
    public List<Asignatura> findByNombre(String nombre) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Asignatura> criteria = builder.createQuery(Asignatura.class);
            Root<Asignatura> root =criteria.from(Asignatura.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Asignatura_.NOMBRE_ASIGNATURA),nombre));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public Asignatura findByName(String name) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Asignatura> criteria = builder.createQuery(Asignatura.class);
            Root<Asignatura> root =criteria.from(Asignatura.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Asignatura_.NOMBRE_ASIGNATURA),name));
            return session.createQuery(criteria).getSingleResult();
        }
    }
}
