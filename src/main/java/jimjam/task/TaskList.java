package jimjam.task;

import jimjam.exception.JimjamException;
import jimjam.parser.Parser;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
	private List<Task> tasks;

	public TaskList(List<Task> tasks) {
		this.tasks = tasks;
	}

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	protected Task addTask(Task task) {
		this.tasks.add(task);
		return task;
	}

	public Task addTodo(String description) throws JimjamException {
		if (description.isBlank()) {
			throw new JimjamException("A todo must have a description.");
		}

		return this.addTask(new Todo(description));
	}

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

	public Task deleteTask(String args) throws JimjamException {
		if (args.isBlank()) {
			throw new JimjamException("Please specify a task number.");
		}

		return this.deleteTask(Integer.parseInt(args));
	}

	public Task deleteTask(int index) throws JimjamException {
		// zero-index task number
		index -= 1;
		this.validateIndex(index);
		return tasks.remove(index);
	}

	public TaskList searchTasks(String substring) {
		List<Task> res = this.tasks.stream()
				.filter(task -> task.getDescription()
						.toLowerCase()
						.contains(substring.toLowerCase()))
				.toList();
		return new TaskList(res);
	}
	public Task updateTaskStatus(String args, boolean isDone)
			throws JimjamException {
		// handle when no task number is indicated
		if (args.isBlank()) {
			throw new JimjamException("Please specify a task number.");
		}

		// zero-index task number
		int idx = Integer.parseInt(args) - 1;
		return updateTaskStatus(idx, isDone);
	}

	public Task updateTaskStatus(int index, boolean isDone) throws JimjamException {
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