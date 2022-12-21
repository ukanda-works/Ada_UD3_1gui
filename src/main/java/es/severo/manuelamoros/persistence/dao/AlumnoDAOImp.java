package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Alumno_;
import es.severo.manuelamoros.persistence.entity.Asignatura;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class AlumnoDAOImp extends GenericDAOImpl<Alumno> implements AlumnoDAO{

    public AlumnoDAOImp() {super(Alumno.class);}


    @Override
    public List<Asignatura> getAsignaturasById(Long id) {
        return null;
    }

    @Override
    public Alumno findByNia(String nia) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Alumno> criteria = builder.createQuery(Alumno.class);
            Root<Alumno> root = criteria.from(Alumno.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Alumno_.NIA_ALUMNO),nia));
            return session.createQuery(criteria).getSingleResult();
        }

    }

    @Override
    public void deleteByNia(String nia) {
            try (Session session = HibernateUtil.getSessionFactory().openSession();) {
                session.beginTransaction();
                session.remove(session.find(Alumno.class, nia));
                session.getTransaction().commit();
            }

    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Alumno> criteria = builder.createQuery(Alumno.class);
            Root<Alumno> root =criteria.from(Alumno.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Alumno_.NOMBRE_ALUMNO),nombre));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public List<Alumno> findByApellido(String apellido) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Alumno> criteria = builder.createQuery(Alumno.class);
            Root<Alumno> root =criteria.from(Alumno.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Alumno_.APELLIDOS_ALUMNO),apellido));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public List<Alumno> findByDireccion(String direccion) {
        try (Session session =HibernateUtil.getSessionFactory().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Alumno> criteria = builder.createQuery(Alumno.class);
            Root<Alumno> root =criteria.from(Alumno.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Alumno_.DIRECCION_ALUMNO),direccion));
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void update(Alumno alumno) {
        String nia = alumno.getNiaAlumno();
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Alumno> criteria = builder.createQuery(Alumno.class);
            Root<Alumno> root = criteria.from(Alumno.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get(Alumno_.NIA_ALUMNO),nia));
            alumno.setId(session.createQuery(criteria).getSingleResult().getId());
            session.beginTransaction();
            session.merge(alumno);
            session.getTransaction().commit();
        }
    }
    public void updatee(Alumno alumno){
        String nia = alumno.getNiaAlumno();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Alumno alumno1 = findByNia(nia);
            alumno1.setEmailAlumno(alumno.getEmailAlumno());
            alumno1.setTelefonoAlumno(alumno.getTelefonoAlumno());
            alumno1.setApellidosAlumno(alumno.getApellidosAlumno());
            alumno1.setAsignaturas(alumno.getAsignaturasAsig());
            alumno1.setNombreAlumno(alumno.getNombreAlumno());
            session.beginTransaction();
            session.update(alumno1);
            session.getTransaction().commit();
        }
    }
    public void updateAlu(Alumno alumno) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.merge(alumno);
            session.getTransaction().commit();
        }
    }

}
