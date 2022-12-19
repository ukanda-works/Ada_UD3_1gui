package es.severo.manuelamoros.persistence.exceptions;

import es.severo.manuelamoros.persistence.util.AlertsUtil;
import javafx.scene.control.Alert;

public class CustomExecption extends Exception {
    public enum CustomType{
        DataBase,
        Any_thing_found,
        unexpected_error,
        Any_thing_slected
    }

    private CustomType type;

    public CustomExecption(String message, CustomType type) {
        super(message);
        this.type = type;
    }

    public void showAsWarring(){
        AlertsUtil.showWarning("Warnign","Warning: "+this.type.toString().replace("_"," "),this.getMessage());
    }
}
