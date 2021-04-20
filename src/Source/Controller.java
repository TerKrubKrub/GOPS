package Source;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Source.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button startButton, tutorialButton, scoreButton, aboutButton, exitButton,
            preGameDoneButton, preGameBackButton, minimizeButton, closeButton;
    @FXML
    private ChoiceBox<String> botSelection = new ChoiceBox<String>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botSelection.getItems().addAll("1 Bot", "2 Bots", "3 Bots");
        botSelection.setValue("1 Bot");
    }

    public void moveWindow(Parent root, Stage window) {
        final double[] xOffset = {0};
        final double[] yOffset = {0};

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset[0] = event.getSceneX();
                yOffset[0] = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.setX(event.getScreenX() - xOffset[0]);
                window.setY(event.getScreenY() - yOffset[0]);
            }
        });
    }

    public void pressStart() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PreGame.fxml")));
        Stage window = (Stage) startButton.getScene().getWindow();
        moveWindow(root, window);
        window.setScene(new Scene(root));
    }

    public void pressTutorial() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Tutorial.fxml")));
        Stage window = (Stage) tutorialButton.getScene().getWindow();
        moveWindow(root, window);
        window.setScene(new Scene(root));
    }

    public void pressScore() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Score.fxml")));
        Stage window = (Stage) scoreButton.getScene().getWindow();
        moveWindow(root, window);
        window.setScene(new Scene(root));
    }

    public void pressAbout() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("About.fxml")));
        Stage window = (Stage) aboutButton.getScene().getWindow();
        moveWindow(root, window);
        window.setScene(new Scene(root));
    }

    public void pressClose() {
        Stage window = (Stage) closeButton.getScene().getWindow();
        window.close();
    }

    public void pressMinimize() {
        Stage window = (Stage) minimizeButton.getScene().getWindow();
        window.setIconified(true);
    }

    public void pressPreGameDone() throws IOException {
        if (botSelection.getValue().toString().equals("1 Bot")) {
            System.out.println("1 Bot");
        } else if (botSelection.getValue().toString().equals("2 Bots")) {
            System.out.println("2 Bots");
        } else if (botSelection.getValue().toString().equals("3 Bots")) {
            System.out.println("3 Bots");
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(".fxml")));
        Stage window = (Stage) preGameDoneButton.getScene().getWindow();
        moveWindow(root, window);
        window.setScene(new Scene(root));
    }

    public void pressPreGameBack() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage window = (Stage) preGameBackButton.getScene().getWindow();
        moveWindow(root, window);
        window.setScene(new Scene(root));
    }
}
