package es.severo.manuelamoros.persistence.test;

import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;
import es.severo.manuelamoros.persistence.util.DButil;

public class pruebas {
    public static void main(String[] args) {
        ClaseDAOImp claseDAOImp = new ClaseDAOImp();
        Profesor p = new Profesor("Ramon","Cajal","ramonYcajal@gmail.com","+21 5645622","Calle falsa nº123");
        p.setId(5L);
        Clase c = new Clase("1CCOW","aula5",p);
        c.setId(5L);

        claseDAOImp.save(c);
    }
}
