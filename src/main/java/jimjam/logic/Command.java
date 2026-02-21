package jimjam.logic;

import jimjam.exception.JimjamException;

/**
 * Represents the valid commands that the Jimjam chatbot can execute.
 * This enum provides a way to map user input strings to internal logic.
 */
public enum Command {
    /** Terminates the application. */
    BYE,
    /** Displays all tasks in the current list. */
    LIST,
    /** Marks a specific task as completed. */
    MARK,
    /** Marks a specific task as incomplete. */
    UNMARK,
    /** Creates a simple task without a date. */
    TODO,
    /** Creates a task with a specific deadline. */
    DEADLINE,
    /** Creates a task with a start and end time. */
    EVENT,
    /** Removes a task from the list. */
    DELETE,
    /** Search for tasks containing a query as substring */
    FIND,
    /** Search for upcoming tasks */
    REMIND,
    /** Help */
    HELP,
    /** Easter Egg: Profess love for monads */
    MONAD;

    /**
     * Translates a string input into a Command.
     * The input is case-insensitive (e.g., "todo" or "TODO" will both work).
     *
     * @param input The raw command string from the user.
     * @return The corresponding Command enum constant.
     * @throws JimjamException If the input does not match any valid command.
     */
    public static Command fromString(String input) throws JimjamException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JimjamException("I don't recognise this command: " + input);
        }
    }
}