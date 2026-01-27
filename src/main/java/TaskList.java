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

	private Task addTask(Task task) {
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
			throw new JimjamException("Deadline must include /by.");
		}

		String[] split = args.split(" /by ", 2);
		String description = split[0];
		String by = split[1];

		return this.addTask(new Deadline(description, by));
	}

	public Task addEvent(String args)
			throws JimjamException {
		// Check that event arguments are correctly formatted
		if (!args.contains(" /from ") || !args.contains(" /to ")) {
			throw new JimjamException("Event must include /from and /to.");
		}

		String[] split = args.split(" /from | /to ", 3);

		String description = split[0];
		String start = split[1];
		String end = split[2];
		return this.addTask(new Event(description, start, end));
	}

	public Task delete(String args) throws JimjamException {
		if (args.isBlank()) {
			throw new JimjamException("Please specify a task number.");
		}

		// zero-index task number
		int idx = Integer.parseInt(args) - 1;
		return this.delete(idx);
	}

	public Task delete(int index) throws JimjamException {
		this.validateIndex(index);
		return tasks.remove(index);
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