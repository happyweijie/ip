public class Event extends Task  {
    protected String date;
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return String.format("[D]%s (from: %s to: %s)",
                super.toString(), this.start, this.end);
    }
}
