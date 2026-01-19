public class Jimjam {
    private static final String LOGO = "--jimjam";
    private static final String LINE = "_".repeat(60);

    public static void main(String[] args) {
        printGreeting();

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
