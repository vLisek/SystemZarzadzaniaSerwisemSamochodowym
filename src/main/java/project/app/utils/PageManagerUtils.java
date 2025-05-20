package project.app.utils;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PageManagerUtils {
    public static void showPageInSameWindow(String fxmlPath, Node anyNodeFromScene) {
        try {
            FXMLLoader loader = new FXMLLoader(PageManagerUtils.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage currentStage = (Stage) anyNodeFromScene.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.setResizable(false);
            currentStage.centerOnScreen();

        } catch (Exception e) {
            showErrorDialog("Błąd ładowania widoku: " + fxmlPath + "\nSzczegóły: " + e.getMessage());
        }
    }

    public static void loadPageIntoAnchorPane(String fxmlPath, AnchorPane targetPane) {
        try {
            FXMLLoader loader = new FXMLLoader(PageManagerUtils.class.getResource(fxmlPath));
            Parent content = loader.load();

            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), targetPane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            fadeOut.setOnFinished(event -> {
                targetPane.getChildren().clear();
                targetPane.getChildren().add(content);
                AnchorPane.setTopAnchor(content, 0.0);
                AnchorPane.setBottomAnchor(content, 0.0);
                AnchorPane.setLeftAnchor(content, 0.0);
                AnchorPane.setRightAnchor(content, 0.0);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), targetPane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (IOException e) {
            showErrorDialog("Błąd ładowania widoku: " + fxmlPath + "\nSzczegóły: " + e.getMessage());
        }
    }

    private static void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

