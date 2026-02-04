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
     * Entry point for launching the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Jimjam().run();
    }

    /**
     * Starts the main execution loop of the application.
     * Displays a welcome message and continuously processes user input until
     * the "BYE" command is received.
     */
    public void run() {
        this.ui.showWelcome();

        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine().trim();

            // run command
            try {
                isRunning = handleCommand(input);
            } catch (Exception e) {
                this.ui.printMessage(e.getMessage());
            }

            this.storage.save(this.tasks.getTasks());
        }

        scanner.close();
        this.ui.showGoodbye();
    }

    /**
     * Interprets and executes the command provided by the user.
     * @param input The raw input string from the user.
     * @return true to continue the application, false to exit.
     * @throws JimjamException If the command is invalid or arguments are missing.
     */
    private boolean handleCommand(String input)
        throws JimjamException {
        // split input
        String[] parts = input.split(" ", 2);
        // get command
        Command command = Command.fromString(parts[0]);
        // additional argument if present
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case BYE:
            return false;

        case LIST:
            this.ui.showTaskList(this.tasks);
            break;

        case MARK:
            Task marked = this.tasks.updateTaskStatus(args, true);
            this.ui.showMarkedTask(marked);
            break;

        case UNMARK:
            Task unmarked = this.tasks.updateTaskStatus(args, false);
            this.ui.showUnmarkedTask(unmarked);
            break;

        case TODO:
            Task todo = this.tasks.addTodo(args);
            this.ui.showAdded(todo, this.tasks.getSize());
            break;

        case DEADLINE:
            Task deadline = this.tasks.addDeadline(args);
            this.ui.showAdded(deadline, this.tasks.getSize());
            break;

        case EVENT:
            Task event = this.tasks.addEvent(args);
            this.ui.showAdded(event, this.tasks.getSize());
            break;

        case DELETE:
            Task deleted = this.tasks.deleteTask(args);
            this.ui.showDeleted(deleted, this.tasks.getSize());
            break;

		case FIND:
			TaskList res = this.tasks.searchTasks(args);
			this.ui.showSearchResults(res);
			break;

        default:
            // Unknown command
            throw new JimjamException("I don't recognise this command.");
        }

        return true;
    }
}
