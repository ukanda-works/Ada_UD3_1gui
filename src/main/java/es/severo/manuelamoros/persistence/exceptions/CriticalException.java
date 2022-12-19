package es.severo.manuelamoros.persistence.exceptions;

import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;

import java.util.Optional;

public class CriticalException extends Exception{

    private CriticalType type;
    private String header;
public enum CriticalType{
        Database,
        Hibernate

    }

    public CriticalException(String message, String header, CriticalType type) {
        super(message);
        this.header = header;
        this.type = type;
    }


    public void showErrorAndClose(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Critical "+type+" Exeption");
        alert.setHeaderText(header);
        alert.setContentText(getMessage());
        alert.showAndWait();
        System.exit(0);
    }
    public void showError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Critical "+type+" Exeption");
        alert.setHeaderText(header);
        alert.setContentText(getMessage());
        alert.showAndWait();
    }

    public void setType(CriticalType type) {
        this.type = type;
    }
}
