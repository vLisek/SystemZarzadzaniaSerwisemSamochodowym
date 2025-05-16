package project.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginPage.fxml")));
        primaryStage.setTitle("REPAIRO");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/black_logo2048x2048.png"))));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //  Potwierdzenie zamykania programu.
        primaryStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie");
            alert.setHeaderText(null);
            alert.setContentText("Czy na pewno chcesz zamknąć aplikację?");

            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/black_logo2048x2048.png"))));

            ButtonType confirmButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(confirmButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() != confirmButton) {
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}