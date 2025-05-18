package project.app.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class ExitHandler {

    public static void handleExit(Stage ignoredStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Czy na pewno chcesz zamknąć program?");

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(ExitHandler.class.getResourceAsStream("/images/black_logo.png"))));

        ButtonType confirmButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            System.exit(0);
        }
    }
}
