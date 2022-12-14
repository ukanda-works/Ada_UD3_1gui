package es.severo.manuelamoros.persistence.util;

import es.severo.manuelamoros.app.controllers.DgFormularioController;
import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.List;
import java.util.stream.Collectors;

public class DialogDB {

    private static ClaseDAOImp claseDaoImp = new ClaseDAOImp();
    private static AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();

    public static void addProfesor(DgFormularioController controller){
        Profesor p = new Profesor();
    }
    public static void addAlumno(DgFormularioController controller){
        Alumno a = new Alumno();
        a.setId(Long.valueOf(controller.tfId.getText()));
        a.setNombreAlumno(controller.tfNombre.getText());
        a.setApellidosAlumno(controller.tfApellidos.getText());
        a.setTelefonoAlumno(controller.tfTelefono.getText());
        a.setDireccionAlumno(controller.tfDireccion.getText());
        a.setEmailAlumno(controller.tfEmail.getText());
        Clase c = claseDaoImp.findByName(controller.cbClase.getItems().get(controller.cbClase.getSelectionModel().getSelectedIndex()));
        a.setClase(c);
        alumnoDAOImp.save(a);
    }

    public static List<String> getAllClase(){
       return claseDaoImp.findAll().stream().map(Clase::getNombreClase).collect(Collectors.toList());
    }
}
