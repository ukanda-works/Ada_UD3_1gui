package es.severo.manuelamoros.app.controllers;

import es.severo.manuelamoros.persistence.dao.AlumnoDAOImp;
import es.severo.manuelamoros.persistence.dao.AsignaturaDAOImp;
import es.severo.manuelamoros.persistence.dao.ClaseDAOImp;
import es.severo.manuelamoros.persistence.dao.ProfesorDAOImp;
import es.severo.manuelamoros.persistence.entity.Alumno;
import es.severo.manuelamoros.persistence.entity.Asignatura;
import es.severo.manuelamoros.persistence.entity.Clase;
import es.severo.manuelamoros.persistence.entity.Profesor;
import es.severo.manuelamoros.persistence.exceptions.CustomExecption;
import es.severo.manuelamoros.persistence.util.DialogDB;
import es.severo.manuelamoros.persistence.util.TableViewDBUtil;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private ComboBox<String> cbFiltrar;
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
                        mostrarAlumnos();
                        break;
                    case 1:
                        DialogDB.addProfesor(controller);
                        mostrarProfesores();
                        break;
                    case 2:
                        DialogDB.addClase(controller);
                        mostrarClases();
                        break;
                    case 3:
                        DialogDB.addAsignatura(controller);
                        mostrarAsginaturas();
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
                mostrarAlumnos();
                break;
            case 1:
                mostrarProfesores();
                break;
            case 2:
                mostrarClases();
                break;
            case 3:
                mostrarAsginaturas();
                break;
        }
    }
    private void mostrarAlumnos(){
        tvPrincipal.getItems().clear();
        tvPrincipal.getColumns().clear();
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
        TableColumn aluAsignaturas = new TableColumn<>("Asginaturas");
        aluAsignaturas.setCellValueFactory(new PropertyValueFactory<>("asignaturas"));
        tvPrincipal.getColumns().addAll(alunNia, aluNombre, aluApellidos, aluClase, aluTelefono, aluEmail, aluDireccion,aluAsignaturas);
        alumnoDAOImp.findAll().forEach(alumno -> tvPrincipal.getItems().add(alumno));

        //le damos valores al combo box
        List<String> list = new ArrayList<>();
        list.add("NIA");
        list.add("Nombre");
        list.add("Apellido");
        list.add("Direccion");
        cbFiltrar.getItems().clear();
        cbFiltrar.getItems().addAll(list);
    }
    private void mostrarProfesores(){
        //le damos valores al table view
        tvPrincipal.getItems().clear();
        tvPrincipal.getColumns().clear();
        TableColumn profNia = new TableColumn<>("DNI");
        profNia.setCellValueFactory(new PropertyValueFactory<>("dniProfesor"));
        TableColumn proNombre = new TableColumn<>("Nombre");
        proNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));
        TableColumn profApellidos = new TableColumn<>("Apellidos");
        profApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidoProfesor"));
        TableColumn profTelefono = new TableColumn<Profesor,String>("Telefono");
        profTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoProfesor"));
        TableColumn profEmail = new TableColumn<>("Email");
        profEmail.setCellValueFactory(new PropertyValueFactory<>("emailProfesor"));
        TableColumn profDireccion = new TableColumn<>("Direccion");
        profDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionProfesor"));

        tvPrincipal.getColumns().addAll(profNia, proNombre, profApellidos, profTelefono, profEmail,profDireccion);
        profesorDAOImp.findAll().forEach(profesor ->tvPrincipal.getItems().add(profesor));

        //le damos valores al combo box
        List<String> list = new ArrayList<>();
        list.add("DNI");
        list.add("Nombre");
        list.add("Apellido");
        list.add("Direccion");
        cbFiltrar.getItems().clear();
        cbFiltrar.getItems().addAll(list);
    }
    private void mostrarClases(){
        tvPrincipal.getItems().clear();
        tvPrincipal.getColumns().clear();
        TableColumn clasNombre = new TableColumn<>("Nombre");
        clasNombre.setCellValueFactory(new PropertyValueFactory<>("nombreClase"));
        TableColumn clasAula = new TableColumn<>("Aula");
        clasAula.setCellValueFactory(new PropertyValueFactory<>("aula"));
        TableColumn clasTutor = new TableColumn<>("Tutor");
        clasTutor.setCellValueFactory(new PropertyValueFactory<>("tutor"));
        tvPrincipal.getColumns().addAll(clasNombre,clasAula,clasTutor);
        claseDAOImp.findAll().forEach(clase -> tvPrincipal.getItems().add(clase));

        //le damos valores al combo box
        List<String> list = new ArrayList<>();
        list.add("Nombre de clase");
        list.add("Aula");
        list.add("Tutor (Dni)");
        cbFiltrar.getItems().clear();
        cbFiltrar.getItems().addAll(list);
    }
    private void mostrarAsginaturas(){
        tvPrincipal.getItems().clear();
        tvPrincipal.getColumns().clear();
        TableColumn asigId = new TableColumn<>("Id");
        asigId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn asigNombre = new TableColumn<>("Nombre");
        asigNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAsignatura"));
        tvPrincipal.getColumns().addAll(asigId,asigNombre);
        asignaturaDAOImp.findAll().forEach(asignatura -> tvPrincipal.getItems().add(asignatura));

        List<String> list = new ArrayList<>();
        list.add("Nombre asignatura");
        cbFiltrar.getItems().clear();
        cbFiltrar.getItems().addAll(list);
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
    protected void onClickDesFiltrar(){
        onClickcbTablas();
    }
    @FXML
    protected void onClickSalir(){
        Platform.exit();
    }

    @FXML
    protected void onClickBorrar() throws CustomExecption {
        try {
        if (!tvPrincipal.getSelectionModel().isEmpty()){
            ObservableList selectedItems= tvPrincipal.getSelectionModel().getSelectedItems();
            int selected = cbTablas.getSelectionModel().getSelectedIndex();
            switch (selected){
                case 0:
                    selectedItems.stream().forEach(a -> TableViewDBUtil.deleteAlu((Alumno) a));
                    mostrarAlumnos();
                    break;
                case 1:
                    selectedItems.stream().forEach(a-> TableViewDBUtil.deleteProf((Profesor) a));
                    mostrarProfesores();
                    break;
                case 2:
                    selectedItems.stream().forEach(a-> TableViewDBUtil.deleteClase((Clase) a));
                    mostrarClases();
                    break;
                case  3:
                    selectedItems.stream().forEach(a-> TableViewDBUtil.deleteAsig((Asignatura) a));
                    mostrarAsginaturas();
                    break;
                default:
                    throw new CustomExecption("No hay ningun tipo seleccionado", CustomExecption.CustomType.Any_thing_slected);

            }
        }else {
            throw new CustomExecption("No hay nada seleccionado", CustomExecption.CustomType.Any_thing_slected);
        }
        }catch (CustomExecption e){
            e.showAsWarring();
        }
    }

    @FXML
    protected void onClickEdit(){
        try {
            ObservableList selectedItems= tvPrincipal.getSelectionModel().getSelectedItems();
            if (selectedItems.size() == 0)
                throw new CustomExecption("No hay nada seleccionado", CustomExecption.CustomType.Any_thing_slected);
            //lanzar excepcion personalizada si no hay nada seleccionado
            Dialog<ButtonType> d = new Dialog<>();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TableViewController.class.getResource("/es/severo/manuelamoros/dgFormulario.fxml"));
            d.initOwner(tvPrincipal.getScene().getWindow());
            d.setResizable(true);

            d.setTitle("Editar "+cbTablas.getItems().get(cbTablas.getSelectionModel().getSelectedIndex()));
            d.getDialogPane().setContent(loader.load());
            d.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            DgFormularioController controller = loader.getController();
            controller.load(cbTablas.getSelectionModel().getSelectedIndex());
            switch (cbTablas.getSelectionModel().getSelectedIndex()){
                case 0:
                    controller.loadAlu((Alumno) selectedItems.get(0));
                    break;
                case 1:
                    controller.loadProf((Profesor)  selectedItems.get(0));
                    break;
                case 2:
                    controller.loadClase((Clase)  selectedItems.get(0));
                    break;
                case 3:
                    controller.loadAsignatura((Asignatura)  selectedItems.get(0));
                    break;
            }
            Optional<ButtonType> response = d.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.APPLY){
                switch (cbTablas.getSelectionModel().getSelectedIndex()){
                    case 0:
                        DialogDB.updateAlumno(controller);
                        mostrarAlumnos();
                        break;
                    case 1:
                        DialogDB.updateProfesor(controller);
                        mostrarProfesores();
                        break;
                    case 2:
                        DialogDB.updateClase(controller);
                        mostrarClases();
                        break;
                    case 3:
                        DialogDB.updateAsignatura(controller);
                        mostrarAsginaturas();
                        break;
                }
            }
            d.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }catch (CustomExecption e){
            e.showAsWarring();
        }
    }

    @FXML
    protected void onClickFiltrar(){
        try {
            String selectedFilter = cbFiltrar.getSelectionModel().getSelectedItem();
            if (selectedFilter==null||tfTermino.getText()==null)
                throw new CustomExecption("No hay ningun campo seleccionado para filtrar", CustomExecption.CustomType.Any_thing_slected);
            int selected = cbTablas.getSelectionModel().getSelectedIndex();
            switch (selected){
                case 0:
                    tvPrincipal.getItems().clear();
                    List<Alumno> listAlumno = TableViewDBUtil.filtrarAlu(selectedFilter,tfTermino.getText());
                    if (listAlumno.isEmpty())
                        throw new CustomExecption("No hay ninguna entidad que coincida con tu busqueda", CustomExecption.CustomType.Any_thing_found);
                    tvPrincipal.getItems().addAll(listAlumno);
                    break;
                case 1:
                    tvPrincipal.getItems().clear();
                    List<Profesor> listProfe = TableViewDBUtil.filtrarProfesor(selectedFilter,tfTermino.getText());
                    if (listProfe.isEmpty())
                        throw new CustomExecption("No hay ninguna entidad que coincida con tu busqueda", CustomExecption.CustomType.Any_thing_found);
                    tvPrincipal.getItems().addAll(listProfe);
                    break;
                case 2:
                    tvPrincipal.getItems().clear();
                    List<Clase> listClase = TableViewDBUtil.filtrarClase(selectedFilter,tfTermino.getText());
                    if (listClase.isEmpty())
                        throw new CustomExecption("No hay ninguna entidad que coincida con tu busqueda", CustomExecption.CustomType.Any_thing_found);
                    tvPrincipal.getItems().addAll(listClase);
                    break;
                case 3:
                    tvPrincipal.getItems().clear();
                    List<Asignatura> listAsig = TableViewDBUtil.filtrarAsig(selectedFilter,tfTermino.getText());
                    if (listAsig.isEmpty())
                        throw new CustomExecption("No hay ninguna entidad que coincida con tu busqueda", CustomExecption.CustomType.Any_thing_found);
                    tvPrincipal.getItems().addAll(listAsig);
                    break;
                default:
                    throw new CustomExecption("No hay ninguna tabla seleccionada", CustomExecption.CustomType.Any_thing_slected);
            }

        }catch (CustomExecption e){
            e.showAsWarring();
        }
    }
}
