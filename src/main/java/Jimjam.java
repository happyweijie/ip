import java.util.Scanner;

public class Jimjam {
    private static final String LOGO = "--jimjam";
    private static final String LINE = "_".repeat(60);

    public static void main(String[] args) {
        printGreeting();

        // take user input
        Scanner scanner= new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String prompt = scanner.nextLine();

            if (prompt.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {
                printMessage(prompt);
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
        System.out.println(LINE);

        System.out.println(message);

        System.out.println(LOGO);
        System.out.println(LINE);
    }
}
