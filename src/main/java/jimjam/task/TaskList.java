package jimjam.task;

import jimjam.exception.JimjamException;
import jimjam.parser.Parser;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Manages a list of tasks and provides operations to add, delete,
 * and update tasks in the list.
 */
public class TaskList {
	private List<Task> tasks;

	/**
	 * Creates a task list backed by the given list of tasks.
	 *
	 * @param tasks Initial list of tasks
	 */
	public TaskList(List<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Creates an empty task list.
	 */
	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	/**
	 * Adds the given task to the task list.
	 *
	 * @param task Task to be added
	 * @return The task that was added
	 */
	protected Task addTask(Task task) {
		this.tasks.add(task);
		return task;
	}

	/**
	 * Adds a to-do task with the given description.
	 *
	 * @param description Description of the to-do task
	 * @return The newly added task
	 * @throws JimjamException If the description is empty
	 */
	public Task addTodo(String description) throws JimjamException {
		if (description.isBlank()) {
			throw new JimjamException("A todo must have a description.");
		}

		return this.addTask(new Todo(description));
	}

	/**
	 * Adds a deadline task using the given argument string.
	 * The argument must include a "/by" field specifying the deadline date.
	 *
	 * @param args Argument string containing description and deadline
	 * @return The newly added task
	 * @throws JimjamException If the arguments are invalid or incorrectly formatted
	 */
	public Task addDeadline(String args)
			throws JimjamException {
		// Ensure deadline is formatted correctly
		if (!args.contains(" /by ")) {
			throw new JimjamException("jimjam.tasks.Deadline must include /by.");
		}

		String[] split = args.split(" /by ", 2);
		String description = split[0];
		LocalDate by = Parser.parseDate(split[1]);

		return this.addTask(new Deadline(description, by));
	}

	/**
	 * Adds an event task using the given argument string.
	 * The argument must include "/from" and "/to" fields specifying the event duration.
	 *
	 * @param args Argument string containing description, start date, and end date
	 * @return The newly added task
	 * @throws JimjamException If the arguments are invalid or incorrectly formatted
	 */
	public Task addEvent(String args)
			throws JimjamException {
		// Check that event arguments are correctly formatted
		if (!args.contains(" /from ") || !args.contains(" /to ")) {
			throw new JimjamException("jimjam.tasks.Event must include /from and /to.");
		}

		String[] split = args.split(" /from | /to ", 3);

		String description = split[0];
		LocalDate start = Parser.parseDate(split[1]);
		LocalDate end = Parser.parseDate(split[2]);;
		return this.addTask(new Event(description, start, end));
	}

	/**
	 * Deletes the task at the specified position in the task list.
	 *
	 * @param args String representing the task number (1-based)
	 * @return The task that was removed
	 * @throws JimjamException If no task number is provided or the index is invalid
	 */
	public Task deleteTask(String args) throws JimjamException {
		if (args.isBlank()) {
			throw new JimjamException("Please specify a task number.");
		}

		return this.deleteTask(Integer.parseInt(args));
	}

	/**
	 * Deletes the task at the specified index in the task list.
	 *
	 * @param index Index of the task to delete (1-based)
	 * @return The task that was removed
	 * @throws JimjamException If the index is invalid
	 */
	public Task deleteTask(int index) throws JimjamException {
		// zero-index task number
		index -= 1;
		this.validateIndex(index);
		return tasks.remove(index);
	}

	/**
	 * Updates the completion status of the specified task.
	 *
	 * @param args String representing the task number (1-based)
	 * @param isDone Whether the task should be marked as completed
	 * @return The updated task
	 * @throws JimjamException If no task number is provided or the index is invalid
	 */
	public Task updateTaskStatus(String args, boolean isDone)
			throws JimjamException {
		// handle when no task number is indicated
		if (args.isBlank()) {
			throw new JimjamException("Please specify a task number.");
		}

		return updateTaskStatus(Integer.parseInt(args), isDone);
	}

	/**
	 * Updates the completion status of the task at the given index.
	 *
	 * @param index Index of the task (0-based)
	 * @param isDone Whether the task should be marked as completed
	 * @return The updated task
	 * @throws JimjamException If the index is invalid
	 */
	public Task updateTaskStatus(int index, boolean isDone) throws JimjamException {
		// zero-index task number
		index -= 1;
		this.validateIndex(index);
		Task t = tasks.get(index);
		if (isDone) {
			t.markDone();
		} else {
			t.unmarkDone();
		}
		return t;
	}

	private void validateIndex(int index) throws JimjamException {
		if (index < 0 || index >= tasks.size()) {
			throw new JimjamException("Invalid task index.");
		}
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public int getSize() {
		return tasks.size();
	}
}