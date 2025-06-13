package project.app;

// Lista import'ów:
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project.app.utils.ConfirmationHandler;
import project.app.utils.Constants;
import project.app.utils.PageManagerUtils;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Constants.appName + " " + Constants.appVersion);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/black_logo.png"))));
        PageManagerUtils.showInitialPage(primaryStage, "/project/app/common/loginPage.fxml");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            ConfirmationHandler.show("Zamykanie aplikacji", "Czy na pewno chcesz zamknąć aplikację?", primaryStage::close);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}