public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean done) {
        super(description);
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}