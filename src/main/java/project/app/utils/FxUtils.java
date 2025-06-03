package project.app.utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class FxUtils {
    public static void clearErrorAfterDelay(Label label) {
        new Timeline(new KeyFrame(Duration.seconds(3), _ -> label.setText(""))).play();
    }
}
