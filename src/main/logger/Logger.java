package main.logger;

import main.styles.TextStyle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm ");
    private static final String LOGGER_FILE_PATH = "src/main/logger/application.log";

    private static String getCurrentDate() {
        return DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }

    private static void saveLog(String log) {
        try (
                FileWriter fileWriter = new FileWriter(LOGGER_FILE_PATH, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(log);
            bufferedWriter.newLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void info(String message) {
        String log = getCurrentDate() + "[DEBUG] " + message;
        System.out.println(TextStyle.GREEN.getStyle() + log + TextStyle.RESET.getStyle());
        // вивід у консоль можна закоментувати
        saveLog(log);
    }

    public static void error(String message) {
        String log = getCurrentDate() + "[ERROR] " + message;
        System.out.println(TextStyle.RED.getStyle() + log + TextStyle.RESET.getStyle());
        // вивід у консоль можна закоментувати
        saveLog(log);
    }
}

