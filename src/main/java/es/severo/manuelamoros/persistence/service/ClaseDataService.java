package es.severo.manuelamoros.persistence.service;

import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.exceptions.CustomExecption;
import es.severo.manuelamoros.persistence.util.AlertsUtil;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.stream.Collectors;

public class ClaseDataService {
    private static ClaseDAOImp claseDAOImp = new ClaseDAOImp();

    public static List<Clase> filtrarPorNombre(String termino){
        return claseDAOImp.findAll().stream().filter(clase -> clase.getNombreClase().contains(termino)).collect(Collectors.toList());
    }
    public static List<Clase> filtrarPorAula(String termino){
        return claseDAOImp.findAll().stream().filter(clase -> clase.getAula().contains(termino)).collect(Collectors.toList());
    }
    public static List<Clase> filtrarPorTutor(String termino){
        return claseDAOImp.findAll().stream().filter(clase -> clase.getTutorTutor().getDniProfesor().contains(termino)).collect(Collectors.toList());
    }
    public static void addClase(Clase clase) throws CustomExecption {
        try {
            claseDAOImp.save(clase);
            AlertsUtil.showInfo("Clase añadida","La clase seleccionada ha sido añadida");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al añadir "+clase.getNombreClase()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se añadia ", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void updateClase(Clase clase) throws CustomExecption {
        try {
            claseDAOImp.update(clase);
            AlertsUtil.showInfo("Clase actualizada","La clase seleccionada ha sido actualizada");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al actualizar "+clase.getNombreClase()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se actualizaba  ", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void deleteClase(Clase clase) throws CustomExecption {
        try {
            claseDAOImp.delete(clase);
            AlertsUtil.showInfo("Clase elminada","La clase seleccionada ha sido eliminada");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al eliminar "+clase.getNombreClase()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se eliminaba ", CustomExecption.CustomType.unexpected_error);
        }

    }
}
