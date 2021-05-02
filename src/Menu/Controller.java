package Menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class Controller extends Main {

    @FXML
    private Button startBtn, tutorialBtn, aboutBtn, backBtn, minimizeBtn, closeBtn;

    public void pressStartBtn() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Game/Game.fxml")));
        Stage window = (Stage) startBtn.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressTutorialBtn() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Tutorial.fxml")));
        Stage window = (Stage) tutorialBtn.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressAboutBtn() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("About.fxml")));
        Stage window = (Stage) aboutBtn.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressCloseBtn() {
        Stage window = (Stage) closeBtn.getScene().getWindow();
        window.close();
    }

    public void pressMinimizeBtn() {
        Stage window = (Stage) minimizeBtn.getScene().getWindow();
        window.setIconified(true);
    }

    public void pressBackBtn() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage window = (Stage) backBtn.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }
}
