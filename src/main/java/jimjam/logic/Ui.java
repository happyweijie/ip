package jimjam.logic;

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
	 * Returns the initial welcome greeting to the user when the application starts.
	 *
	 * @return The welcome message.
	 */
	public String welcomeMessage() {
		return "Hello from Jimjam!\n" +
				"What can I do for you?";
	}

	/**
	 * Returns the goodbye message to the user.
	 *
	 * @return The goodbye message.
	 */
	public String goodbyeMessage() {
		return "Bye. Hope to see you again soon!";
	}

	/**
	 * Returns a String representation of tasks in the search result.
	 * If the list is empty, a notification String will be returned instead.
	 *
	 * @param results The {@link TaskList} containing the search results.
	 * @return The search results displayed as a String.
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
	 * Returns a String representation of tasks in the reminders.
	 * If the list is empty, a notification String will be returned instead.
	 *
	 * @param reminders The {@link TaskList} containing the reminders.
	 * @return The reminders displayed as a String.
	 */
	public String remindersMessage(TaskList reminders) {
		StringBuilder out = new StringBuilder();
		if (reminders.getSize() > 0) {
			out.append("Here are your reminders:\n");
		}

		out.append(this.taskListMessage(reminders));
		return out.toString();
	}

	/**
	 * Returns a String representation of the tasks currently in the provided task list.
	 * If the list is empty, a notification String will be returned instead.
	 *
	 * @param taskList The {@link TaskList} containing the tasks to be displayed.
	 * @return The TaskList displayed as a String.
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
	 * Returns a String confirmation that a task has been successfully added.
	 *
	 * @param task The task that was added.
	 * @param size The new total number of tasks in the list.
	 * @return The message indicated that a task was successfully added.
	 */
	public String addTaskMessage(Task task, int size) {
		return "Got it. I've added:\n" + task + "\n" +
				"Now you have " + size + " tasks.";
	}

	/**
	 * Returns a String confirmation that a task has been successfully removed.
	 *
	 * @param task The task that was removed.
	 * @param size The new total number of tasks remaining in the list.
	 * @return The message indicated that a task was successfully deleted.
	 */
	public String deleteTaskMessage(Task task, int size) {
		return "Got it. I've removed:\n" + task + "\n" +
				"Now you have " + size + " tasks.";
	}

	/**
	 * Returns a String confirmation that a task has been marked as completed.
	 *
	 * @param task The task that was marked done.
	 * @return The message indicated that a task was marked as done.
	 */
	public String markedTaskMessage(Task task) {
		return "Nice! I've marked this task as done:\n" +
				task;
	}

	/**
	 * Returns a String confirmation that a task completion has been undone.
	 *
	 * @param task The task that was unmarked.
	 * @return The message indicated that a task was marked as undone.
	 */
	public String unmarkedTaskMessage(Task task) {
		return "OK, I've marked this task as not done yet:\n" +
				task;
	}

	/**
	 * Returns a String message professing appreciation for monads.
	 *
	 * @return The message about monads.
	 */
	public String monadMessage() {
		return "I \u2661 monads too!!\n" + // Uses Unicode hollow heart
				"Did you know that monads are monoids in the category of endofunctors?";
	}
}
