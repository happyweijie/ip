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

	/**
	 * Displays the initial welcome greeting to the user when the application starts.
	 */
	public String welcomeMessage() {
		return "Hello from Jimjam!\n" +
				"What can I do for you?";
	}

	/**
	 * Displays the exit message to the user.
	 */
	public String goodbyeMessage() {
		return "Bye. Hope to see you again soon!";
	}

	/**
	 * Prints all tasks in the search result.
	 * If the list is empty, a notification message is shown instead.
	 *
	 * @param results The {@link TaskList} containing the search results.
	 */
	public String searchResultsMessage(TaskList results) {
		StringBuilder out = new StringBuilder();
		if (results.getSize() > 0) {
			out.append("Here are the matching tasks in your list:\n");
		}

		out.append(this.taskListMessage(results));
		return out.toString();
	}

	/**
	 * Prints all tasks currently in the provided task list.
	 * If the list is empty, a notification message is shown instead.
	 *
	 * @param taskList The {@link TaskList} containing the tasks to be displayed.
	 */
	public String taskListMessage(TaskList taskList) {
		// Handle empty taskList
		if (taskList.getSize() == 0) {
			return "No matching tasks found!";
		}

		List<Task> tasks = taskList.getTasks();
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < tasks.size(); i++) {
			out.append(String.format("%d: %s\n",
					i + 1,
					tasks.get(i)));
		}

		return out.toString().stripTrailing();
	}

	/**
	 * Displays a confirmation that a task has been successfully added.
	 *
	 * @param task The task that was added.
	 * @param size The new total number of tasks in the list.
	 */
	public String addTaskMessage(Task task, int size) {
		return "Got it. I've added:\n" + task + "\n" +
				"Now you have " + size + " tasks.";
	}

	/**
	 * Displays a confirmation that a task has been successfully removed.
	 *
	 * @param task The task that was removed.
	 * @param size The new total number of tasks remaining in the list.
	 */
	public String deleteTaskMessage(Task task, int size) {
		return "Got it. I've removed:\n" + task + "\n" +
				"Now you have " + size + " tasks.";
	}

	/**
	 * Displays a confirmation that a task has been marked as completed.
	 *
	 * @param task The task that was marked done.
	 */
	public String markedTaskMessage(Task task) {
		return "Nice! I've marked this task as done:\n" +
				task;
	}

	/**
	 * Displays a confirmation that a task completion has been undone.
	 *
	 * @param task The task that was unmarked.
	 */
	public String unmarkedTaskMessage(Task task) {
		return "OK, I've marked this task as not done yet:\n" +
				task;
	}
}
