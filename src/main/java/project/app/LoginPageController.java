package project.app;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

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

    // CheckBox do wyświetlania hasła.
    @FXML
    private CheckBox showPasswordCheckbox;

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

    // Inicjalizacja aplikacji.
    @FXML
    public void initialize() {
        // Przejście powolnego pojawiania się.
        FadeTransition ft = new FadeTransition(Duration.seconds(2), loginPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        // Wyświetlanie wersji aplikacji.
        versionLabel.setText("v1.0.0 BETA");

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

        // Obsługa CheckBoxa do pokazywania hasła.
        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        showPasswordCheckbox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            passwordField.setVisible(!isSelected);
            passwordField.setManaged(!isSelected);
            visiblePasswordField.setVisible(isSelected);
            visiblePasswordField.setManaged(isSelected);
        });
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Zalogowano pomyślnie!");
                alert.showAndWait();
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

    // Uchwyt do zamykania programu.
    @FXML
    public void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Czy na pewno chcesz zamknąć program?");

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/black_logo2048x2048.png")));

        ButtonType confirmButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            System.exit(0);
        }
    }
}
