package Source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;

public class Main extends Application {

    private double[] xOffset = {0};
    private double[] yOffset = {0};

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Scene scene = new Scene(root, 1000, 700);
        window.setScene(scene);
        window.setTitle("GOPS: Shuffled!");
        window.initStyle(StageStyle.TRANSPARENT);
        moveStage(root, window);
        Image icon = new Image("/Resource/icon.png");
        window.getIcons().add(icon);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void moveStage(Parent root, Stage window) {
        root.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset[0]);
            window.setY(event.getScreenY() - yOffset[0]);
        });
    }

}
