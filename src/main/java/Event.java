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
    public String getIcon() {
        return "E";
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.start, this.end);
    }
}
