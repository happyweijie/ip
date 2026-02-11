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
    private final TaskList tasks;

    /**
     * Initializes the Jimjam application components.
     * Loads existing tasks from storage upon startup.
     */
    public Jimjam() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_STORAGE_PATH);
        this.tasks = new TaskList(storage.load());

        assert this.tasks.getSize() >= 0 : "list size cannot be negative.";
    }

    /**
     * Interprets and executes the command provided by the user.
     * @param input The raw input string from the user.
     * @return Jimjam's response to the user input.
     * @throws JimjamException If the command is invalid or arguments are missing.
     */
    public String getResponse(String input) throws JimjamException {
        String[] parts = input.split(" ", 2);
        // get command
        Command command = Command.fromString(parts[0]);
        // additional argument if present
        String args = parts.length > 1 ? parts[1] : "";

        String response = "";
        switch (command) {
        case BYE:
            throw new ExitException(this.ui.goodbyeMessage());

        case LIST:
            response = this.ui.taskListMessage(this.tasks);
            break;

        case MARK:
            Task marked = this.tasks.updateTaskStatus(args, true);
            response = this.ui.markedTaskMessage(marked);
            break;

        case UNMARK:
            Task unmarked = this.tasks.updateTaskStatus(args, false);
            response = this.ui.unmarkedTaskMessage(unmarked);
            break;

        case TODO:
            Task todo = this.tasks.addTodo(args);
            response = this.ui.addTaskMessage(todo, this.tasks.getSize());
            break;

        case DEADLINE:
            Task deadline = this.tasks.addDeadline(args);
            response = this.ui.addTaskMessage(deadline, this.tasks.getSize());
            break;

        case EVENT:
            Task event = this.tasks.addEvent(args);
            response = this.ui.addTaskMessage(event, this.tasks.getSize());
            break;

        case DELETE:
            Task deleted = this.tasks.deleteTask(args);
            response = this.ui.deleteTaskMessage(deleted, this.tasks.getSize());
            break;

        case FIND:
            TaskList res = this.tasks.searchTasks(args);
            response = this.ui.searchResultsMessage(res);
            break;

        default:
            // Unknown command
            throw new JimjamException("I don't recognise this command.");
        }

        // write task list to storage
        this.storage.save(this.tasks.getTasks());
        // return response
        return response;
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
