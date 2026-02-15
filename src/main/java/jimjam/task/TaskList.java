package jimjam.task;

import jimjam.exception.JimjamException;
import jimjam.logic.Parser;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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
        int oldSize = this.getSize();

		this.tasks.add(task);

        assert this.getSize() == oldSize + 1 : "Task list size did not increase";

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

		String[] parts = args.split(" /by ", 2);
		String description = parts[0];
		LocalDate by = Parser.parseDate(parts[1]);

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

		String[] parts = args.split(" /from | /to ", 3);

		String description = parts[0];
		LocalDate start = Parser.parseDate(parts[1]);
		LocalDate end = Parser.parseDate(parts[2]);;
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

        int oldSize = this.getSize();

        Task deleted = tasks.remove(index);

        assert this.getSize() == oldSize - 1 : "Task list size did not decrease";

		return deleted;
	}

	/**
	 * Filters the task list for tasks whose descriptions contain the specified substring.
	 * The search is performed case-insensitively. If the provided substring is empty,
	 * a copy of the original task list is returned.
	 *
	 * @param substring The search term to look for within task descriptions.
	 * @return A new {@link TaskList} containing only the tasks that match the search criteria.
	 */
	public TaskList searchTask(String substring) {
		List<Task> res = this.tasks.stream()
				.filter(task -> task.getDescription()
						.toLowerCase()
						.contains(substring.toLowerCase()))
				.toList();

		return new TaskList(res);
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
		t.setDone(isDone);

		return t;
	}

	/**
	 * Returns a {@code TaskList} containing tasks due within the specified number of days.
	 *
	 * <p>The given argument is expected to be a non-blank string representation
	 * of a non-negative integer. The integer value represents the number of days
	 * from today (inclusive) within which tasks should be returned.</p>
	 *
	 * @param args a non-blank string representing the number of days
	 * @return a {@code TaskList} containing tasks due within the specified number of days
	 * @throws JimjamException if {@code args} is blank or not a valid integer
	 */
	public TaskList getTasksDueWithin(String args) throws JimjamException {
		if  (args.isBlank()) {
			throw new JimjamException("Please specify number of days from today.");
		}

		try {
			int n = Integer.parseInt(args);
			return this.getTasksDueWithin(n);
		} catch (NumberFormatException e) {
			throw new JimjamException("Please an integer for number of days.");
		}
	}

	/**
	 * Returns a {@code TaskList} containing tasks whose relevant date
	 * falls within {@code n} days from today inclusive.
	 *
	 * <p>Only tasks with a non-empty relevant date are considered.
	 * Tasks without an associated date are ignored.</p>
	 *
	 * @param numDays the number of days from today (must be non-negative)
	 * @return a {@code TaskList} containing tasks due within {@code n} days
	 * @throws JimjamException if {@code n} is negative
	 */
	public TaskList getTasksDueWithin(int numDays) throws JimjamException {
		if (numDays < 0) {
			throw new JimjamException("Reminder days should not be negative");
		}

		LocalDate today = LocalDate.now();

		List<Task> reminders = this.tasks.stream()
				.filter(t -> t.getRelevantDate().isPresent()) // filter tasks with dates
				.filter(t -> {
					// filter tasks where days until event is within desired range
					long daysTil = t.getRelevantDate()
							.map(date -> ChronoUnit.DAYS.between(today, date))
							.orElse(0L);
					return daysTil <= numDays;
				})
				.toList();

		return new TaskList(reminders);
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