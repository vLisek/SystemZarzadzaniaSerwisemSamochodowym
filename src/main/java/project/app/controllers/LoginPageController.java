package project.app.controllers;

// JavaFX
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

// Java SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Java Util
import java.util.Objects;

// Klasy
import project.app.utils.Constants;
import project.app.utils.DatabaseConnector;
import project.app.utils.PageManagerUtils;
import project.app.utils.ConfirmationHandler;

public class LoginPageController {

    // ---------------------------
    // Elementy FXML
    // ---------------------------

    // Kontenery
    @FXML private StackPane loginPane;

    // Pola formularza
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField visiblePasswordField;

    // Przyciski
    @FXML private ToggleButton togglePasswordButton;
    @FXML private Button loginButton;
    @FXML private Button exitButton;

    // Wyświetlanie informacji
    @FXML private Label errorMessage;
    @FXML private Label versionLabel;
    @FXML private Label dateLabel;


    // ---------------------------
    // Pola kontrolera
    // ---------------------------
    private Image eyeOpenIcon;
    private Image eyeClosedIcon;
    private ImageView eyeIcon;


    // ---------------------------
    // Metody publiczne
    // ---------------------------
    @FXML
    public void initialize() {

        // Wywołanie metody "Pokaż / ukryj hasło".
        initializePasswordVisibilityToggle();

        // Wyświetlenie wersji aplikacji i aktualnej daty w odpowiednich labelach.
        versionLabel.setText(Constants.appVersion);
        dateLabel.setText(Constants.getCurrentDate());

        // Obsługa kliknięcia poza pole formularza logowania i usunięcie focusu.
        loginPane.setOnMouseClicked(event -> {
            Node target = (Node) event.getTarget();
            if (target != usernameField && target != passwordField && target != loginButton) {
                loginPane.requestFocus();
            }
        });

        loginButton.setDefaultButton(true);
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() && password.isEmpty()) {
            errorMessage.setText("Wprowadź login i hasło.");
            clearErrorAfterDelay();
            return;
        }
        else if (username.isEmpty()) {
            errorMessage.setText("Wprowadź login.");
            clearErrorAfterDelay();
            return;
        }
        else if (password.isEmpty()) {
            errorMessage.setText("Wprowadź hasło.");
            clearErrorAfterDelay();
            return;
        }

        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT p.imie, p.rola, p.stanowisko FROM loginy l JOIN pracownicy p ON l.pracownik_id = p.id WHERE l.login = ? AND l.haslo = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("p.imie");
                String role = resultSet.getString("p.rola");
                String position = resultSet.getString("p.stanowisko");
                Stage stage = (Stage) loginButton.getScene().getWindow();

                switch (role.toLowerCase()) {
                    case "właściciel":
                        PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/adminPage.fxml", name, position);
                        break;
                    case "kierownik":
                        PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/managerPage.fxml", name, position);
                        break;
                    case "mechanik":
                        PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/mechanicPage.fxml", name, position);
                        break;
                    case "recepcjonista":
                        PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/receptionistPage.fxml", name, position);
                        break;
                    case "magazynier":
                        PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/warehousemanPage.fxml", name, position);
                        break;
                    default:
                        errorMessage.setText("Nieznana rola użytkownika.");
                        clearErrorAfterDelay();
                }
            }
            else {
                errorMessage.setText("Niepoprawny login lub hasło.");
                clearErrorAfterDelay();
            }

        } catch (SQLException e) {
            errorMessage.setText("Błąd połączenia z bazą.");
            clearErrorAfterDelay();
        }
    }

    @FXML
    public void handleExit() {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        ConfirmationHandler.show("Potwierdzenie wyjścia", "Czy na pewno chcesz zamknąć aplikację?", currentStage::close);
    }


    // ---------------------------
    // Metody prywatne
    // ---------------------------
    private void clearErrorAfterDelay() {
        new Timeline(new KeyFrame(Duration.seconds(3), _ -> errorMessage.setText(""))).play();
    }

    private void initializePasswordVisibilityToggle() {

        // Domyślna widoczność pól.
        visiblePasswordField.setVisible(false);
        passwordField.setVisible(true);

        // Zarządzanie layoutem.
        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());

        // Synchronizacja tekstu.
        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        // Ładowanie ikon.
        eyeOpenIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_open_icon.png")).toExternalForm());
        eyeClosedIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_closed_icon.png")).toExternalForm());

        // Konfiguracja przycisku.
        eyeIcon = new ImageView(eyeClosedIcon);
        eyeIcon.setFitWidth(20);
        eyeIcon.setFitHeight(20);
        eyeIcon.setPreserveRatio(true);
        togglePasswordButton.setGraphic(eyeIcon);

        // Obsługa kliknięcia przycisku.
        togglePasswordButton.selectedProperty().addListener((_, _, isNowSelected) -> {
            passwordField.setVisible(!isNowSelected);
            visiblePasswordField.setVisible(isNowSelected);
            eyeIcon.setImage(isNowSelected ? eyeOpenIcon : eyeClosedIcon);

            if (isNowSelected) {
                visiblePasswordField.requestFocus();
                visiblePasswordField.positionCaret(visiblePasswordField.getText().length());
            } else {
                passwordField.requestFocus();
                passwordField.positionCaret(passwordField.getText().length());
            }
        });
    }
}