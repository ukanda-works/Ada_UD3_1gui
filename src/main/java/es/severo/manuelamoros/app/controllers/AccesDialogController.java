package es.severo.manuelamoros.app.controllers;

import es.severo.manuelamoros.persistence.exceptions.CriticalException;
import es.severo.manuelamoros.persistence.util.AlertsUtil;
import es.severo.manuelamoros.persistence.util.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccesDialogController {
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField tfPass;

    @FXML
    private void onClickClose(){
        Stage stage = (Stage) tfPass.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    private void setValues(){
        String user = tfUser.getText();
        String pass = tfPass.getText();
        try {
            HibernateUtil.setConectionsSeting(user,pass);
        } catch (CriticalException e) {
            e.showErrorAndClose();
        }
    }

    @FXML
    private void onClickAcept(){
        setValues();
        AlertsUtil.showInfo("Setings","","Configuracion cargada correctamente, vuelva a ejecutar el programa");
        System.exit(0);
    }

}
