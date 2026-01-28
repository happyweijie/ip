package jimjam.ui;

import jimjam.exception.JimjamException;

public enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    public static Command fromString(String input) throws JimjamException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JimjamException("I don't recognise this command.");
        }
    }
}