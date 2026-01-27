import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task  {
    private static final DateTimeFormatter OUTPUT_FORMAT= DateTimeFormatter.ofPattern(
            "MMM dd yyyy");
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

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
