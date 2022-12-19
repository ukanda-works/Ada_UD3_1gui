package es.severo.manuelamoros.app.controllers;

import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;
import es.severo.manuelamoros.persistence.util.AlertsUtil;
import es.severo.manuelamoros.persistence.util.DialogDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class DgFormularioController {

    @FXML
    private Label lbId;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellidos;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbClase;
    @FXML
    private Label lbDireccion;
    @FXML
    private Label lbTelefono;
    @FXML
    public ListView<String> lvAsignaturas;
    @FXML
    private Button btAdd;
    @FXML
    private ComboBox<String> cbAsignaturas;

    @FXML
    public TextField tfId;
    @FXML
    public TextField tfTelefono;
    @FXML
    public TextField tfNombre;
    @FXML
    public TextField tfApellidos;
    @FXML
    public TextField tfEmail;
    @FXML
    public TextField tfDireccion;

    @FXML
    public ComboBox<String> cbClase;

    protected void load(int selectedItem){
        switch (selectedItem){
            case 0:
                lbId.setText("NIA");
                lbNombre.setText("Nombre: ");
                lbApellidos.setText("Apellidos");
                lbCorreo.setText("Email:");
                lbTelefono.setText("Telefono");
                lbDireccion.setText("Direccion");
                lbClase.setText("Clase");
                cbAsignaturas.getItems().addAll(DialogDB.getAllAsignaturas());
                cbClase.getItems().addAll(DialogDB.getAllClase());
                break;
            case 1:
                lbId.setText("Dni");
                lbNombre.setText("Nombre");
                lbApellidos.setText("Apellidos");
                lbCorreo.setText("Email");
                lbTelefono.setText("Telefono");
                lbDireccion.setText("Direccion");
                lbClase.setVisible(false);
                cbClase.setVisible(false);
                lvAsignaturas.setVisible(false);
                cbAsignaturas.setVisible(false);
                btAdd.setVisible(false);
                break;
            case 2:
                lbId.setVisible(false);
                tfId.setVisible(false);
                lbNombre.setText("Nombre: ");
                lbCorreo.setText("Aula:");
                lbClase.setText("Tutor:");
                cbClase.getItems().addAll(DialogDB.getProfSinClase());
                lbApellidos.setVisible(false);
                tfApellidos.setVisible(false);
                lbTelefono.setVisible(false);
                tfTelefono.setVisible(false);
                lbDireccion.setVisible(false);
                tfDireccion.setVisible(false);
                tfTelefono.setVisible(false);
                lvAsignaturas.setVisible(false);
                cbAsignaturas.setVisible(false);
                btAdd.setVisible(false);

                break;
            case 3:
                lbId.setVisible(false);
                tfId.setVisible(false);
                lbTelefono.setText("Nombre:");

                lbApellidos.setVisible(false);
                tfApellidos.setVisible(false);
                lbDireccion.setVisible(false);
                tfDireccion.setVisible(false);
                lbClase.setVisible(false);
                cbClase.setVisible(false);
                lbCorreo.setVisible(false);
                tfEmail.setVisible(false);
                lbNombre.setVisible(false);
                tfNombre.setVisible(false);
                lvAsignaturas.setVisible(false);
                cbAsignaturas.setVisible(false);
                btAdd.setVisible(false);
                break;
        }

    }
    @FXML
    public void myKeyListener(KeyEvent keyEvent) {
        if ((keyEvent.getCode().equals(KeyCode.DELETE) ||
                keyEvent.getCharacter().equals("\r") ||
                keyEvent.getCharacter().equals("\u007F")    //<-- **THIS** is the important one! *****
        )) {
            if (lvAsignaturas.getSelectionModel().getSelectedItem()!=null){
                lvAsignaturas.getItems().remove(lvAsignaturas.getSelectionModel().getSelectedIndex());
            }
        }
            keyEvent.consume();

        }
    protected void loadAlu(Alumno alu){
        tfNombre.setText(alu.getNombreAlumno());
        tfDireccion.setText(alu.getDireccionAlumno());
        tfId.setText(alu.getNiaAlumno());
        tfEmail.setText(alu.getEmailAlumno());
        tfApellidos.setText(alu.getApellidosAlumno());
        tfTelefono.setText(alu.getApellidosAlumno());
        String clase = alu.getClase();
        ObservableList<String> list = cbClase.getItems();
        for (int x = 0;x < list.size();x++){
            if (clase.equals(list.get(x)))
                cbClase.getSelectionModel().select(x);
        }
        lvAsignaturas.getItems().addAll(alu.getAsignaturasAsig().stream().map(Asignatura::getNombreAsignatura).collect(Collectors.toList()));
    }
    protected void loadProf(Profesor pro){
    tfTelefono.setText(pro.getTelefonoProfesor());
    tfApellidos.setText(pro.getApellidoProfesor());
    tfEmail.setText(pro.getEmailProfesor());
    tfNombre.setText(pro.getNombreProfesor());
    tfId.setText(pro.getDniProfesor());
    tfDireccion.setText(pro.getDireccionProfesor());

    }
    protected void loadClase(Clase clase){
        tfId.setText(clase.getId().toString());
        tfNombre.setText(clase.getNombreClase());
        tfEmail.setText(clase.getAula());
        String tutor = clase.getTutorTutor().getNombreProfesor()+"-"+clase.getTutorTutor().getDniProfesor();
        cbClase.getItems().add(tutor);
        cbClase.getSelectionModel().select(cbClase.getItems().size()-1);
    }
    protected void loadAsignatura(Asignatura asig){
        tfId.setText(asig.getId().toString());
        tfTelefono.setText(asig.getNombreAsignatura());
    }

    @FXML
    protected void onlcickAdd(){


        if (lvAsignaturas.getItems().contains(cbAsignaturas.getSelectionModel().getSelectedItem())){
            AlertsUtil.showInfo("Asignatura alredy in list", "","La asignatura ya se encuentra en la lista");
        } else if (cbAsignaturas.getSelectionModel().getSelectedItem()==null) {
            AlertsUtil.showInfo("Debes seleccionar alguna clase", "","Tienes que seleccionar algo");

        } else {
            lvAsignaturas.getItems().add(cbAsignaturas.getSelectionModel().getSelectedItem());
        }
    }
}
