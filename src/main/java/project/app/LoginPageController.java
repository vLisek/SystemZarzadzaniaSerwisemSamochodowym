package project.app;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import java.sql.Connection;

@SuppressWarnings("ALL")
public class LoginPageController {

    @FXML
    private StackPane loginPane;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessage;

    @FXML
    public void initialize() {
        FadeTransition ft = new FadeTransition(Duration.seconds(2), loginPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    public void handleCustomer() {
        System.out.println("Wybrano klienta!");
    }

    @FXML
    public void handleEmployee() {
        System.out.println("Wybrano pracownika!");
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Proszę wypełnić wszystkie pola.");
            return;
        }
        try (Connection conn = DatabaseConnector.connect()) {
            String sql = "SELECT * FROM loginy WHERE login = ? AND haslo = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Zalogowano pomyślnie!");
                alert.showAndWait();
            } else {
                errorMessage.setText("Niepoprawny login lub hasło.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage.setText("Błąd połączenia z bazą.");
        }
    }
}
