package project.app.utils;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import project.app.interfaces.UserDataReceiver;

import java.io.IOException;

public class PageManagerUtils {
    public static void showInitialPage(Stage stage, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(PageManagerUtils.class.getResource(fxmlPath));
            Parent root = loader.load();

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.centerOnScreen();
        } catch (Exception e) {
            showErrorDialog("Błąd ładowania widoku: " + fxmlPath + "\nSzczegóły: " + e.getMessage());
        }
    }

    public static void showInitialPageWithUserData(Stage stage, String fxmlPath, String imie, String position) {
        try {
            FXMLLoader loader = new FXMLLoader(PageManagerUtils.class.getResource(fxmlPath));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof UserDataReceiver) {
                ((UserDataReceiver) controller).initUserData(imie, position);
            }

            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();

        } catch (Exception e) {
            showErrorDialog("Błąd ładowania widoku: " + fxmlPath + "\nSzczegóły: " + e.getMessage());
        }
    }

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

            fadeOut.setOnFinished(_ -> {
                targetPane.getChildren().clear();
                targetPane.getChildren().add(content);
                AnchorPane.setTopAnchor(content, 0.0);
                AnchorPane.setBottomAnchor(content, 0.0);
                AnchorPane.setLeftAnchor(content, 0.0);
                AnchorPane.setRightAnchor(content, 0.0);

                targetPane.setTranslateY(20);
                targetPane.setOpacity(0);
                targetPane.setScaleX(0.97);
                targetPane.setScaleY(0.97);
                targetPane.setEffect(new DropShadow(15, Color.rgb(0, 0, 0, 0.2)));

                FadeTransition fadeIn = new FadeTransition(Duration.millis(400), targetPane);
                fadeIn.setToValue(1.0);
                fadeIn.setInterpolator(Interpolator.EASE_IN);

                TranslateTransition slideUp = new TranslateTransition(Duration.millis(400), targetPane);
                slideUp.setToY(0);
                slideUp.setInterpolator(Interpolator.EASE_OUT);

                ScaleTransition scaleUp = new ScaleTransition(Duration.millis(400), targetPane);
                scaleUp.setToX(1.0);
                scaleUp.setToY(1.0);
                scaleUp.setInterpolator(Interpolator.EASE_OUT);

                ParallelTransition reveal = new ParallelTransition(fadeIn, slideUp, scaleUp);
                reveal.play();
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

