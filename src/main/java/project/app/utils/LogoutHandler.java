package project.app.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogoutHandler {

    public static void handleLogout(Stage ownerStage) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.setContentText("Czy na pewno chcesz się wylogować?");

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(Objects.requireNonNull(LogoutHandler.class.getResourceAsStream("/images/black_logo.png"))));

        ButtonType confirmButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(confirmButton, cancelButton);

        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
                FXMLLoader loader = new FXMLLoader(LogoutHandler.class.getResource("/project/app/LoginPage.fxml"));
                Parent root = loader.load();
                ownerStage.setScene(new Scene(root));
        }
    }
}
