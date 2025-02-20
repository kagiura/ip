import java.util.Scanner;

public class Wen {

    private static final int TASK_COUNT = 100;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[TASK_COUNT];

    public static void main(String[] args) {
        initializeAndGreet();

        String input = "";
        Scanner in = new Scanner(System.in);


        while (!input.equals("bye")) {
            final String command = input.split(" ", 2)[0];
            final String commandArgs = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";

            switch (command) {
            case "":
                break;

            case "list":
                printTasks();
                break;

            case "mark":
            case "unmark":
                try {
                    int taskIndex = Integer.parseInt(commandArgs);
                    markTask(tasks[taskIndex - 1], command.equals("mark"));
                } catch (NumberFormatException e) {
                    System.out.println("The task number is formatted incorrectly!");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("This task doesn't exist!");
                }
                break;

            case "todo":
                addTodo(commandArgs.trim());
                break;

            case "deadline":
                if (!commandArgs.contains("/by")) {
                    System.out.println("Please specify a due date using /by!");
                    break;
                }
                final String deadlineDesc = commandArgs.split("/by", 2)[0].trim();
                final String deadlineBy = commandArgs.split("/by", 2)[1].trim();

                addDeadline(deadlineDesc, deadlineBy);
                break;


            case "event":
                if (!commandArgs.contains("/from") || !commandArgs.contains("/to")) {
                    System.out.println("Please specify the event duration using /from and /to!");
                    break;
                }
                final String eventDesc = commandArgs.split("/from", 2)[0].split("/to", 2)[0].trim();
                final String eventFrom = commandArgs.split("/from", 2)[1].split("/to", 2)[0].trim();
                final String eventTo = commandArgs.split("/to", 2)[1].split("/from", 2)[0].trim();

                addEvent(eventDesc, eventFrom, eventTo);
                break;

            default:
                System.out.println("Unknown command \"" + command + "\"! Double check your message and try running again~");
                break;
            }

            input = in.nextLine();
        }

        terminateAndGoodbye();
    }


    private static void initializeAndGreet() {
        System.out.println("Hello, I'm Wen!");
        System.out.println("Let me know what I can help you with~☆");
    }

    private static void terminateAndGoodbye() {
        System.out.println();
        System.out.println("Aw, you're already going?");
        System.out.println("It's okay, let's meet again soon!");

        System.exit(0);
    }

    private static void printTasks() {
        System.out.println("You currently have " + taskCount + " tasks!");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }

    private static void markTask(Task task, boolean done) {
        task.setDone(done);
        if (done) {
            System.out.println("Task marked as done: " + task);
        } else {
            System.out.println("Task marked as incomplete: " + task);
        }
    }

    private static void addTodo(String description) {
        tasks[taskCount] = new Todo(description);
        System.out.println("Task added successfully:" + tasks[taskCount]);
        taskCount++;
    }

    private static void addDeadline(String desc, String by) {
        tasks[taskCount] = new Deadline(desc, by);
        System.out.println("Task added successfully:" + tasks[taskCount]);
        taskCount++;
    }

    private static void addEvent(String desc, String from, String to) {
        tasks[taskCount] = new Event(desc, from, to);
        System.out.println("Task added successfully:" + tasks[taskCount]);
        taskCount++;
    }
}
