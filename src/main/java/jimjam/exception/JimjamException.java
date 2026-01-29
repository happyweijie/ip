package jimjam.exception;

/**
 * Signals an error specific to the Jimjam application logic.
 * This checked exception is used to handle various issues such as invalid user commands,
 * incorrect date formats, or corrupted storage data.
 */
public class JimjamException extends Exception {
    /**
     * Constructs a new JimjamException with a detailed error message.
     *
     * @param message The specific error message to be displayed to the user.
     */
    public JimjamException(String message) {
        super(message);
    }
}
