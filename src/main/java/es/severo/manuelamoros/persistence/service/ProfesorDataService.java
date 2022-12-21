package es.severo.manuelamoros.persistence.service;

import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.entity.Profesor;
import es.severo.manuelamoros.persistence.entity.Profesor_;
import es.severo.manuelamoros.persistence.exceptions.CustomExecption;
import es.severo.manuelamoros.persistence.util.AlertsUtil;
import jakarta.persistence.PersistenceException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

public class ProfesorDataService {
    private static ProfesorDAOImp  profesorDAOImp = new ProfesorDAOImp();
    public static List<Profesor> filtrarPorNombre(String termino){
        return profesorDAOImp.findAll().stream().filter(profesor -> profesor.getNombreProfesor().contains(termino)).collect(Collectors.toList());

    }
    public static List<Profesor> filtrarPorDni(String termino){
        return profesorDAOImp.findAll().stream().filter(profesor -> profesor.getDniProfesor().contains(termino)).collect(Collectors.toList());

    }
    public static List<Profesor> filtrarPorApellido(String termino){
        return profesorDAOImp.findAll().stream().filter(profesor -> profesor.getApellidoProfesor().contains(termino)).collect(Collectors.toList());

    }
    public static List<Profesor> filtrarPorDireccion(String termino){
        return profesorDAOImp.findAll().stream().filter(profesor -> profesor.getDireccionProfesor().contains(termino)).collect(Collectors.toList());
    }

    public static void addProfesor(Profesor profesor) throws CustomExecption {
        try {
            profesorDAOImp.save(profesor);
            AlertsUtil.showInfo("Profesor añadido","El profesor seleccionado ha sido añadido");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al añadir "+profesor.getNombreProfesor()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se añadia", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void updateProfesor(Profesor profesor) throws CustomExecption {
        try {
            profesorDAOImp.update(profesor);
            //AlertsUtil.showInfo("Profesor modificado","El profesor seleccionado ha sido modificado");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al modificar "+profesor.getNombreProfesor()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se modificaba", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void deleteProf(Profesor prof) throws CustomExecption {
        try {
            profesorDAOImp.delete(prof);
            AlertsUtil.showInfo("Profesor elminado","El profesor seleccionado ha sido eliminado");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al eliminar "+prof.getNombreProfesor()+" comprueba que no sea tutor de ninguna clase", CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se eliminaba", CustomExecption.CustomType.unexpected_error);
        }

    }
}
