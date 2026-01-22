import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Jimjam {
    private static final String LOGO = "--jimjam";

    public static void main(String[] args) {
        printGreeting();

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        boolean isRunning = true;
        while (isRunning) {
            String input = scanner.nextLine().trim();
            isRunning = handleCommand(input, tasks);
        }

        scanner.close();
        printGoodbye();
    }

    private static boolean handleCommand(String input, List<Task> tasks) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "bye":
                return false;

            case "list":
                listTasks(tasks);
                break;

            case "mark":
                updateTaskStatus(parts, tasks, true);
                break;

            case "unmark":
                updateTaskStatus(parts, tasks, false);
                break;

            case "todo":
                addTodo(args, tasks);
                break;

            case "deadline":
                addDeadline(args, tasks);
                break;

            case "event":
                addEvent(args, tasks);
                break;

            default:
                // Unknown command
                printMessage("Unknown command. Please try again.");
        }

        return true;
    }

    // ---------- Task handlers ----------
    private static void addTodo(String description, List<Task> tasks) {
        Task task = new Todo(description);
        tasks.add(task);
        printAddMessage(task, tasks.size());
    }

    private static void addDeadline(String args, List<Task> tasks) {
        String[] split = args.split(" /by ", 2);
        Task task = new Deadline(split[0], split[1]);
        tasks.add(task);
        printAddMessage(task, tasks.size());
    }

    private static void addEvent(String args, List<Task> tasks) {
        String[] split = args.split(" /from | /to ", 3);
        Task task = new Event(split[0], split[1], split[2]);
        tasks.add(task);
        printAddMessage(task, tasks.size());
    }

    private static void updateTaskStatus(String[] parts,
                                         List<Task> tasks, boolean markDone) {
        int idx = Integer.parseInt(parts[1]) - 1;
        Task task = tasks.get(idx);

        if (markDone) {
            task.markDone();
            printMessage("Nice! I've marked this task as done:\n" + task);
        } else {
            task.unmarkDone();
            printMessage("OK, I've marked this task as not done yet:\n" + task);
        }
    }

    // ---------- UI ----------
    private static void listTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
        System.out.println(LOGO);
    }

    private static void printAddMessage(Task task, int size) {
        // Printed when adding tasks
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LOGO);
    }

    public static void printGreeting() {
        String greeting = "Hello from Jimjam!\n" +
                "What can I do for you?";
        printMessage(greeting);
    }

    public static void printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void printMessage(String message) {
        System.out.println(message);
        System.out.println(LOGO);
    }
}
