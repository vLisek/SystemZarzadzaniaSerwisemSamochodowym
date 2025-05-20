package project.app;

// Lista import'ów:
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project.app.utils.ConfirmationHandler;
import project.app.utils.Constants;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle(Constants.appName + " " + Constants.appVersion);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/black_logo.png"))));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            ConfirmationHandler.show("Zamykanie aplikacji", "Czy na pewno chcesz zamknąć aplikację?", primaryStage::close
            );
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}