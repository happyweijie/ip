package jimjam;

import jimjam.task.Task;

public class TaskStub extends Task {
	public TaskStub(String description) {
		super(description);
	}

	public TaskStub(String description, boolean isDone) {
		super(description, isDone);
	}

	@Override
	public String getIcon() {
		return "S"; // S for Stub
	}
}
