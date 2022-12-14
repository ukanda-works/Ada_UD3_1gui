package es.severo.manuelamoros.persistence.test;

import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.util.DButil;

public class pruebas {
    public static void main(String[] args) {
        ClaseDAOImp claseDAOImp = new ClaseDAOImp();
        System.out.println(claseDAOImp.findByName("1KDAM"));
    }
}
