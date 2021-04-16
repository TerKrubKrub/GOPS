import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;

public class DeckOfCards {
    private ArrayList<Card> deck;
    private Image backOfCardImage;

    public DeckOfCards(ArrayList<Card> deck) {
        this.deck = deck;
        backOfCardImage = new Image("images/backOfCard.png");
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
        backOfCardImage = new Image("images/backOfCard.png");
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

    /**
     * This Fuck you
     */
    public Card dealTopCard(){
        if(deck.size()>0)
            return deck.remove(0);
        else
            return null;
    }

    /**
     * This method will shuffle the deck of cards
     */
    public void shuffle(){
        Collections.shuffle(deck);
    }
}