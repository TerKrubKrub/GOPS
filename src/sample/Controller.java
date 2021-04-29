package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Deck deck = new Deck();
    private User user = new User("Copter");
    private Enemy bot = new Enemy();
    private int mostInHand;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deck.shuffle();
        mostInHand = 15;
    }

    public void startGame() throws IllegalAccessException {
        for(int i = 0; i < Card.Value.values().length; i++) {
            user.hand.add(deck.drawCard());
            bot.hand.add(deck.drawCard());
        }
    }

    public void selectCard(ActionEvent e) {
        String id = ((Button)e.getSource()).getId();

    }

    public int getValueFromCard(String id) {
        for(int i = 0; i < mostInHand; i++){
            if(id.equals("select" + i)){
                return user.hand.get(i).getScore();
            }
        }
        return 0;
    }

}
