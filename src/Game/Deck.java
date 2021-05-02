package Game;

import java.util.Random;
import javafx.scene.image.Image;

public class Deck {
    private Card[] cards;
    private int cardsInDeck;
    private Image[] img;

    public Deck() {
        initVariable();
    }

    public void initVariable() {
        cards = new Card[45];
        img = new Image[45];
    }

    public Card drawCard() throws IllegalAccessException {
        if(isEmpty()){
            throw new IllegalAccessException("No card in the deck");
        }
        return cards[--cardsInDeck];
    }

    public Card[] getCards() {
        return cards;
    }

    public boolean isEmpty() {
        return cardsInDeck == 0;
    }

    public void reset() {
        Card.Color[] color = Card.Color.values();
        cardsInDeck = 0;
        for(int i = 0; i < Card.Color.values().length; i++){
            for(int j = 1; j <= 15; j++){
                img[cardsInDeck] = new Image("./Resource/Card/" + color[i] + "_" + j + ".png");
                cards[cardsInDeck] = new Card(color[i], Card.Value.getValue(j), img[cardsInDeck]);
                cardsInDeck++;
            }
        }
    }

    public void shuffle() {
        int n = cards.length;
        Random random = new Random();

        for(int i = 0; i < cards.length; i++){
            // Get a random index of the array
            // Swap the random element

            int randomValue = i + random.nextInt(n - i);
            Card randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }

    public int getCardsInDeck() {
        return this.cardsInDeck;
    }
}
