package project.app;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import project.app.utils.ConfirmationHandler;
import project.app.utils.PageManagerUtils;

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
    private void handleLogout() {
        ConfirmationHandler.show("Potwierdzenie", "Czy na pewno chcesz się wylogować?",
                () -> PageManagerUtils.showPageInSameWindow("/project/app/loginPage.fxml", logoutButton, "Ekran logowania"));
    }
}
