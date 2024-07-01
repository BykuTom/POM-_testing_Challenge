package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static Logger instance;
    private static final String LOG_FILE_PATH = "log.txt";
    private PrintWriter writer;

    private Logger() {
        try {
            FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true); // Append mode
            writer = new PrintWriter(fileWriter, true);
        } catch (IOException e) {
            System.out.println("There was an issue with writing to the logger file...");
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        writer.println(message);
    }

    public void close() {
        writer.close();
    }
}

