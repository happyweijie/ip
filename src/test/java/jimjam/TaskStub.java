package jimjam;

import jimjam.task.Task;

import java.time.LocalDate;
import java.util.Optional;

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

	@Override
	public Optional<LocalDate> getRelevantDate() {
		return Optional.empty();
	}
}
