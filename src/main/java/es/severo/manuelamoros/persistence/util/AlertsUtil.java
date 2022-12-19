package es.severo.manuelamoros.persistence.util;

import javafx.scene.control.Alert;

public class AlertsUtil {

    public static void showInfo(String titulo, String header){
        showInfo(titulo,header,"");
    }
    public static void showInfo(String titulo, String header,String detalles){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(detalles);
        alert.showAndWait();
    }

    public static void showWarning(String titulo, String header, String detalles){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(detalles);
        alert.showAndWait();

    }
    public static void showError(String titulo, String header, String detalles){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(detalles);
        alert.showAndWait();

    }

}
