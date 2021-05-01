package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Deck deck;
    private User user;
    private Enemy bot;
    private int mostBet;
    private int indexSelectedCard;
    private int totalBet;

    @FXML
    private ImageView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, selectedCardView, battleCardView,
            cardBot0, cardBot1, cardBot2, cardBot3, cardBot4, cardBot5, cardBot6, cardBot7, cardBot8, cardBot9, cardBot10, cardBot11, cardBot12, cardBot13, cardBot14;
    @FXML
    private Button drawBtn, undoBtn, battleBtn, selectedBtn, closeButton, minimizeButton;
    @FXML
    private Image selectedCard, backCard;
    private ArrayList<ImageView> imgList, imgBotList;
    private boolean checkSelected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initVariable();
        initCardImageView();
        deck.reset();
        deck.shuffle();
        try {
            handOutCards();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        renderCardImageViewStart();
        drawBtn.setVisible(true);
        battleBtn.setVisible(true);
    }

    public void battle() {
        if (user.betPoint > bot.betPoint) {
            user.money += totalBet;
        } else if (user.betPoint < bot.betPoint) {
            bot.money += totalBet;
        } else {
            user.money += user.betMoney;
            bot.money += bot.betMoney;
        }
    }

    public void battleBtn() {
        battle();
        battleReset();
    }

    public void battleReset() {
        user.betMoney = 0;
        bot.betMoney = 0;
        totalBet = 0;
    }

    public void draw() throws IllegalAccessException {
        Card battleCard = deck.drawCard();
        battleCardView.setImage(battleCard.getImage());
    }

    public void handOutCards() throws IllegalAccessException {
        for (int i = 0; i < Card.Value.values().length; i++) {
            user.hand.add(deck.drawCard());
            bot.hand.add(deck.drawCard());
        }
    }

    public void initVariable() {
        deck = new Deck();
        user = new User("Copter");
        bot = new Enemy();
        mostBet = 100;
        checkSelected = false;
        backCard = new Image("Resource/Card/back.png");
        selectedCardView.setVisible(false);
        drawBtn.setVisible(false);
        undoBtn.setVisible(false);
        battleBtn.setVisible(false);
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

        imgBotList = new ArrayList<ImageView>();
        imgBotList.add(cardBot0);
        imgBotList.add(cardBot1);
        imgBotList.add(cardBot2);
        imgBotList.add(cardBot3);
        imgBotList.add(cardBot4);
        imgBotList.add(cardBot5);
        imgBotList.add(cardBot6);
        imgBotList.add(cardBot7);
        imgBotList.add(cardBot8);
        imgBotList.add(cardBot9);
        imgBotList.add(cardBot10);
        imgBotList.add(cardBot11);
        imgBotList.add(cardBot12);
        imgBotList.add(cardBot13);
        imgBotList.add(cardBot14);
    }

    public int getAmountBetFromBtn(String id) {
        for (int i = 10; i <= mostBet; i += 10) {
            if (id.equals("bet" + i)) {
                return i;
            }
        }
        return 0;
    }

    public Image getImageFromCard(String id) {
        for (int i = 0; i < user.hand.size(); i++) {
            if (id.equals("select" + i)) {
                return user.hand.get(i).getImage();
            }
        }
        return null;
    }

    public int getObjectFromBtn(String id) {
        for (int i = 0; i < user.hand.size(); i++) {
            if (id.equals("select" + i)) {
                return i;
            }
        }
        return -1;
    }

    public int getValueFromCard(String id) {
        for (int i = 0; i < user.hand.size(); i++) {
            if (id.equals("select" + i)) {
                return user.hand.get(i).getScore();
            }
        }
        return 0;
    }

    public void renderCard(ImageView imgView) {
        imgView.setVisible(true);
    }

    public void renderCardImageViewStart() {
        for (int i = 0; i < imgList.size(); i++) {
            imgList.get(i).setImage(user.hand.get(i).getImage());
        }

        for (int i = 0; i < imgBotList.size(); i++) {
            imgBotList.get(i).setImage(backCard);
        }
    }

    public void removeCard(ImageView imgView) {
        imgView.setVisible(false);
    }

    public void selectBetBtn(ActionEvent e) {
        String id = ((Button) e.getSource()).getId();
        if (user.getMoney() >= getAmountBetFromBtn(id)) {
            user.bet(getAmountBetFromBtn(id));
            totalBet += getAmountBetFromBtn(id);
            System.out.println(user.getBetMoney());
        } else System.out.println("Can't bet more than your money!!!");
    }

    public void selectCardBtn(ActionEvent e) {
        if (!checkSelected) {
            String id = ((Button) e.getSource()).getId();
            indexSelectedCard = getObjectFromBtn(id);
            user.setBetPoint(getValueFromCard(id));
            System.out.println(getValueFromCard(id));
            selectedCard = getImageFromCard(id);
            selectedCardView.setImage(backCard);
            renderCard(selectedCardView);
            removeCard(imgList.get(getObjectFromBtn(id)));
            selectedBtn = (Button) e.getSource();
            selectedBtn.setVisible(false);
            undoBtn.setVisible(true);
            checkSelected = true;
        } else System.out.println("Can't choose 2 card together");
    }

    public void undoBtn() {
        if (checkSelected) {
            Image undoCard = selectedCard;
            removeCard(selectedCardView);
            for (int i = 0; i < user.hand.size(); i++) {
                if (indexSelectedCard == i) {
                    imgList.get(i).setImage(undoCard);
                    renderCard(imgList.get(i));
                }
            }
            selectedBtn.setVisible(true);
            undoBtn.setVisible(false);
            user.betPoint = 0;
            checkSelected = false;
        } else System.out.println("No card on battle");
    }

    public void pressCloseButton() {
        Stage window = (Stage) closeButton.getScene().getWindow();
        window.close();
    }

    public void pressMinimizeButton() {
        Stage window = (Stage) minimizeButton.getScene().getWindow();
        window.setIconified(true);
    }
}
