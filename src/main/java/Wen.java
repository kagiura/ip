import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Wen {
    private static TaskList tasks;

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
                tasks.print();
                break;

            case "delete":
                try {
                    int taskIndex = Integer.parseInt(commandArgs);
                    tasks.deleteTask(taskIndex -1);
                } catch (NumberFormatException e) {
                    System.out.println("The task number is formatted incorrectly!");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("This task doesn't exist!");
                }
                break;

            case "mark":
            case "unmark":
                try {
                    int taskIndex = Integer.parseInt(commandArgs);
                    tasks.markTask(taskIndex - 1, command.equals("mark"));
                } catch (NumberFormatException e) {
                    System.out.println("The task number is formatted incorrectly!");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("This task doesn't exist!");
                }
                break;

            case "todo":
                tasks.addTodo(commandArgs.trim());
                break;

            case "deadline":
                if (!commandArgs.contains("/by")) {
                    System.out.println("Please specify a due date using /by!");
                    break;
                }
                final String deadlineDesc = commandArgs.split("/by", 2)[0].trim();
                final String deadlineBy = commandArgs.split("/by", 2)[1].trim();

                tasks.addDeadline(deadlineDesc, deadlineBy);
                break;


            case "event":
                if (!commandArgs.contains("/from") || !commandArgs.contains("/to")) {
                    System.out.println("Please specify the event duration using /from and /to!");
                    System.out.println("Example: \"event Birthday party /from 6pm /to 8pm\"");
                    break;
                }
                final String eventDesc = commandArgs.split("/from", 2)[0].split("/to", 2)[0].trim();
                final String eventFrom = commandArgs.split("/from", 2)[1].split("/to", 2)[0].trim();
                final String eventTo = commandArgs.split("/to", 2)[1].split("/from", 2)[0].trim();

                tasks.addEvent(eventDesc, eventFrom, eventTo);
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
        tasks = new TaskList();
        tasks.initializeFromStorage();
        System.out.println("Hello, I'm Wen!");
        System.out.println("Let me know what I can help you with~☆");
    }

    private static void terminateAndGoodbye() {
        System.out.println();
        System.out.println("Aw, you're already going?");
        System.out.println("It's okay, let's meet again soon!");

        System.exit(0);
    }
}
