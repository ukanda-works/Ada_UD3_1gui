package es.severo.manuelamoros.app.controllers;

import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.util.DialogDB;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

                cbClase.getItems().addAll(DialogDB.getAllClase());
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

}
