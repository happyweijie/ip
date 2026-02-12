package jimjam.task;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Represents a generic task in the JimJam application.
 * This is an abstract base class that encapsulates a description and a completion status.
 * Specific task types should extend this class and implement the {@link #getIcon()} method.
 */
public abstract class Task {
    /** The description of the Task */
    protected String description;
    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a new Task with a specified description and completion status.
     *
     * @param description The text describing the task.
     * @param isDone The initial completion status.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a new Task with a specified description.
     * The task is initialized as not done by default.
     *
     * @param description The text describing the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Returns a status icon representing whether the task is completed.
     *
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a type-specific icon representing the task category.
     * Subclasses must implement this to return their unique identifier (e.g., "T" for Todo).
     *
     * @return A String representation of the Task icon.
     */
    public abstract String getIcon();

    /**
     * Returns the date relevant for time-based operations such as reminders.
     *
     * <p>For example:
     * <ul>
     *   <li>Deadline returns its due date</li>
     *   <li>Event returns its start date</li>
     *   <li>Tasks without a date return {@code Optional.empty()}</li>
     * </ul>
     *
     * @return an {@code Optional} containing the relevant {@code LocalDate},
     * or {@code Optional.empty()} if the task has no associated date
     */
    public abstract Optional<LocalDate> getRelevantDate();

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Updates the completion status of the task to the specified boolean value
     *
     * @param isDone Completion status of the task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.getStatusIcon(), this.description);
    }
}
