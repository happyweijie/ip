public class Ui {
	private static final String LOGO = "--jimjam";

	public void printMessage(String message) {
		System.out.println(message + "\n" + LOGO);
	}

	public void showWelcome() {
		printMessage("Hello from Jimjam!\n" +
				"What can I do for you?");
	}

	public void showGoodbye() {
		printMessage("Bye. Hope to see you again soon!");
	}

	public void showTaskList(TaskList taskList) {
		List<Task> tasks = taskList.getTasks();
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println((i + 1) + ": " + tasks.get(i));
		}
		System.out.println(LOGO);
	}

	public void showAdded(Task task, int size) {
		printMessage("Got it. I've added:\n" + task + "\n" +
				"Now you have " + size + " tasks.");
	}

	public void showDeleted(Task task, int size) {
		printMessage("Got it. I've removed:\n" + task + "\n" +
				"Now you have " + size + " tasks.");
	}
}
