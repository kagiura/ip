import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final WenStorage WenStorage;

    /**
     * Create a list of tasks, and initialize stored value from the default path
     * @see Task
     */
    public TaskList() {
        tasks = new ArrayList<>();
        WenStorage = new WenStorage();
    }

    /**
     * Create a list of task, and initialize stored value from a custom path
     * @param filePath path of storage file
     * @see WenStorage
     */
    public TaskList(String filePath) {
        tasks = new ArrayList<>();
        WenStorage = new WenStorage(filePath);
        initializeFromStorage(filePath);
    }

    /**
     * Return size of task list
     * @return integer, size of list
     */
    private int count() {
        return tasks.size();
    }

    /**
     * Prettifies and prints all tasks on the list
     */
    public void print() {
        System.out.println("You currently have " + count() + " tasks!");
        for (int i = 0; i < count(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    private void updateStorage() {
        WenStorage.writeToFile(tasks.stream().map(Task::toString).toArray(String[]::new));
    }

    /**
     * Find index of task
     * @param task task to locate
     * @return integer index, -1 if not found
     */
    public int getIndex(Task task) {
        for (int i = 0; i < count(); i++) {
            if (tasks.get(i).equals(task)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove task from list
     * @param taskIndex index of task to be removed
     */
    public void deleteTask(int taskIndex) {
        String taskToDelete = tasks.get(taskIndex - 1).toString();
        tasks.remove(taskIndex -1);
        updateStorage();
        System.out.println("Task " + taskIndex + " has been deleted!");
        System.out.println("(" + taskToDelete + ")");
        System.out.println("You currently have " + count() + " tasks!");
    }

    /**
     * Mark task as complete / incomplete
     * @param taskIndex index of task to check
     * @param done status to be set
     */
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

    /**
     * Find task based on a string search query (has to be exact match)
     * @param query string to search for in descriptions
     * @return a list of tasks that match the query
     */
    public ArrayList<Task> findTask(String query) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < count(); i++) {
            if (tasks.get(i).getDescription().contains(query)) {
                foundTasks.add(tasks.get(i));
            }
        }
        return foundTasks;
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
        updateStorage();
        System.out.println("Task added successfully:" + tasks.get(tasks.size() - 1));
    }

    public void addDeadline(String desc, String by) {
        tasks.add(new Deadline(desc, by));
        updateStorage();
        System.out.println("Task added successfully:" + tasks.get(tasks.size() - 1));
    }

    public void addEvent(String desc, String from, String to) {
        tasks.add(new Event(desc, from, to));
        updateStorage();
        System.out.println("Task added successfully:" + tasks.get(tasks.size() - 1));
    }

    public void initializeFromStorage(String filePath){
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