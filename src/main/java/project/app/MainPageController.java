package project.app;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;

import java.io.IOException;

@SuppressWarnings("CallToPrintStackTrace")
public class MainPageController {

    @FXML
    private StackPane mainPane;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        FadeTransition ft = new FadeTransition(Duration.seconds(2), mainPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    @FXML
    private void handleLogout(ActionEvent ignoredEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/app/LoginPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Logowanie");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
