package jimjam.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a description, start date and end date.
 * An event can be marked as completed or incomplete.
 */
public class Event extends Task  {
    private static final DateTimeFormatter OUTPUT_FORMAT= DateTimeFormatter.ofPattern(
            "MMM dd yyyy");
    /** The start date of the event */
    protected LocalDate start;
    /** The end date of the event */
    protected LocalDate end;

    /**
     * Creates an event task with the given description, start date and end date.
     * The task is initially marked as not completed.
     *
     * @param description Description of the event task
     * @param start Start date of the event
     * @param end End date of the event
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task with the given description, completion status, start date and end date.
     * The task is initially marked as not completed.
     *
     * @param description Description of the event task
     * @param isDone Whether the task is already completed
     * @param start Start date of the event
     * @param end End date of the event
     */
    public Event(String description, boolean isDone,
                 LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getIcon() {
        return "E";
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.start.format(OUTPUT_FORMAT),
                this.end.format(OUTPUT_FORMAT));
    }
}
