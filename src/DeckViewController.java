import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class DeckViewController implements Initializable {

    @FXML private Button nextCardButton;
    @FXML private ImageView deckImageView;
    @FXML private ImageView activeCardImageView;
    private DeckOfCards deck;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deck = new DeckOfCards();
        deck.shuffle();
        deckImageView.setImage(deck.getBackOfCardImage());
    }

    @FXML public void nextCardButtonPushed(){
        this.activeCardImageView.setImage(deck.dealTopCard().getImage());
    }
}
