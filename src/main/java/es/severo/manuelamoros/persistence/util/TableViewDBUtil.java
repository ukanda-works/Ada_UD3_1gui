package es.severo.manuelamoros.persistence.util;

import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.dao.AsignaturaDAOImp;
import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.entity.*;
import es.severo.manuelamoros.persistence.exceptions.CustomExecption;
import es.severo.manuelamoros.persistence.service.AlumnoDataService;
import es.severo.manuelamoros.persistence.service.AsignaturaDataService;
import es.severo.manuelamoros.persistence.service.ClaseDataService;
import es.severo.manuelamoros.persistence.service.ProfesorDataService;
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







    public static List<Alumno> filtrarAlu(String campo, String parametro) throws CustomExecption {
        switch (campo){
            case ("NIA"):
                return AlumnoDataService.filtrarPorNia(parametro);

            case("Nombre"):
                return AlumnoDataService.filtrarPorNombre(parametro);

            case("Apellido"):
                return AlumnoDataService.filtrarPorApellido(parametro);

            case("Asignatura"):
                return AlumnoDataService.filtrarPorAsignatura(parametro);
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static List<Profesor> filtrarProfesor(String campo, String parametro) throws CustomExecption {
        switch (campo){
            case ("DNI"):
                return ProfesorDataService.filtrarPorDni(parametro);
            case("Nombre"):
                return ProfesorDataService.filtrarPorNombre(parametro);
            case("Apellido"):
                return ProfesorDataService.filtrarPorApellido(parametro);
            case("Direccion"):
                return ProfesorDataService.filtrarPorDireccion(parametro);
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }

    }
    public static List<Clase> filtrarClase(String campo, String parametro) throws CustomExecption {

        switch (campo){
            case("Nombre de clase"):
                return ClaseDataService.filtrarPorNombre(parametro);
            case("Aula"):
                return ClaseDataService.filtrarPorAula(parametro);
            case("Tutor (Dni)"):
                return ClaseDataService.filtrarPorTutor(parametro);
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static List<Asignatura> filtrarAsig(String campo, String parametro) throws CustomExecption {
        switch (campo){
            case("Nombre asignatura"):
                return AsignaturaDataService.filtrarPorNombre(parametro);
            default:
                throw new CustomExecption("Error inesperado al filtrar", CustomExecption.CustomType.unexpected_error);
        }
    }
}
