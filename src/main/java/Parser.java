import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final String commandName;
    private final Map<String, String> commandArgs;
    private final String commandMainArg;


    Parser(String input) {

        commandName = input.split(" ", 2)[0];
        String commandArgsString = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";

        commandArgs = new HashMap<>();

        String[] commandParts = commandArgsString.split("/");
        if (commandParts.length > 1) {
            for (int i = 1; i < commandParts.length; i++) {
                String argName = commandParts[i].split(" ")[0].trim();
                String argValue = commandParts[i].split(" ").length > 1 ? commandParts[i].split(" ", 2)[1].trim() : "";
                commandArgs.put(argName, argValue);
            }
        }
        commandMainArg = commandParts[0].trim().isEmpty() ? null : commandParts[0].trim();

    }

    public boolean equals(String name) {
        return commandName.equals(name);
    }

    public String getName() {
        return commandName;
    }

    public String getArg(){
        return commandMainArg;
    }

    public String getArg(String arg) {
        return commandArgs.get(arg);
    }

    public boolean hasArg() {
        return commandMainArg != null;
    }
    public boolean hasArg(String arg) {
        return commandArgs.containsKey(arg);
    }

}