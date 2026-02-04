package jimjam.jimjam;

import jimjam.exception.JimjamException;
import jimjam.storage.Storage;
import jimjam.task.Task;
import jimjam.task.TaskList;
import jimjam.ui.Command;
import jimjam.ui.Ui;

import java.util.List;
import java.util.Scanner;

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

        switch (command) {
        case BYE:
            return this.ui.goodbyeMessage();

        case LIST:
            return this.ui.taskListMessage(this.tasks);

        case MARK:
            Task marked = this.tasks.updateTaskStatus(args, true);
            return this.ui.markedTaskMessage(marked);

        case UNMARK:
            Task unmarked = this.tasks.updateTaskStatus(args, false);
            return this.ui.unmarkedTaskMessage(unmarked);

        case TODO:
            Task todo = this.tasks.addTodo(args);
            return this.ui.addTaskMessage(todo, this.tasks.getSize());

        case DEADLINE:
            Task deadline = this.tasks.addDeadline(args);
            return this.ui.addTaskMessage(deadline, this.tasks.getSize());

        case EVENT:
            Task event = this.tasks.addEvent(args);
            return this.ui.addTaskMessage(event, this.tasks.getSize());

        case DELETE:
            Task deleted = this.tasks.deleteTask(args);
            return this.ui.deleteTaskMessage(deleted, this.tasks.getSize());

        case FIND:
            TaskList res = this.tasks.searchTasks(args);
            return this.ui.searchResultsMessage(res);

        default:
            // Unknown command
            throw new JimjamException("I don't recognise this command.");
        }
    }
}
