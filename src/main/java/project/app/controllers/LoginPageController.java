package project.app.controllers;

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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import project.app.utils.ConfirmationHandler;
import project.app.utils.Constants;
import project.app.utils.DatabaseConnector;

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
    private ToggleButton togglePasswordButton;

    private Image eyeOpenIcon;
    private Image eyeClosedIcon;
    private ImageView eyeIcon;

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

    private void clearErrorAfterDelay() {
        new Timeline(new KeyFrame(Duration.seconds(3), _ -> errorMessage.setText(""))).play();
    }

    @FXML
    public void handleExit() {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        ConfirmationHandler.show("Potwierdzenie wyjścia", "Czy napewno chcesz zamknąć aplikację?", currentStage::close);
    }

    // Inicjalizacja aplikacji.
    @FXML
    public void initialize() {
        // Przejście powolnego pojawiania się.
        FadeTransition ft = new FadeTransition(Duration.seconds(2), loginPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        versionLabel.setText(Constants.appVersion);

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

        loginButton.setDefaultButton(true);

        visiblePasswordField.setVisible(false);
        passwordField.setVisible(true);

        // Synchronizacja widoczności i zarządzania layoutem
        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());

        // Wspólna wartość tekstu między polami
        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        // Załaduj ikony
        eyeOpenIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_open_icon.png")).toExternalForm());
        eyeClosedIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_closed_icon.png")).toExternalForm());

        // Obiekt ImageView, który dynamicznie zmienia ikonę
        eyeIcon = new ImageView(eyeClosedIcon);
        eyeIcon.setFitWidth(20);
        eyeIcon.setFitHeight(20);
        eyeIcon.setPreserveRatio(true);

        togglePasswordButton.setGraphic(eyeIcon);

        togglePasswordButton.selectedProperty().addListener((_, _, isNowSelected) -> {
            // Zmień pole
            passwordField.setVisible(!isNowSelected);
            visiblePasswordField.setVisible(isNowSelected);

            // Zmień ikonę
            eyeIcon.setImage(isNowSelected ? eyeOpenIcon : eyeClosedIcon);

            // Zmień fokus i kursor
            if (isNowSelected) {
                visiblePasswordField.requestFocus();
                visiblePasswordField.positionCaret(visiblePasswordField.getText().length());
            } else {
                passwordField.requestFocus();
                passwordField.positionCaret(passwordField.getText().length());
            }
        });
    }

    // Uchwyt do bazy danych.
    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Wyświetlenie odpowiedniej informacji na temat wprowadzonych danych.
        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty() && password.isEmpty()) {
                errorMessage.setText("Proszę wypełnić wszystkie pola!");
            }
            else if (username.isEmpty()) {
                errorMessage.setText("Proszę wprowadzić login!");
            }
            else {
                errorMessage.setText("Proszę wprowadzić hasło!");
            }
            clearErrorAfterDelay();
            return;
        }
        try (Connection connection = DatabaseConnector.getConnection()) {
            String loginSQL = "SELECT * FROM loginy WHERE login = ? AND haslo = ?";
            var statement = connection.prepareStatement(loginSQL);
            statement.setString(1, username);
            statement.setString(2, password);

            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/app/mainPage.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else {
                errorMessage.setText("Niepoprawny login lub hasło.");
                clearErrorAfterDelay();
            }
        } catch (SQLException | IOException e) {
            errorMessage.setText("Błąd połączenia z bazą.");
        }
    }
}