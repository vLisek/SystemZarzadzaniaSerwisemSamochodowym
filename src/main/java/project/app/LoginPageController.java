package project.app;

// Lista import'ów:
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import project.app.utils.Constants;
import project.app.utils.DatabaseConnector;
import project.app.utils.ExitHandler;

@SuppressWarnings("ALL")
public class LoginPageController {

    // Główny element logowania.
    @FXML
    private StackPane loginPane;

    // Pole użytkownika.
    @FXML
    private TextField usernameField;

    // Pole hasła.
    @FXML
    private PasswordField passwordField;

    // Pole widoczności hasła.
    @FXML
    private TextField visiblePasswordField;

    // Button do wyświetlania hasła.
    @FXML
    private Button togglePasswordButton;

    @FXML
    private ImageView toggleIcon;

    private boolean isPasswordVisible = false;

    private Image eyeOpenIcon;
    private Image eyeClosedIcon;

    // Przycisk od logowania.
    @FXML
    private Button loginButton;

    // Wyświetlanie statusu logowania.
    @FXML
    private Label errorMessage;

    // Wyświetlanie wersji aplikacji.
    @FXML
    private Label versionLabel;

    // Wyświetlanie aktualnej daty.
    @FXML
    private Label dateLabel;

    // Przycisk do zamykania.
    @FXML
    private Button exitButton;

    @FXML
    public void handleExit() {
        ExitHandler.handleExit((Stage) exitButton.getScene().getWindow());
    }


    // Inicjalizacja aplikacji.
    @FXML
    public void initialize() {
        // Przejście powolnego pojawiania się.
        FadeTransition ft = new FadeTransition(Duration.seconds(2), loginPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        versionLabel.setText(Constants.APP_VERSION);

        // Wyświetlanie aktualnej daty.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String actualDate = LocalDate.now().format(formatter);
        dateLabel.setText(actualDate);

        loginPane.setOnMouseClicked(event -> {
            // Po kliknięciu poza elementy panelu logowania, tracą one fokus i "odznaczają się".
            if (!(event.getTarget() instanceof TextField) && !(event.getTarget() instanceof PasswordField) && !(event.getTarget() instanceof Button)) {
                // Zabranie fokusa z pól tekstowych.
                loginPane.requestFocus();
            }
        });

        visiblePasswordField.setVisible(false);
        passwordField.setVisible(true);

        // Zarządzanie widocznością = zarządzanie układem
        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());

        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        eyeOpenIcon = new Image(getClass().getResource("/images/eye_open_icon.png").toExternalForm());
        eyeClosedIcon = new Image(getClass().getResource("/images/eye_closed_icon.png").toExternalForm());

        toggleIcon.setImage(eyeClosedIcon);

        toggleIcon.setFitWidth(20);
        toggleIcon.setFitHeight(20);
        toggleIcon.setPreserveRatio(true);

        togglePasswordButton.setTooltip(new Tooltip("Pokaż/Ukryj hasło"));
    }

    @FXML
    private void handleTogglePassword() {
        isPasswordVisible = !isPasswordVisible;

        passwordField.setVisible(!isPasswordVisible);
        visiblePasswordField.setVisible(isPasswordVisible);

        toggleIcon.setImage(isPasswordVisible ? eyeOpenIcon : eyeClosedIcon);

        if (isPasswordVisible) {
            visiblePasswordField.requestFocus();
            visiblePasswordField.positionCaret(visiblePasswordField.getText().length());
        }
        else {
            passwordField.requestFocus();
            passwordField.positionCaret(passwordField.getText().length());
        }
    }

    // Uchwyt do bazy danych.
    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Wyświetlenie odpowiedniej informacji na temat wprowadzonych danych.
        if (username.isEmpty() && password.isEmpty()) {
            errorMessage.setText("Proszę wypełnić wszystkie pola!");
            new Timeline(new KeyFrame(Duration.seconds(3), evt -> errorMessage.setText(""))).play();
            return;
        }
        else if (username.isEmpty()) {
            errorMessage.setText("Proszę wprowadzić login!");
            new Timeline(new KeyFrame(Duration.seconds(3), evt -> errorMessage.setText(""))).play();
            return;
        }
        else if (password.isEmpty()) {
            errorMessage.setText("Proszę wprowadzić hasło!");
            new Timeline(new KeyFrame(Duration.seconds(3), evt -> errorMessage.setText(""))).play();
            return;
        }
        try (Connection connection = DatabaseConnector.connect()) {
            String loginSQL = "SELECT * FROM loginy WHERE login = ? AND haslo = ?";
            var statement = connection.prepareStatement(loginSQL);
            statement.setString(1, username);
            statement.setString(2, password);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent mainRoot = loader.load();

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(mainRoot));
                stage.show();
            }
            else {
                errorMessage.setText("Niepoprawny login lub hasło.");
                new Timeline(new KeyFrame(Duration.seconds(3), evt -> errorMessage.setText(""))).play();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage.setText("Błąd połączenia z bazą.");
        }
    }
}
