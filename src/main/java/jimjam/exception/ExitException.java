package jimjam.exception;

/**
 * Signals that the application should terminate after displaying
 * a final response to the user.
 * <p>
 * This exception is thrown when the user issues the {@code bye} command.
 * The exception message contains the goodbye message to be shown to the user.
 * <p>
 * The actual termination of the application is handled by the UI layer
 * (e.g. JavaFX), not by the core logic.
 */
public class ExitException extends JimjamException {
    /**
     * Constructs a new ExitException with a detailed error message.
     *
     * @param message The specific error message to be displayed to the user.
     */
    public ExitException(String message) {
        super(message);
    }
}

