public class Event extends Task  {
    protected String date;
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone,
                 String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String export() {
        return String.format("E %s | %s | %s",
                super.export(),
                this.start,
                this.end);
    }

    public String toString() {
        return String.format("[D]%s (from: %s to: %s)",
                super.toString(), this.start, this.end);
    }
}
