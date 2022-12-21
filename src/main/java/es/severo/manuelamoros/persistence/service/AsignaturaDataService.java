package es.severo.manuelamoros.persistence.service;

import es.severo.manuelamoros.persistence.dao.AsignaturaDAOImp;
import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;
import es.severo.manuelamoros.persistence.exceptions.CustomExecption;
import es.severo.manuelamoros.persistence.util.AlertsUtil;
import jakarta.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AsignaturaDataService {
    private static AsignaturaDAOImp asignaturaDAOImp = new AsignaturaDAOImp();

    public static List<Asignatura> filtrarPorNombre(String termino){
        return asignaturaDAOImp.findAll().stream().filter(asignatura -> asignatura.getNombreAsignatura().contains(termino)).collect(Collectors.toList());
    }
    public static void addAsignatura(Asignatura asignatura) throws CustomExecption {
        try {
            asignaturaDAOImp.save(asignatura);
            AlertsUtil.showInfo("Asinatura añadida","La asignatura seleccionada ha sido añadida");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al añadir "+asignatura.getNombreAsignatura()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se añadia", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void updateAsignatura(Asignatura asignatura) throws CustomExecption {
        try {
            asignaturaDAOImp.update(asignatura);
            //AlertsUtil.showInfo("Asinatura actualizada","La asignatura seleccionada ha sido actualizada");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al actualizar "+asignatura.getNombreAsignatura()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se actualizaba", CustomExecption.CustomType.unexpected_error);
        }
    }
    public static void deleteAsig(Asignatura asig) throws CustomExecption {
        try {
            AlumnoDataService.filtrarPorAsignatura(asig.getNombreAsignatura()).forEach(alumno -> {
                alumno.delAsig(asig);
                try {
                    AlumnoDataService.updateAlumnoBien(alumno);
                } catch (CustomExecption e) {
                    throw new RuntimeException(e);
                }
            });
            asignaturaDAOImp.delete(asig);
            AlertsUtil.showInfo("Asinatura eliminada","La asignatura seleccionada ha sido eliminada");
        }catch (PersistenceException e){
            throw new CustomExecption("Error al eliminar "+asig.getNombreAsignatura()+e.getMessage(), CustomExecption.CustomType.Persistence);
        } catch (Exception e){
            throw new CustomExecption("Ha sucedido un error mientras se eliminaba", CustomExecption.CustomType.unexpected_error);
        }
    }

}
