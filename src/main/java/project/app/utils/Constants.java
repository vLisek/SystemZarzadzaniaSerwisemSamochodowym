package project.app.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String appVersion = "v1.9.0";
    public static final String appName = "REPAIRO";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
}
