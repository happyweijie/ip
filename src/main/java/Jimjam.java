import java.util.Scanner;

public class Jimjam {
    private static final String DEFAULT_STORAGE_PATH = "./data/jimjam.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Jimjam() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_STORAGE_PATH);
        this.tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        new Jimjam().run();
    }

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
                this.ui.printMessage("Error:" + e.getMessage());
            }

            this.storage.save(this.tasks.getTasks());
        }

        scanner.close();
        this.ui.showGoodbye();
    }

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
            Task unmarked = this.tasks.updateTaskStatus(args, true);
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
            Task deleted = this.tasks.delete(args);
            this.ui.showDeleted(deleted, this.tasks.getSize());
            break;

        default:
            // Unknown command
            throw new JimjamException("I don't recognise this command.");
        }

        return true;
    }
}
