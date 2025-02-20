import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WenStorage {
    private static File storageFile;
    private static final String filePath = "./wen-storage.txt";

    WenStorage() {
        storageFile = new File(filePath);
    }

    public static boolean isFileExists() {
        return storageFile.exists();
    }

    public static String getAbsolutePath() {
        return storageFile.getAbsolutePath();
    }

    public static void writeToFile(String[] data) {
        String dataString = "";
        for (String datum : data) {
            dataString += datum;
            dataString += System.lineSeparator();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            fw.write(
                dataString
            );
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file! This may be because your memory is full, or the file is corrupted :(");
        }
    }

    public static String[] readFromFile() {
        ArrayList<String> output = new ArrayList<>();
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(storageFile);
            while (s.hasNext()) {
//                System.out.println("|" + s.nextLine());
                output.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return output.toArray(new String[0]);
    }
}