package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Deck deck;
    private User user;
    private Enemy bot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initVariable();
        deck.reset();
        deck.shuffle();
    }

    public void initVariable(){
        deck = new Deck();
        user = new User("Copter");
        bot = new Enemy();
    }

    public void startGame(ActionEvent e) throws IllegalAccessException {
        System.out.println("Start!!");
        for(int i = 0; i < Card.Value.values().length; i++) {
            user.hand.add(deck.drawCard());
            bot.hand.add(deck.drawCard());
        }

    }

    public void selectCard(ActionEvent e) {
        String id = ((Button)e.getSource()).getId();
        user.setBetScore(getValueFromCard(id));
        System.out.println(getValueFromCard(id));
    }

    public int getValueFromCard(String id) {
        for(int i = 0; i < user.hand.size(); i++){
            if(id.equals("select" + i)){
                return user.hand.get(i).getScore();
            }
        }
        return 0;
    }



}
