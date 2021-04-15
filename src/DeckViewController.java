import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class DeckViewController implements Initializable {

    @FXML private ImageView deckImageView;
    @FXML private ImageView activeCardImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DeckOfCards deck = new DeckOfCards();
        deckImageView.setImage(deck.getBackOfCardImage());
    }
}
