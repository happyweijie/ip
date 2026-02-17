package jimjam.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import jimjam.exception.ExitException;
import jimjam.exception.JimjamException;
import jimjam.jimjam.Jimjam;

/**
 * Controller for the main GUI window.
 * <p>
 * Handles user interactions, displays dialog boxes, and coordinates
 * communication between the UI and the {@link Jimjam} logic.
 * </p>
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jimjam jimjam;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image jimjamImage = new Image(this.getClass().getResourceAsStream("/images/jimjam.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@link Jimjam} instance into the controller and
     * displays the welcome message.
     *
     * @param jimjam the Jimjam logic component
     */
    public void setJimjam(Jimjam jimjam) {
        this.jimjam = jimjam;

        // Add welcome message
        String welcome = jimjam.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getJimjamDialog(welcome, jimjamImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        String response;
        try {
            response = jimjam.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJimjamDialog(response, jimjamImage)
            );
        } catch (ExitException e) {
            // Exit program if the user types bye
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJimjamDialog(e.getMessage(), jimjamImage)
            );

            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        } catch (JimjamException e) {
            response = "Error: " + e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJimjamDialog(response, jimjamImage)
            );
        }
        userInput.clear();
    }
}
