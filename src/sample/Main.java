package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Card.Color[] colors = Card.Color.values();
        System.out.println(colors[0]);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
