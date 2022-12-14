package es.severo.manuelamoros;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("tableView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GUI proyecto");
        stage.setScene(scene);
        stage.show();
    }
}
