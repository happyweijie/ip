package jimjam.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import jimjam.jimjam.Jimjam;

/**
 * The main GUI entry point for Jimjam.
 * <p>
 * This class initializes and launches the JavaFX user interface using FXML.
 * It loads the main window layout from {@code MainWindow.fxml}, sets up the
 * primary stage and scene, and injects the {@link Jimjam} instance into the
 * {@link MainWindow} controller.
 * </p>
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
            stage.setTitle("Jimjam");
            fxmlLoader.<MainWindow>getController().setJimjam(jimjam);  // inject the Jimjam instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
