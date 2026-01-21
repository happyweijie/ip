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
        String[] parts = input.split(" ");
        String command = parts[0].toLowerCase();

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

            default:
                addTask(input, tasks);
        }
        return true;
    }

    private static void listTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ": " + tasks.get(i));
        }
        System.out.println(LOGO);
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

    private static void addTask(String input, List<Task> tasks) {
        tasks.add(new Task(input));
        printMessage("added: " + input);
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
