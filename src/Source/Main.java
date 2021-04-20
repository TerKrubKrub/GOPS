package Source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Image icon = new Image("/Resource/icon.png");
        window.getIcons().add(icon);
        window.setTitle("GOPS: Shuffled!");
        window.setScene(new Scene(root, 1000, 700));
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
