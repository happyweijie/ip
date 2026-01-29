package jimjam.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT= DateTimeFormatter.ofPattern(
            "MMM dd yyyy");

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;

    }

    public Deadline(String description, boolean isDone,
                    LocalDate by) {
        super(description, isDone);
        this.by = by;
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
