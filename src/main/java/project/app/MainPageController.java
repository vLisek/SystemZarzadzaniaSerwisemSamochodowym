package project.app;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.app.utils.LogoutHandler;

import java.io.IOException;

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

    public void handleLogout(ActionEvent actionEvent) {
        logoutButton.setOnAction(event -> {
            try {
                LogoutHandler.handleLogout((Stage) logoutButton.getScene().getWindow());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
