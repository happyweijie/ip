import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Jimjam {
    private static final String LOGO = "--jimjam";

    public static void main(String[] args) {
        printGreeting();

        // take user input
        Scanner scanner= new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        boolean isRunning = true;
        while (isRunning) {
            String prompt = scanner.nextLine();

            if (prompt.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else if (prompt.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ": " + tasks.get(i));
                }
            } else {
                tasks.add(prompt);
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
