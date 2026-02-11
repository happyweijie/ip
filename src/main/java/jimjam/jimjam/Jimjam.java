package jimjam.jimjam;

import jimjam.exception.ExitException;
import jimjam.exception.JimjamException;
import jimjam.storage.Storage;
import jimjam.task.Task;
import jimjam.task.TaskList;
import jimjam.ui.Command;
import jimjam.ui.Ui;

/**
 * The main entry point for the Jimjam task management application.
 * This class coordinates the user interface, file storage, and task list logic
 * to provide a functioning chatbot experience.
 */
public class Jimjam {
    /** Default path where the task data is saved. */
    private static final String DEFAULT_STORAGE_PATH = "./data/jimjam.txt";

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Initializes the Jimjam application components.
     * Loads existing tasks from storage upon startup.
     */
    public Jimjam() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_STORAGE_PATH);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Interprets and executes the command provided by the user.
     * @param input The raw input string from the user.
     * @return Jimjam's response to the user input.
     * @throws JimjamException If the command is invalid or arguments are missing.
     */
    public String getResponse(String input) throws JimjamException {
        String[] parts = input.split(" ", 2);
        Command command = Command.fromString(parts[0]);
        // additional argument if present
        String args = parts.length > 1 ? parts[1] : "";

        String response = this.executeCommand(command, args);

        // write task list to storage
        this.storage.save(this.taskList.getTasks());
        return response;
    }

    private String executeCommand(Command command, String args)
            throws JimjamException {
        return switch (command) {
        case BYE -> throw new ExitException(this.ui.goodbyeMessage());

        case LIST -> this.ui.taskListMessage(this.taskList);

        case MARK -> this.handleMark(args);

        case UNMARK -> this.handleUnmark(args);

        case TODO -> {
            Task todo = this.taskList.addTodo(args);
            yield this.ui.addTaskMessage(todo, this.taskList.getSize());
        }

        case DEADLINE -> {
            Task deadline = this.taskList.addDeadline(args);
            yield this.ui.addTaskMessage(deadline, this.taskList.getSize());
        }

        case EVENT -> {
            Task event = this.taskList.addEvent(args);
            yield this.ui.addTaskMessage(event, this.taskList.getSize());
        }

        case DELETE -> {
            Task deleted = this.taskList.deleteTask(args);
            yield this.ui.deleteTaskMessage(deleted, this.taskList.getSize());
        }

        case FIND -> {
            TaskList res = this.taskList.searchTask(args);
            yield this.ui.searchResultsMessage(res);
        }

        default -> throw new JimjamException("I don't recognise this command.");
        };
    }

    private String handleMark(String args) throws JimjamException {
        Task marked = this.taskList.updateTaskStatus(args, true);
        return this.ui.markedTaskMessage(marked);
    }

    private String handleUnmark(String args) throws JimjamException {
        Task unmarked = this.taskList.updateTaskStatus(args, false);
        return this.ui.unmarkedTaskMessage(unmarked);
    }

    /**
     * Returns Jimjam's welcome message to the user
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return this.ui.welcomeMessage();
    }
}
