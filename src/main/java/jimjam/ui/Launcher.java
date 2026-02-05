package jimjam.ui;

import javafx.application.Application;

/**
 * A launcher class used to start the JavaFX application.
 * <p>
 * This class serves as an entry point to launch the {@link Main} JavaFX
 * application by delegating to {@link javafx.application.Application#launch(Class, String...)}.
 * It exists to work around JavaFX classpath and module system issues that may
 * occur when launching the application directly.
 * </p>
 */
public class Launcher {

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

