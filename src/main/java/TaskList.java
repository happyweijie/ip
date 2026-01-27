public class TaskList {
	private List<Task> tasks;

	public TaskList(List<Task> tasks) {
		this.tasks = tasks;
	}

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public void addTodo(String description) throws JimjamException {
		if (description.isBlank()) {
			throw new JimjamException("A todo must have a description.");
		}

		tasks.add(new Todo(description));
	}

	public Task delete(int index) throws JimjamException {
		validateIndex(index);
		return tasks.remove(index);
	}

	public Task updateStatus(int index, boolean isDone) throws JimjamException {
		validateIndex(index);
		Task t = tasks.get(index);
		if (!isDone) {
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