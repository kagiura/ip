import java.util.Scanner;

public class Wen {

    private static int taskCount = 0;
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        initializeAndGreet();

        String input = "";
        Scanner in = new Scanner(System.in);

        while (!input.equals("bye")) {
            input = in.nextLine();
            final String command = input.split(" ", 2)[0];
            final String commandArgs = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";

            switch (command) {
                case "":
                case "bye":
                    break;

                case "list":
                    printTasks();
                    break;

                case "mark":
                case "unmark":
                    try {
                        int taskIndex = Integer.parseInt(commandArgs);
                        markTask(tasks[taskIndex -1], command.equals("mark"));
                    } catch (NumberFormatException e) {
                        System.out.println("The task number is formatted incorrectly!");
                    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("This task doesn't exist!");
                    }
                    break;

                default:
                    addTask(input);
//                    System.out.println("Unknown command \""+command+"\"! Double check your message and try running again~");
                    break;
            }
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
        for (int i=0; i<taskCount; i++) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
    }

    private static void addTask(String description) {
        tasks[taskCount] = new Task(description);

        System.out.println("Task added succesfully:");
        tasks[taskCount].print();

        taskCount++;
    }

    private static void markTask(Task task, boolean done) {
        task.setDone(done);
        if (done) {
            System.out.println("Task marked as done: " + task);
        } else {
            System.out.println("Task marked as incomplete: " + task);
        }
    }
}
