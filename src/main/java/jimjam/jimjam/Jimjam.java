package jimjam.jimjam;

import jimjam.ai.AiHelper;
import jimjam.exception.ExitException;
import jimjam.exception.JimjamException;

import jimjam.storage.Storage;

import jimjam.task.Task;
import jimjam.task.TaskList;

import jimjam.logic.Command;
import jimjam.logic.Ui;

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
    private final AiHelper aiHelper;

    /**
     * Initializes the Jimjam application components.
     * Loads existing tasks from storage upon startup.
     */
    public Jimjam() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_STORAGE_PATH);
        this.taskList = new TaskList(storage.load());
        this.aiHelper = new AiHelper();

        assert this.taskList.getSize() >= 0 : "list size cannot be negative.";
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

            case TODO -> this.handleTodo(args);

            case DEADLINE -> this.handleDeadline(args);

            case EVENT -> this.handleEvent(args);

            case DELETE -> this.handleDelete(args);

            case FIND -> this.handleFind(args);

            case REMIND -> this.handleRemind(args);

            case HELP -> this.handleHelp();

            case AI ->  this.handleAi(args);

            case MONAD -> this.handleMonad();

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

    private String handleTodo(String args) throws JimjamException {
        Task todo = taskList.addTodo(args);
        return ui.addTaskMessage(todo, taskList.getSize());
    }

    private String handleDeadline(String args) throws JimjamException {
        Task deadline = taskList.addDeadline(args);
        return ui.addTaskMessage(deadline, taskList.getSize());
    }

    private String handleEvent(String args) throws JimjamException {
        Task event = taskList.addEvent(args);
        return ui.addTaskMessage(event, taskList.getSize());
    }

    private String handleDelete(String args) throws JimjamException {
        Task deleted = taskList.deleteTask(args);
        return ui.deleteTaskMessage(deleted, taskList.getSize());
    }

    private String handleFind(String args) throws JimjamException {
        TaskList result = taskList.searchTask(args);
        return ui.searchResultsMessage(result);
    }

    private String handleRemind(String args) throws JimjamException {
        TaskList reminders = taskList.getTasksDueWithin(args);
        return ui.remindersMessage(reminders);
    }

    private String handleHelp() {
        return ui.helpMessage();
    }

    private String handleAi(String userPrompt) throws JimjamException {
        if (userPrompt.isBlank()) {
            throw new JimjamException("Please specify what you would like to ask.");
        }

        String systemPrompt =
                "You are an assistant for a CLI task management app.\n"
                + "Decide whether the user wants:\n"
                + "1) HELP — explanation of how to use the commands in the app\n"
                + "2) COMMAND — generate a valid command the app understands\n\n"
                + "Rules:\n"
                + "- If the user asks how something works, reply:\n"
                + "  HELP: <one sentence>\n"
                + "- If the user wants the app to do something, reply:\n"
                + "  COMMAND: <command only>\n"
                + "- For words like 'tomorrow', 'next monday', use today's date as the reference date\n"
                + "- Do NOT output anything else.\n\n"
                + "Available commands:\n"
                + ui.helpMessage();

        String aiOutput = aiHelper
                .getAiResponse(systemPrompt, userPrompt)
                .aiMessage()
                .text()
                .trim();

        // Route based on prefix
        if (aiOutput.startsWith("COMMAND:")) {
            String command = aiOutput.substring(8).trim();
            System.out.println("Ai command: " + command);
            return this.getResponse(command);
        }

        if (aiOutput.startsWith("HELP:")) {
            return aiOutput.substring(5).trim();
        }

        // fallback if AI messes up
        return aiOutput;
    }

    private String handleMonad() throws JimjamException {
        return ui.monadMessage();
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
