package es.severo.manuelamoros.persistence.dao;

import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Clase_;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.Optional;

import static es.severo.manuelamoros.persistence.entity.Clase_.nombreClase;

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
            criteriaQuery.select(root).where(builder.equal(root.get(nombreClase),name));
            return session.createQuery(criteriaQuery).getSingleResult();

        }
    }
}
