import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WenStorage {
    private final String filePath;
    private final File storageFile;

    /**
     * Create a new storage instance using the default file path
     */
    public WenStorage() {
        filePath = "./wen-storage.txt";
        storageFile = new File(filePath);
    }

    /**
     * Create a new storage instance using a custom file path
     * @param customFilePath path to save info to, relative to the main code
     */
    public WenStorage(String customFilePath) {
        filePath = customFilePath;
        storageFile = new File(filePath);
    }

    /**
     * Check if the storage file exists
     * @return boolean to represent file state
     */
    public boolean isFileExists() {
        return storageFile.exists();
    }

    /**
     * Write any string to file
     * @param data array of strings, to be joined in the saved file with newlines (\n)
     */
    public void writeToFile(String[] data) {
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

    /**
     * Read from file and return as strings
     * @return array of strings, delimited with newlines (\n)
     */
    public String[] readFromFile() {
        ArrayList<String> output = new ArrayList<>();
        Scanner s = null;
        try {
            s = new Scanner(storageFile);
            while (s.hasNext()) {
                output.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return output.toArray(new String[0]);
    }
}