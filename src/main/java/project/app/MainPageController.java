package project.app;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainPageController {

    // Główny element strony.
    @FXML
    private StackPane mainPane;

    @FXML
    public void initialize() {
        FadeTransition ft = new FadeTransition(Duration.seconds(2), mainPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
