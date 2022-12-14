package es.severo.manuelamoros.persistence.util;

import es.severo.manuelamoros.app.controllers.DgFormularioController;
import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.dao.AsignaturaDAOImp;
import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DialogDB {

    private static ClaseDAOImp claseDaoImp = new ClaseDAOImp();
    private static AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();
    private static ProfesorDAOImp profesorDAOImp = new ProfesorDAOImp();
    private static AsignaturaDAOImp asignaturaDAOImp = new AsignaturaDAOImp();

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

    public static void addProfesor(DgFormularioController controller){
        Profesor p = new Profesor();

        profesorDAOImp.save(p);
    }

    public static void addClase(DgFormularioController controller){
        Clase c = new Clase();
        claseDaoImp.save(c);
    }

    public static void addAsignatura(DgFormularioController controller){
        Asignatura asig = new Asignatura();
        asignaturaDAOImp.save(asig);
    }

    public static List<String> getAllClase(){
       return claseDaoImp.findAll().stream().map(Clase::getNombreClase).collect(Collectors.toList());
    }

    public static List<String> getProfSinClase(){
        List<Long> tutores = claseDaoImp.getProfes().stream().map(Profesor::getId).collect(Collectors.toList());
        List<Profesor> profesors = profesorDAOImp.findAll();
        List<String> profesoresSin = new ArrayList<>();

        for (Profesor p : profesors) {
            if (!tutores.contains(p.getId())){
                profesoresSin.add(p.getNombreProfesor()+" "+p.getId());
            }
        }
        return profesoresSin;
    }
}
