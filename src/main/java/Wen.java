import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Wen {
//    private static String storagePath = "./wen-storage.txt";
    private final TaskList tasks;

    private Wen(String storagePath) {
        tasks = new TaskList(storagePath);
    }

    private void run() {

        Ui.greet();

        String input = "";
        Scanner in = new Scanner(System.in);


        while (!input.equals("bye")) {
            Parser command = new Parser(input);

            switch (command.getName()) {
            case "":
                break;

            case "list":
                tasks.print();
                break;

            case "delete":
                try {
                    int taskIndex = Integer.parseInt(command.getArg());
                    tasks.deleteTask(taskIndex -1);
                } catch (NumberFormatException e) {
                    System.out.println("The task number is formatted incorrectly!");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("This task doesn't exist!");
                }
                break;

            case "find":
                ArrayList<Task> foundTasks = tasks.findTask(command.getArg());
                if (foundTasks.isEmpty()) {
                    System.out.println("There are no tasks found!");
                } else {
                    System.out.println("Found " + foundTasks.size() + " tasks!");
                    for (Task task : foundTasks) {
                        System.out.print(tasks.getIndex(task)+1);
                        System.out.print(". ");
                        System.out.println(task);
                    }
                }
                break;

            case "mark":
            case "unmark":
                try {
                    int taskIndex = Integer.parseInt(command.getArg());
                    tasks.markTask(taskIndex - 1, command.equals("mark"));
                } catch (NumberFormatException e) {
                    System.out.println("The task number is formatted incorrectly!");
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("This task doesn't exist!");
                }
                break;

            case "todo":
                tasks.addTodo(command.getArg());
                break;

            case "deadline":
                if (!command.hasArg("by")) {
                    System.out.println("Please specify a due date using /by!");
                    break;
                }

                tasks.addDeadline(command.getArg(), command.getArg("by"));
                break;

            case "event":
                if (!command.hasArg("from") || !command.hasArg("to")) {
                    System.out.println("Please specify the event duration using /from and /to!");
                    System.out.println("Example: \"event Birthday party /from 6pm /to 8pm\"");
                    break;
                }

                tasks.addEvent(command.getArg(), command.getArg("from"), command.getArg("to"));
                break;

            default:
                System.out.println("Unknown command \"" + command.getName() + "\"! Double check your message and try running again~");
                break;
            }

            input = in.nextLine();
        }

        Ui.goodbye();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Wen("./wen-storage.txt").run();
    }

}
