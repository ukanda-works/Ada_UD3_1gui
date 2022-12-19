package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.*;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import es.severo.manuelamoros.persistence.util.TableViewDBUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ClaseDAOImp extends GenericDAOImpl<Clase> implements ClaseDAO {
    public ClaseDAOImp() {
        super(Clase.class);
    }

    @Override
    public Clase findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Clase> criteriaQuery = builder.createQuery(Clase.class);
            Root<Clase> root = criteriaQuery.from(Clase.class);
            criteriaQuery.select(root).where(builder.equal(root.get(Clase_.nombreClase),name));
            return session.createQuery(criteriaQuery).getSingleResult();

        }
    }

    @Override
    public List<Profesor> getProfes() {
        return this.findAll().stream().map(Clase::getTutorTutor).peek(Profesor::mostrar).collect(Collectors.toList());
    }

    @Override
    public void update(Clase clase) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Clase> criteria = builder.createQuery(Clase.class);
            Root<Clase> root = criteria.from(Clase.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Clase_.nombreClase),clase.getNombreClase()));
            clase.setId(session.createQuery(criteria).getSingleResult().getId());
            session.beginTransaction();
            session.merge(clase);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Clase> findByNombre(String nombre) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Clase> criteria = builder.createQuery(Clase.class);
            Root<Clase> root =criteria.from(Clase.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Clase_.NOMBRE_CLASE),nombre));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public List<Clase> findByAula(String alua) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Clase> criteria = builder.createQuery(Clase.class);
            Root<Clase> root =criteria.from(Clase.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Clase_.NOMBRE_CLASE),alua));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public List<Clase> findByDniTutor(String dniTutor) {
        Profesor tutor = TableViewDBUtil.getByDniProf(dniTutor);
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Clase> criteria = builder.createQuery(Clase.class);
            Root<Clase> root =criteria.from(Clase.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Clase_.TUTOR),tutor));
            return session.createQuery(criteria).getResultList();
        }
    }
}
