package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.*;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class ProfesorDAOImp extends GenericDAOImpl<Profesor> implements ProfesorDAO{
    public ProfesorDAOImp() {
        super(Profesor.class);
    }

    @Override
    public Profesor findByDni(String dni) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Profesor> criteria = builder.createQuery(Profesor.class);
            Root<Profesor> root = criteria.from(Profesor.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Profesor_.DNI_PROFESOR),dni));
            return session.createQuery(criteria).getSingleResult();
        }
    }

    @Override
    public void deleteByDni(String dni) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.remove(session.find(Profesor.class, dni));
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Profesor profesor) {
        String dni = profesor.getDniProfesor();
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Profesor> criteria = builder.createQuery(Profesor.class);
            Root<Profesor> root = criteria.from(Profesor.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Profesor_.DNI_PROFESOR),dni));
            profesor.setId(session.createQuery(criteria).getSingleResult().getId());
            session.beginTransaction();
            session.merge(profesor);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Profesor> findByNombre(String nombre) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Profesor> criteria = builder.createQuery(Profesor.class);
            Root<Profesor> root =criteria.from(Profesor.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Profesor_.NOMBRE_PROFESOR),nombre));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public List<Profesor> findByApellido(String apellido) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Profesor> criteria = builder.createQuery(Profesor.class);
            Root<Profesor> root =criteria.from(Profesor.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Profesor_.APELLIDO_PROFESOR),apellido));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public List<Profesor> findByDireccion(String direccion) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Profesor> criteria = builder.createQuery(Profesor.class);
            Root<Profesor> root =criteria.from(Profesor.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Profesor_.DIRECCION_PROFESOR),direccion));
            return session.createQuery(criteria).getResultList();
        }
    }
}
