public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description, false);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getProgress() {
        // Returns 1 if task is done else 0
        return (isDone ? 1 : 0);
    }

    public void markDone() {
        this.isDone = true;
    }
    
    public void unmarkDone() {
        this.isDone = false;
    }

    public String export() {
        return  String.format("| %d | %s",
                this.getProgress(),
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.getStatusIcon(), this.description);
    }
}
