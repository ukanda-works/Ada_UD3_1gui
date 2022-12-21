package es.severo.manuelamoros.persistence.service;

import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.exceptions.CustomExecption;
import es.severo.manuelamoros.persistence.util.AlertsUtil;
import jakarta.persistence.PersistenceException;
import javafx.scene.control.Alert;

import java.util.List;
import java.util.stream.Collectors;

public class AlumnoDataService {
    private static AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();

    public static List<Alumno> filtrarPorNombre(String termino){
        return alumnoDAOImp.findAll().stream().filter(alumno -> alumno.getNombreAlumno().contains(termino)).collect(Collectors.toList());
    }
    public static List<Alumno> filtrarPorApellido(String termino){
        return alumnoDAOImp.findAll().stream().filter(alumno -> alumno.getApellidosAlumno().contains(termino)).collect(Collectors.toList());
    }
    public static List<Alumno> filtrarPorNia(String termino){
        return alumnoDAOImp.findAll().stream().filter(alumno -> alumno.getNiaAlumno().contains(termino)).collect(Collectors.toList());
    }
    public static List<Alumno> filtrarPorAsignatura(String termino){
        return alumnoDAOImp.findAll().stream().filter(alumno -> alumno.getAsignaturas().contains(termino)).collect(Collectors.toList());
    }
    public static void addAlumno(Alumno alumno) throws CustomExecption {
        try {
            alumnoDAOImp.save(alumno);
            AlertsUtil.showInfo("Alumno añadido correctamente","El alumno ha sido añadido correctamente");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al añadir "+alumno.getNombreAlumno()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se añadia", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void updateAlumno(Alumno alumno) throws CustomExecption {
        try {
            alumnoDAOImp.update(alumno);
            AlertsUtil.showInfo("Alumno actualizado correctamente","El alumno ha sido actualizado correctamente");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al actualizar "+alumno.getNombreAlumno()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se actualizaba", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void updateAlumnoBien(Alumno alumno) throws CustomExecption {
        try {
            alumnoDAOImp.updateAlu(alumno);
        }catch (PersistenceException e){
            throw new CustomExecption("Error al actualizar "+alumno.getNombreAlumno()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se actualizaba", CustomExecption.CustomType.unexpected_error);
        }
    }

    public static void deleteAlu(Alumno alu) throws CustomExecption {
        try {
            alumnoDAOImp.delete(alu);
            AlertsUtil.showInfo("Alumno eliminado correctamente","El alumno ha sido eliminado correctamente");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al eliminar "+alu.getNombreAlumno()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se eliminaba", CustomExecption.CustomType.unexpected_error);
        }
    }

}
