import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Jimjam {
    private static final String LOGO = "--jimjam";

    public static void main(String[] args) {
        printGreeting();

        // take user input
        Scanner scanner= new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        boolean isRunning = true;
        while (isRunning) {
            String[] parts = scanner.nextLine()
                                    .toLowerCase()
                                    .split(" ");
            String prompt = parts[0];

            // exit
            if (prompt.equals("bye")) {
                isRunning = false;
            } else if (prompt.equals("list")) { // list tasks
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ": " + tasks.get(i));
                }
            } else if (prompt.equals("mark")) { // mark
                int idx = Integer.parseInt(parts[1]) - 1;
                Task task  = tasks.get(idx);
                task.markDone();

                printMessage("Nice! I've marked this task as done:\n" +
                        task);
            } else if (prompt.equals("unmark")) { // unmark
                int idx = Integer.parseInt(parts[1]) - 1;
                Task task  = tasks.get(idx);
                task.unmarkDone();

                printMessage("OK, I've marked this task as not done yet:\n" +
                        task);
            } else { // add task
                tasks.add(new Task(prompt));
                printMessage("added: " + prompt);
            }
        }

        scanner.close();
        printGoodbye();
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
