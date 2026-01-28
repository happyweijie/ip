package jimjam.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
