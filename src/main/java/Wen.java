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
                case "bye":
                    break;

                case "list":
                    printTasks();
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
            tasks[i].print();
        }
    }

    private static void addTask(String description) {
        tasks[taskCount] = new Task(description);

        System.out.println("Task added succesfully:");
        tasks[taskCount].print();

        taskCount++;
    }
}
