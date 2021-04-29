package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Deck deck;
    private User user;
    private Enemy bot;
    private int mostBet;

    @FXML
    private ImageView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14;
    private ArrayList<ImageView> imgList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initVariable();
        initCardImageView();
        deck.reset();
        deck.shuffle();
    }

    public void initVariable() {
        deck = new Deck();
        user = new User("Copter");
        bot = new Enemy();
        mostBet = 100;
    }

    public void initCardImageView() {
        imgList = new ArrayList<ImageView>();
        imgList.add(card0);
        imgList.add(card1);
        imgList.add(card2);
        imgList.add(card3);
        imgList.add(card4);
        imgList.add(card5);
        imgList.add(card6);
        imgList.add(card7);
        imgList.add(card8);
        imgList.add(card9);
        imgList.add(card10);
        imgList.add(card11);
        imgList.add(card12);
        imgList.add(card13);
        imgList.add(card14);
    }

    public void renderCardImageView() {
        for(int i = 0; i < imgList.size(); i++) {
            imgList.get(i).setImage(user.hand.get(i).getImage());
        }
    }

    public void startGame(ActionEvent e) throws IllegalAccessException {
        System.out.println("Start!!");
        handOutCards();
        ((Button)e.getSource()).setVisible(false);
        renderCardImageView();
    }

    public void handOutCards() throws IllegalAccessException {
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
        for(int i = 0; i < user.hand.size(); i++) {
            if(id.equals("select" + i)) {
                return user.hand.get(i).getScore();
            }
        }
        return 0;
    }

    public Image getImageFromCard(String id) {
        for(int i = 0; i < user.hand.size(); i++) {
            if(id.equals("card" + i)) {
                return user.hand.get(i).getImage();
            }
        }
        return null;
    }

    public void selectBet(ActionEvent e) {
        String id = ((Button)e.getSource()).getId();
        if(user.getMoney() >= getAmountBetFromBtn(id)) {
            user.bet(getAmountBetFromBtn(id));
            System.out.println(user.getBetMoney());
        }
        else System.out.println("Can't bet more than your money!!!");
    }

    public int getAmountBetFromBtn(String id) {
        for(int i = 10; i <= mostBet ; i+=10) {
            if (id.equals("bet" + i)) {
                return i;
            }
        }
        return 0;
    }

}
