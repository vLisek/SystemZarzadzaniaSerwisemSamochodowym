package project.app.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class ConfirmationHandler {
    private static final String ICON_PATH = "/images/black_logo.png";

    public static void show(String title, String message, Runnable onConfirm) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(ConfirmationHandler.class.getResourceAsStream(ICON_PATH))));

        ButtonType confirmButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            onConfirm.run();
        }
    }
}
