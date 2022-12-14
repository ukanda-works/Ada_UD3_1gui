package es.severo.manuelamoros.app.controllers;

import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.dao.AsignaturaDAOImp;
import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.util.DialogDB;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

public class TableViewController<T> {

    private AlumnoDAOImp alumnoDAOImp = new AlumnoDAOImp();
    private ProfesorDAOImp profesorDAOImp = new ProfesorDAOImp();
    private ClaseDAOImp claseDAOImp = new ClaseDAOImp();
    private AsignaturaDAOImp asignaturaDAOImp =new AsignaturaDAOImp();

    @FXML
    private TableView tvPrincipal;
    @FXML
    private Button btFilter;
    @FXML
    private ComboBox cbTablas;
    @FXML
    private ComboBox cbFiltrar;
    @FXML
    private TextField tfTermino;

    @FXML
    public void initialize() throws IOException{
        cbTablas.getItems().add("Alumnos");
        cbTablas.getItems().add("Profesores");
        cbTablas.getItems().add("Clase");
        cbTablas.getItems().add("Asignatura");
    }

    @FXML
    protected void onClickAdd(){
        try {
            //lanzar excepcion personalizada si no hay nada seleccionado
            Dialog<ButtonType> d = new Dialog<>();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TableViewController.class.getResource("/es/severo/manuelamoros/dgFormulario.fxml"));
            d.initOwner(tvPrincipal.getScene().getWindow());
            d.setResizable(true);


            d.setTitle("Añadir nueva entrada a: "+cbTablas.getItems().get(cbTablas.getSelectionModel().getSelectedIndex()));
            d.getDialogPane().setContent(loader.load());
            d.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            DgFormularioController controller = loader.getController();
            controller.load(cbTablas.getSelectionModel().getSelectedIndex());
            Optional<ButtonType> response = d.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.APPLY){
                switch (cbTablas.getSelectionModel().getSelectedIndex()){
                    case 0:
                        DialogDB.addAlumno(controller);
                        break;
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClickcbTablas(){
        int a = cbTablas.getSelectionModel().getSelectedIndex();
        tvPrincipal.getItems().clear();
        tvPrincipal.getColumns().clear();
        switch (a){
            case 0:

                TableColumn alunNia = new TableColumn<>("NIA");
                alunNia.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn aluNombre = new TableColumn<>("Nombre");
                aluNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAlumno"));
                TableColumn aluApellidos = new TableColumn<>("Apellidos");
                aluApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidosAlumno"));
                TableColumn aluClase = new TableColumn<>("Clase");
                aluClase.setCellValueFactory(new PropertyValueFactory<>("clase"));
                TableColumn aluTelefono = new TableColumn<>("Telefono");
                aluTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoAlumno"));
                TableColumn aluEmail = new TableColumn<>("Email");
                aluEmail.setCellValueFactory(new PropertyValueFactory<>("emailAlumno"));
                TableColumn aluDireccion = new TableColumn<>("Direccion");
                aluDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionAlumno"));

                tvPrincipal.getColumns().addAll(alunNia, aluNombre, aluApellidos, aluClase, aluTelefono, aluEmail, aluDireccion);

                alumnoDAOImp.findAll().forEach(alumno -> tvPrincipal.getItems().add(alumno));

                break;
            case 1:

                TableColumn profNia = new TableColumn<>("NIA");
                profNia.setCellValueFactory(new PropertyValueFactory<>("id"));
                TableColumn proNombre = new TableColumn<>("Nombre");
                proNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));
                TableColumn profApellidos = new TableColumn<>("Apellidos");
                profApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidoProfesor"));
                TableColumn profTelefono = new TableColumn<>("Telefono");
                profTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoProfesor"));
                TableColumn profEmail = new TableColumn<>("Email");
                profEmail.setCellValueFactory(new PropertyValueFactory<>("emailProfesor"));
                TableColumn profDireccion = new TableColumn<>("Direccion");
                profDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionProfesor"));

                tvPrincipal.getColumns().addAll(profNia, proNombre, profApellidos, profTelefono, profEmail,profDireccion);

                profesorDAOImp.findAll().forEach(profesor ->tvPrincipal.getItems().add(profesor));
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    @FXML
    protected void onClickAcercaDe(){
        try{
            Dialog<ButtonType> d = new Dialog<>();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TableViewController.class.getResource("/es/severo/manuelamoros/acercaDe.fxml"));
            d.initOwner(tvPrincipal.getScene().getWindow());
            d.setResizable(true);
            d.setTitle("Acerca de ");
            d.getDialogPane().setContent(loader.load());
            d.getDialogPane().getButtonTypes().add(ButtonType.YES);
            d.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClickSalir(){
        Platform.exit();
    }
}
