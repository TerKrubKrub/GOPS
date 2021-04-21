package Source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button startButton, tutorialButton, scoreButton, aboutButton, exitButton,
            doneButton, backButton, minimizeButton, closeButton;
    @FXML
    private ChoiceBox<String> botSelection = new ChoiceBox<>();
    @FXML
    private TextField textFPlayerName;
    private File file;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.file = new File("Score.dat");
        botSelection.getItems().addAll("1 Bot", "2 Bots", "3 Bots");
        botSelection.setValue("1 Bot");
    }

    public void moveWindow(Parent root, Stage window) {
        final double[] xOffset = {0};
        final double[] yOffset = {0};

        root.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset[0]);
            window.setY(event.getScreenY() - yOffset[0]);
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
        if (exitButton.isPressed() || closeButton.isPressed()) {
            System.out.println("Exit");
        }
    }

    public void pressMinimize() {
        Stage window = (Stage) minimizeButton.getScene().getWindow();
        window.setIconified(true);
    }

    public void pressDone() throws IOException {
        switch (botSelection.getValue()) {
            case "1 Bot" -> System.out.println("1 Bot");
            case "2 Bots" -> System.out.println("2 Bots");
            case "3 Bots" -> System.out.println("3 Bots");
        }

        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream savedName = new DataOutputStream(fos);
        if (textFPlayerName.getText().equals("")) {
            textFPlayerName.setText("Unnamed");
        }
        savedName.writeUTF(textFPlayerName.getText());
        System.out.println(textFPlayerName.getText());

//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(".fxml")));
//        Stage window = (Stage) preGameDoneButton.getScene().getWindow();
//        moveWindow(root, window);
//        window.setScene(new Scene(root));
    }

    public void pressBack() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Menu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        moveWindow(root, window);
        window.setScene(new Scene(root));
    }
}
