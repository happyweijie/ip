package jimjam.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import jimjam.jimjam.Jimjam;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Jimjam jimjam = new Jimjam();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJimjam(jimjam);  // inject the Jimjam instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
