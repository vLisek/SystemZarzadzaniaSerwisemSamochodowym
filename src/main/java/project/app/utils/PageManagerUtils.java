package project.app.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PageManagerUtils {
    public static void showPageInSameWindow(String fxmlPath, Node anyNodeFromScene, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(PageManagerUtils.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage currentStage = (Stage) anyNodeFromScene.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(title);
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

            targetPane.getChildren().clear();
            targetPane.getChildren().add(content);
            AnchorPane.setTopAnchor(content, 0.0);
            AnchorPane.setBottomAnchor(content, 0.0);
            AnchorPane.setLeftAnchor(content, 0.0);
            AnchorPane.setRightAnchor(content, 0.0);

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

