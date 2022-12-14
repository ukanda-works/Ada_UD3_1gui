package es.severo.manuelamoros.persistence.test;

import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import org.hibernate.Session;

public class TestOneToOne {
    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Profesor p = new Profesor("Ramon","Cajal","ramonYcajal@gmail.com","+21 5645622","Calle falsa nº123");
            Clase c = new Clase("1CCOW","aula5",p);
            c.setId(1L);
            Alumno a = new Alumno("Rickk","Calle santiago segura","+31 54555",c);


            session.persist(a);

            session.flush();


            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
