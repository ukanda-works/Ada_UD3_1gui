package es.severo.manuelamoros.persistence.util;

import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.dao.AsignaturaDAOImp;
import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.entity.*;
import es.severo.manuelamoros.persistence.exceptions.CustomExecption;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class TableViewDBUtil {
    private static AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();
    private static ProfesorDAOImp profesorDAOImp = new ProfesorDAOImp();
    private static ClaseDAOImp claseDAOImp = new ClaseDAOImp();
    private static AsignaturaDAOImp asignaturaDAOImp = new AsignaturaDAOImp();
    public static Alumno getByNiaAlu(String nia){
        return alumnoDAOImp.findByNia(nia);
    }

    public static void deleteByNiaAlu(String nia){
        try {
            alumnoDAOImp.deleteByNia(nia);
        }catch (Exception e){
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alumno eliminado correctamente");
        alert.setHeaderText("El alumno ha sido eliminado correctamente");
        alert.showAndWait();

    }

    public static void deleteAlu(Alumno alu){
        try {
            alumnoDAOImp.delete(alu);
        }catch (Exception e){
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alumno eliminado correctamente");
        alert.setHeaderText("El alumno ha sido eliminado correctamente");
        alert.showAndWait();

    }
    public static Profesor getByDniProf(String dni){
        return profesorDAOImp.findByDni(dni);
    }

    public static void deleteByDniProf(String dni){
        try {
            profesorDAOImp.deleteByDni(dni);
        }catch (Exception e){
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profesor eliminado correctamente");
        alert.setHeaderText("El prosfesor ha sido eliminado correctamente");
        alert.showAndWait();

    }

    public static void deleteProf(Profesor prof){
        try {
            profesorDAOImp.delete(prof);
        }catch (Exception e){
            e.printStackTrace();
        }
        AlertsUtil.showInfo("Profesor elminado","El profesor seleccionado ha sido eliminado");
    }

    public static void deleteClase(Clase clase){
        try {
            claseDAOImp.delete(clase);
        }catch (Exception e){
            e.printStackTrace();
        }
        AlertsUtil.showInfo("Clase elminada","La clase seleccionada ha sido eliminada");
    }

    public static void deleteAsig(Asignatura asig){
        try {
            asignaturaDAOImp.delete(asig);
        }catch (Exception e){
            e.printStackTrace();
        }
        AlertsUtil.showInfo("Asinatura eliminada","La asignatura seleccionada ha sido eliminada");
    }

    public static List<Alumno> filtrarAlu(String campo, String parametro) throws CustomExecption {
        List<Alumno> alumnoList = new ArrayList<>();
        switch (campo){
            case ("NIA"):
                alumnoList.add(alumnoDAOImp.findByNia(parametro));
                return alumnoList;
            case("Nombre"):
                alumnoList = alumnoDAOImp.findByNombre(parametro);
                return alumnoList;
            case("Apellido"):
                alumnoList = alumnoDAOImp.findByApellido(parametro);
                return alumnoList;
            case("Direccion"):
                alumnoList = alumnoDAOImp.findByDireccion(parametro);
                return alumnoList;
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static List<Profesor> filtrarProfesor(String campo, String parametro) throws CustomExecption {
        List<Profesor> profesorList = new ArrayList<>();
        switch (campo){
            case ("DNI"):
               profesorList.add(profesorDAOImp.findByDni(parametro));
                return profesorList;
            case("Nombre"):
                profesorList = profesorDAOImp.findByNombre(parametro);
                return profesorList;
            case("Apellido"):
                profesorList = profesorDAOImp.findByApellido(parametro);
                return profesorList;
            case("Direccion"):
                profesorList = profesorDAOImp.findByDireccion(parametro);
                return profesorList;
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }

    }
    public static List<Clase> filtrarClase(String campo, String parametro) throws CustomExecption {
        List<Clase> claseList = new ArrayList<>();
        switch (campo){
            case("Nombre de clase"):
                claseList = claseDAOImp.findByNombre(parametro);
                return claseList;
            case("Aula"):
                claseList = claseDAOImp.findByAula(parametro);
                return claseList;
            case("Tutor (Dni)"):
                claseList = claseDAOImp.findByDniTutor(parametro);
                return claseList;
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static List<Asignatura> filtrarAsig(String campo, String parametro) throws CustomExecption {
        switch (campo){
            case("Nombre asignatura"):
                return asignaturaDAOImp.findByNombre(parametro);
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }
    }
}
