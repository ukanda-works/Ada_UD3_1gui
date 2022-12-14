package es.severo.manuelamoros.persistence.util;

import com.google.gson.Gson;
import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.dao.AsignaturaDAOImp;
import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DButil {
    private static ProfesorDAOImp pImp= new ProfesorDAOImp();
    private static AlumnoDAOImp aluImp = new AlumnoDAOImp();
    private static AsignaturaDAOImp asigImp = new AsignaturaDAOImp();
    private static ClaseDAOImp cImp = new ClaseDAOImp();

    public static void showAll(){
        System.out.println("*****PROFESORES***********");
        pImp.findAll().forEach(Profesor::mostrar);
        System.out.println("*****CLASES***************");
        cImp.findAll().forEach(Clase::mostrar);
        System.out.println("*****ALUMNOS**************");
        aluImp.findAll().forEach(Alumno::mostrar);
        System.out.println("*****ASIGNATURAS**********");
        asigImp.findAll().forEach(Asignatura::mostrar);
    }

    public static void exportAllJson(String url){
        Gson gson = new Gson();
        try(BufferedWriter buffer = new BufferedWriter(new FileWriter(url))) {
            pImp.findAll().forEach(a -> {
                try {
                    buffer.write(gson.toJson(a));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void exportAllXML(){

    }

    public static void main(String[] args) {
        exportAllJson("ola.json");
    }
}
