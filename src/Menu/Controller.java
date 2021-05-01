package Menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller extends Main implements Initializable {

    @FXML
    private Button startButton, tutorialButton, scoreButton, aboutButton, exitButton,
            doneButton, backButton, minimizeButton, closeButton;

    @FXML
    private TextField textFPlayerName;
    private File scoreFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.scoreFile = new File("src/File/Score.dat");
    }

    public void pressStartButton() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GetName.fxml")));
        Stage window = (Stage) startButton.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressTutorialButton() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Tutorial.fxml")));
        Stage window = (Stage) tutorialButton.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressScoreButton() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Score.fxml")));
        Stage window = (Stage) scoreButton.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressAboutButton() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("About.fxml")));
        Stage window = (Stage) aboutButton.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressCloseButton() {
        Stage window = (Stage) closeButton.getScene().getWindow();
        window.close();
    }

    public void pressMinimizeButton() {
        Stage window = (Stage) minimizeButton.getScene().getWindow();
        window.setIconified(true);
    }

    public void pressDoneButton() throws IOException {
        FileOutputStream fos = new FileOutputStream(scoreFile);
        DataOutputStream savedName = new DataOutputStream(fos);
        if (textFPlayerName.getText().equals("")) {
            textFPlayerName.setText("Unnamed");
        }
        savedName.writeUTF(textFPlayerName.getText());
        System.out.println(textFPlayerName.getText());

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Game/Game.fxml")));
        Stage window = (Stage) doneButton.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }

    public void pressBackButton() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        moveStage(root, window);
        window.setScene(new Scene(root));
    }
}
