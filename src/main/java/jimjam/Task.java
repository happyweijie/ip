package jimjam;

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

    public abstract String getIcon();

    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }
    
    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.getStatusIcon(), this.description);
    }
}
