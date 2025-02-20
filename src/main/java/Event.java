public class Event extends Task {

    protected String from;
    protected String by;

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    public Event(String description, String from, String by, boolean done) {
        super(description);
        this.from = from;
        this.by = by;
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + by + ")";
    }
}