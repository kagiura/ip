import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    private int count() {
        return tasks.size();
    }

    public void print() {
        System.out.println("You currently have " + count() + " tasks!");
        for (int i = 0; i < count(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }


    public void updateStorage() {
        WenStorage.writeToFile(tasks.stream().map(Task::toString).toArray(String[]::new));
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int taskIndex) {
        String taskToDelete = tasks.get(taskIndex - 1).toString();
        tasks.remove(taskIndex);
        updateStorage();
        System.out.println("Task " + taskIndex + " has been deleted!");
        System.out.println("(" + taskToDelete + ")");
        System.out.println("You currently have " + count() + " tasks!");
    }

    public void markTask(int taskIndex, boolean done) {
        Task task = tasks.get(taskIndex - 1);
        task.setDone(done);
        updateStorage();
        if (done) {
            System.out.println("Task marked as done: " + task);
        } else {
            System.out.println("Task marked as incomplete: " + task);
        }
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
        updateStorage();
        System.out.println("Task added successfully:" + tasks.getLast());
    }

    public void addDeadline(String desc, String by) {
        tasks.add(new Deadline(desc, by));
        updateStorage();
        System.out.println("Task added successfully:" + tasks.getLast());
    }

    public void addEvent(String desc, String from, String to) {
        tasks.add(new Event(desc, from, to));
        updateStorage();
        System.out.println("Task added successfully:" + tasks.getLast());
    }

    public void initializeFromStorage(){
        if (!WenStorage.isFileExists()) {
            WenStorage.writeToFile(new String[0]);
        }
        String[] data = WenStorage.readFromFile();
        if (data.length > 0) {
            System.out.println("[I've loaded " + data.length + " tasks from your local storage!]");
            for (String datum : data) {
                Task t = new Task("").fromString(datum);
                tasks.add(t);
            }
        }
    }
}