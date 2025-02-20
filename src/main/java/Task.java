public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    public void setDone(boolean done){
        isDone = done;
    }
}
