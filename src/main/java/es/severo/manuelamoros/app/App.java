package es.severo.manuelamoros.app;

import es.severo.manuelamoros.app.controllers.TableViewController;
import es.severo.manuelamoros.persistence.exceptions.CriticalException;
import es.severo.manuelamoros.persistence.util.AlertsUtil;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/es/severo/manuelamoros/tableView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("GUI proyecto");
            stage.setScene(scene);
            stage.show();
            if (!HibernateUtil.checkValues())
                openConexionSeting(stage.getScene());
            HibernateUtil.tryConexion();
            AlertsUtil.showInfo("Correct conexion","Conexion a la base de datos realizada correctamente");
        } catch (IOException e){
            AlertsUtil.showError("Error al iniciar","Error al mostrar la escena(IOException)",e.getMessage());
        } catch (CriticalException e) {
            e.showError();
            try {
                openConexionSeting(stage.getScene());
            } catch (IOException ex) {
                AlertsUtil.showError("Error al iniciar","Error al mostrar la escena(IOException)",e.getMessage());
            }
        }
    }

    public void openConexionSeting(Scene scene) throws IOException {
            Dialog<ButtonType> d = new Dialog<>();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TableViewController.class.getResource("/es/severo/manuelamoros/accesDialog.fxml"));
            d.initOwner(scene.getWindow());
            d.setTitle("Conection setings");
            d.getDialogPane().setContent(loader.load());
            d.showAndWait();
    }

}