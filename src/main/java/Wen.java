import java.util.Scanner;

public class Wen {
    public static void main(String[] args) {
        initializeAndGreet();

        String command = "";
        Scanner in = new Scanner(System.in);

        while (!command.equals("bye")) {
            command = in.nextLine();
            switch (command) {
                case "bye":
                    break;
                default:
                    System.out.println(command);
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
}
