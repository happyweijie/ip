public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String export() {
        return String.format("T %s", super.export());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
