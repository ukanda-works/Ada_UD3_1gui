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

    public static Alumno addAlumno(DgFormularioController controller){
        Alumno a = new Alumno();
        a.setNiaAlumno(controller.tfId.getText());
        a.setNombreAlumno(controller.tfNombre.getText());
        a.setApellidosAlumno(controller.tfApellidos.getText());
        a.setTelefonoAlumno(controller.tfTelefono.getText());
        a.setDireccionAlumno(controller.tfDireccion.getText());
        a.setEmailAlumno(controller.tfEmail.getText());
        Clase c = claseDaoImp.findByName(controller.cbClase.getItems().get(controller.cbClase.getSelectionModel().getSelectedIndex()));
        a.setClase(c);
        List<Asignatura> asignaturas = controller.lvAsignaturas.getItems().stream().map(name->asignaturaDAOImp.findByName(name)).collect(Collectors.toList());
        a.setAsignaturas(asignaturas);
        return a;
    }

    public static Alumno updateAlumno(DgFormularioController controller){
        Alumno a = new Alumno();
        a.setNiaAlumno(controller.tfId.getText());
        a.setNombreAlumno(controller.tfNombre.getText());
        a.setApellidosAlumno(controller.tfApellidos.getText());
        a.setTelefonoAlumno(controller.tfTelefono.getText());
        a.setDireccionAlumno(controller.tfDireccion.getText());
        a.setEmailAlumno(controller.tfEmail.getText());
        Clase c = claseDaoImp.findByName(controller.cbClase.getItems().get(controller.cbClase.getSelectionModel().getSelectedIndex()));
        a.setClase(c);

        List<Asignatura> asignaturas = controller.lvAsignaturas.getItems().stream().map(name->asignaturaDAOImp.findByName(name)).collect(Collectors.toList());
        a.setAsignaturas(asignaturas);
        return a;
    }

    public static Profesor addProfesor(DgFormularioController controller){
        Profesor p = new Profesor();
        p.setApellidoProfesor(controller.tfApellidos.getText());
        p.setDniProfesor(controller.tfId.getText());
        p.setNombreProfesor(controller.tfNombre.getText());
        p.setDireccionProfesor(controller.tfDireccion.getText());
        p.setEmailProfesor(controller.tfEmail.getText());
        p.setTelefonoProfesor(controller.tfTelefono.getText());
        return p;
    }

    public static Profesor updateProfesor(DgFormularioController controller){
        Profesor p = new Profesor();
        p.setApellidoProfesor(controller.tfApellidos.getText());
        p.setDniProfesor(controller.tfId.getText());
        p.setNombreProfesor(controller.tfNombre.getText());
        p.setDireccionProfesor(controller.tfDireccion.getText());
        p.setEmailProfesor(controller.tfEmail.getText());
        p.setTelefonoProfesor(controller.tfTelefono.getText());
        return p;
    }

    public static Clase addClase(DgFormularioController controller){
        Clase c = new Clase();
        c.setAula(controller.tfEmail.getText());
        c.setNombreClase(controller.tfNombre.getText());
        String dniProf = controller.cbClase.getSelectionModel().getSelectedItem().split("-")[1];
        c.setTutor(profesorDAOImp.findByDni(dniProf));
        return c;
    }

    public static Clase updateClase(DgFormularioController controller){
        Clase c = new Clase();
        c.setAula(controller.tfEmail.getText());
        c.setNombreClase(controller.tfNombre.getText());
        String dniProf = controller.cbClase.getSelectionModel().getSelectedItem().split("-")[1];
        Profesor tutor = profesorDAOImp.findByDni(dniProf);
        System.out.println(dniProf);
        tutor.mostrar();
        c.setTutor(tutor);

        return c;
    }

    public static Asignatura addAsignatura(DgFormularioController controller){
        Asignatura asig = new Asignatura();
        asig.setNombreAsignatura(controller.tfTelefono.getText());
        return asig;
    }

    public static Asignatura updateAsignatura(DgFormularioController controller){
        Asignatura asig = new Asignatura();
        asig.setId(Long.valueOf(controller.tfId.getText()));
        asig.setNombreAsignatura(controller.tfTelefono.getText());
        return asig;
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
                profesoresSin.add(p.getNombreProfesor()+"-"+p.getDniProfesor());
            }
        }
        return profesoresSin;
    }

    public static List<String> getAllAsignaturas() {
        return asignaturaDAOImp.findAll().stream().map(Asignatura::getNombreAsignatura).collect(Collectors.toList());
    }
}
