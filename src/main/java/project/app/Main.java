package project.app;

// Lista import'ów:
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();
        LoginPageController controller = loader.getController();

        // Wprowadzanie wersji.
        controller.setVersion("v1.1.2 BETA");

        primaryStage.setTitle("REPAIRO");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/black_logo2048x2048.png"))));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            controller.handleExit();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}