import java.util.HashMap;
import java.util.Map;

public class Parser {
    private String input;
    private String commandName;
    private String commandArgsString;
    private Map<String, String> commandArgs;


    Parser(String input) {
        this.input = input;

        commandName = input.split(" ", 2)[0];
        commandArgsString = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";

        commandArgs = new HashMap<>();

        String[] commandParts = commandArgsString.split("/");
        if (commandParts.length > 1) {
            commandArgs.put("", commandParts[0]);
            for (int i = 1; i < commandParts.length; i++) {
                String argName = commandParts[i].split(" ")[0].trim();
                String argValue = commandParts[i].split(" ").length > 1 ? commandParts[i].split(" ", 2)[1].trim() : "";
                commandArgs.put(argName, argValue);
            }
        } else {
            commandArgs.put("", commandArgsString);
        }

    }

    public boolean equals(String name) {
        return commandName.equals(name);
    }

    public String getName() {
        return commandName;
    }

    public String getArg(){
        return commandArgs.get("");
    }

    public String getArg(String arg) {
        return commandArgs.get(arg);
    }

    public boolean hasArg(String arg) {
        return commandArgs.containsKey(arg);
    }

}