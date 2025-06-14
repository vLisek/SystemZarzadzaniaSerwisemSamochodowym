package project.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.app.utils.AlertUtils;
import project.app.utils.Constants;
import project.app.utils.DatabaseConnector;
import project.app.utils.PageManagerUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPageController {

    @FXML
    private StackPane loginPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField visiblePasswordField;
    @FXML
    private ToggleButton togglePasswordButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label versionLabel;
    @FXML
    private Label dateLabel;
    private Image eyeOpenIcon;
    private Image eyeClosedIcon;
    private ImageView eyeIcon;

    @FXML
    public void initialize() {

        initializePasswordVisibilityToggle();

        versionLabel.setText(Constants.appVersion);
        dateLabel.setText(Constants.getCurrentDate());

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
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() && password.isEmpty()) {
            AlertUtils.showWarning("Błąd", "Wprowadź login i hasło.");
            return;
        } else if (username.isEmpty()) {
            AlertUtils.showWarning("Błąd", "Wprowadź login.");
            return;
        } else if (password.isEmpty()) {
            AlertUtils.showWarning("Błąd", "Wprowadź hasło.");
            return;
        }

        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT e.first_name, r.name AS role, e.position FROM logins l JOIN employees e ON l.employee_id = e.employee_id JOIN roles r ON e.role_id = r.id WHERE l.login = ? AND l.password = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("first_name");
                        String role = resultSet.getString("role");
                        String position = resultSet.getString("position");

                        Stage stage = (Stage) loginButton.getScene().getWindow();

                        switch (role.toLowerCase()) {
                            case "admin":
                                PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/admin/adminPage.fxml", name, position);
                                break;
                            case "manager":
                                PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/employees/managerPage.fxml", name, position);
                                break;
                            case "mechanic":
                                PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/employees/mechanicPage.fxml", name, position);
                                break;
                            case "receptionist":
                                PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/employees/receptionistPage.fxml", name, position);
                                break;
                            case "warehouseman":
                                PageManagerUtils.showInitialPageWithUserData(stage, "/project/app/employees/warehousemanPage.fxml", name, position);
                                break;
                            default:
                                AlertUtils.showError("Error", "Nieznana rola użytkownika: " + role);
                        }
                    } else {
                        AlertUtils.showWarning("Błąd", "Niepoprawny login lub hasło.");
                    }
                }
            }
        } catch (SQLException e) {
            AlertUtils.showError("Error", "Błąd połączenia z bazą: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleExit() {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        AlertUtils.showConfirmation("Potwierdzenie wyjścia", "Czy na pewno chcesz zamknąć aplikację?", currentStage::close);
    }

    private void initializePasswordVisibilityToggle() {

        visiblePasswordField.setVisible(false);
        passwordField.setVisible(true);

        visiblePasswordField.managedProperty().bind(visiblePasswordField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());

        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        eyeOpenIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_open_icon.png")).toExternalForm());
        eyeClosedIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/eye_closed_icon.png")).toExternalForm());

        eyeIcon = new ImageView(eyeClosedIcon);
        eyeIcon.setFitWidth(20);
        eyeIcon.setFitHeight(20);
        eyeIcon.setPreserveRatio(true);
        togglePasswordButton.setGraphic(eyeIcon);

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