package Source;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button startButton, tutorialButton, scoreButton, aboutButton, exitButton, preGameDoneButton;
    @FXML
    private ChoiceBox<String> botSelection = new ChoiceBox<String>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botSelection.getItems().addAll("1 Bot", "2 Bots", "3 Bots");
        botSelection.setValue("1 Bot");
    }

    public void pressStart() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PreGame.fxml")));
        Stage window = (Stage) startButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void pressTutorial() {
    }

    public void pressScore() {
    }

    public void pressAbout(ActionEvent actionEvent) {
    }

    public void pressExit() {
        System.exit(0);
    }

    public void pressPreGameDone() throws IOException {

        if (botSelection.getValue().toString().equals("1 Bot")) {
            System.out.println("1 Bot");
        } else if (botSelection.getValue().toString().equals("2 Bots")) {
            System.out.println("2 Bots");
        } else if (botSelection.getValue().toString().equals("3 Bots")) {
            System.out.println("3 Bots");
        }
    }

}
