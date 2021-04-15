import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class DeckOfCards {
    private ArrayList<Card> deck;
    private Image backOfCardImage;

    public DeckOfCards(ArrayList<Card> deck) {
        this.deck = deck;
        backOfCardImage = new Image("backOfCard.png");
    }

    public DeckOfCards(){
        List<String> suits = Card.getValidSuits();
        List<String> faceNames = Card.getValidFaceNames();
        deck = new ArrayList<>();
        for(String suit:suits)
        {
            for(String faceName:faceNames)
            {
                deck.add(new Card(faceName,suit));
            }
        }
        backOfCardImage = new Image("backOfCard.png");
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Image getBackOfCardImage() {
        return backOfCardImage;
    }

    public void setBackOfCardImage(Image backOfCardImage) {
        this.backOfCardImage = backOfCardImage;
    }
}
