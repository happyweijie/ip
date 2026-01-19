public class Jimjam {
    private static final String LOGO = "--jimjam";
    private static final String LINE = "_".repeat(60);

    public static void main(String[] args) {
        printGreeting();

        printGoodbye();
    }

    public static void printGreeting() {
        System.out.println(LINE);

        System.out.println("Hello from Jimjam!");
        System.out.println("What can I do for you?");
        System.out.println(LOGO);

        System.out.println(LINE);
    }

    public static void printGoodbye() {
        System.out.println(LINE);

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LOGO);

        System.out.println(LINE);
    }
}
