package project.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Constants {
    public static final String appVersion = "v1.8.0 BETA";
    public static final String appName = "REPAIRO";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
}
