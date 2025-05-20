package project.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
    private static final Logger logger = Logger.getLogger(DatabaseConnector.class.getName());

    private static final String config_file = "/config.properties";
    private static String databaseUrl;
    private static String databaseUser;
    private static String databasePassword;

    static {
        loadConfiguration();
    }

    private static void loadConfiguration() {
        try (InputStream input = DatabaseConnector.class.getResourceAsStream(config_file)) {
            if (input == null) {
                logger.severe("Nie znaleziono pliku konfiguracyjnego: " + config_file);
                return;
            }

            Properties props = new Properties();
            props.load(input);

            databaseUrl = props.getProperty("db.url");
            databaseUser = props.getProperty("db.user");
            databasePassword = props.getProperty("db.password");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Błąd podczas wczytywania konfiguracji DB", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Błąd podczas łączenia z bazą danych", e);
            throw e;
        }
    }
}
