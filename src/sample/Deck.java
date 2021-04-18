package sample;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private int cardsInDeck;

    public Deck(){
        cards = new Card[30];
    }

    public void reset(){
        Card.Color[] color = Card.Color.values();
        cardsInDeck = 0;
        for(int i = 0; i < Card.Color.values().length; i++){
            cards[cardsInDeck++] = new Card(color[i], Card.Value.getValue(0));

            for(int j = 1; j < 10; j++){
                cards[cardsInDeck++] = new Card(color[i], Card.Value.getValue(j));
            }
        }
    }

    public boolean isEmpty(){
        return cardsInDeck == 0;
    }

    public void shuffle(){
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

    public Card drawCard() throws IllegalAccessException{
        if(isEmpty()){
          throw new IllegalAccessException("No card in the deck");
        }
        return cards[--cardsInDeck];
    }

    public Card[] drawCard(int n) throws IllegalAccessException{
        Card[] ret = new Card[n];
        for(int i = 0; i < n; i++){
            ret[i] = cards[--cardsInDeck];
        }
        return ret;
    }
}
