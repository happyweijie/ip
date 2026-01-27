import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT= DateTimeFormatter.ofPattern(
            "MMM dd yyyy");

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);

    }

    public Deadline(String description, boolean isDone,
                    String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
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
