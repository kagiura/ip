/**
 * A task where users can mark as done/undone, with different specific types
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a new task
     * @param description basic description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Parse the task from a string, usually for reading saved tasks.
     * @param description The string to parse the task from
     * @return a new task object, one of three types (Deadline, Event, Todo)
     */
    public Task fromString(String description) {
        char taskType = description.charAt(1);
        boolean taskDone = description.charAt(4) == 'X';

        switch (taskType) {
        case 'D':
            String deadlineDesc = description.substring(7);
            String deadline = "";
            int deadlineIndex = description.indexOf(" (by: ", 1);
            if (deadlineIndex != -1) {
                deadlineDesc = description.substring(7, deadlineIndex);
                deadline = description.substring(deadlineIndex + 6, description.length() - 1);
            }
            return new Deadline(deadlineDesc, deadline, taskDone);

        case 'E':
            String eventDesc = description.substring(7);
            String from = "";
            String to = "";

            int fromIndex = description.indexOf(" (from: ", 1);
            int toIndex = description.indexOf(" to: ", 1);
            if (fromIndex != -1 && toIndex != -1) {
                eventDesc = description.substring(7, fromIndex);
                from = description.substring(fromIndex + 8, toIndex);
                to = description.substring(toIndex + 5, description.length() - 1);
            }

            return new Event(eventDesc, from, to, taskDone);

        case 'T': // todo
        default: // just in case ??
            return new Todo(description.substring(7), taskDone);
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void print() {
        System.out.print("[" + (isDone ? "X" : " ") + "] ");
        System.out.println(description);
    }

    /**
     * Get the task description
     * @return String form of task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets a simplified representation of the task
     * @return A string form of the task, including its done state
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Set the task to be done or not
     * @param done New state of task to be set
     */
    public void setDone(boolean done){
        isDone = done;
    }
}
