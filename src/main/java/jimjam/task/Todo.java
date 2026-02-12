package jimjam.task;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Represents a to-do task that has a description and no associated date or time.
 * A to-do task can be marked as completed or incomplete.
 */
public class Todo extends Task {

    /**
     * Creates a to-do task with the given description.
     * The task is initially marked as not completed.
     *
     * @param description Description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a to-do task with the given description and completion status.
     *
     * @param description Description of the to-do task
     * @param isDone Whether the task is already completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getIcon() {
        return "T";
    }

    @Override
    public Optional<LocalDate> getRelevantDate() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
