package jimjam.ui;

import jimjam.task.Task;
import jimjam.task.TaskList;

import java.util.List;

/**
 * Handles the user interface of the Jimjam application.
 * This class is responsible for interacting with the user by printing
 * messages, task lists, and operation confirmations to the standard output.
 */
public class Ui {
	private static final String LOGO = "--jimjam";

	/**
	 * Prints a message to the console, followed by the Jimjam application logo.
	 *
	 * @param message The string to be displayed to the user.
	 */
	public void printMessage(String message) {
		System.out.println(message + "\n" + LOGO);
	}

	/**
	 * Displays the initial welcome greeting to the user when the application starts.
	 */
	public void showWelcome() {
		printMessage("Hello from Jimjam!\n" +
				"What can I do for you?");
	}

	/**
	 * Displays the exit message to the user.
	 */
	public void showGoodbye() {
		printMessage("Bye. Hope to see you again soon!");
	}

	/**
	 * Prints all tasks currently in the provided task list.
	 * If the list is empty, a notification message is shown instead.
	 *
	 * @param taskList The {@link TaskList} containing the tasks to be displayed.
	 */
	public void showTaskList(TaskList taskList) {
		// Handle empty taskList
		if (taskList.getSize() == 0) {
			printMessage("No tasks to show!");
			return;
		}

		List<Task> tasks = taskList.getTasks();
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println((i + 1) + ": " + tasks.get(i));
		}
		System.out.println(LOGO);
	}

	/**
	 * Displays a confirmation that a task has been successfully added.
	 *
	 * @param task The task that was added.
	 * @param size The new total number of tasks in the list.
	 */
	public void showAdded(Task task, int size) {
		printMessage("Got it. I've added:\n" + task + "\n" +
				"Now you have " + size + " tasks.");
	}

	/**
	 * Displays a confirmation that a task has been successfully removed.
	 *
	 * @param task The task that was removed.
	 * @param size The new total number of tasks remaining in the list.
	 */
	public void showDeleted(Task task, int size) {
		printMessage("Got it. I've removed:\n" + task + "\n" +
				"Now you have " + size + " tasks.");
	}

	/**
	 * Displays a confirmation that a task has been marked as completed.
	 *
	 * @param task The task that was marked done.
	 */
	public void showMarkedTask(Task task) {
		printMessage("Nice! I've marked this task as done:\n" +
				task);
	}

	/**
	 * Displays a confirmation that a task completion has been undone.
	 *
	 * @param task The task that was unmarked.
	 */
	public void showUnmarkedTask(Task task) {
		printMessage("OK, I've marked this task as not done yet:\n" +
				task);
	}
}
