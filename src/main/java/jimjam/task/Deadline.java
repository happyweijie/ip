package jimjam.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that has a description and a deadline date.
 * A deadline can be marked as completed or incomplete.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT= DateTimeFormatter.ofPattern(
            "MMM dd yyyy");

    /** The deadline date */
    protected LocalDate by;

    /**
     * Creates a deadline task with the given description and deadline date.
     * The task is initially marked as not completed.
     *
     * @param description Description of the deadline task
     * @param by Deadline for the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;

    }

    /**
     * Creates a deadline task with the given description, completion status and deadline date.
     *
     * @param description Description of the deadline task
     * @param isDone Whether the task is already completed
     * @param by Deadline for the task
     */
    public Deadline(String description, boolean isDone,
                    LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    private static LocalDate parseDate(String date) {
        try {
            // Parse using yyyy-mm-dd
            return LocalDate.parse(date);
        } catch (DateTimeParseException d) {
            // Set to today's date by default
            return LocalDate.now();
        }
    }

    @Override
    public String getIcon() {
        return "D";
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), this.by.format(OUTPUT_FORMAT));
    }
}
