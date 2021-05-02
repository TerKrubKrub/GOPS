package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Deck deck;
    private User user;
    private Enemy bot;
    private Card battleCard, botSelectedCard;

    private int mostBet;
    private int indexSelectedCard;
    private int totalBet;
    private int betMoneySelected;

    private String botAction;

    @FXML
    private ImageView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, selectedCardView, battleCardView,
            cardBot0, cardBot1, cardBot2, cardBot3, cardBot4, cardBot5, cardBot6, cardBot7, cardBot8, cardBot9, cardBot10, cardBot11, cardBot12, cardBot13, cardBot14,
            botSelectedCardView;

    @FXML
    private Button drawBtn, undoBtn, battleBtn, selectedBtn, closeButton, minimizeButton, betBtn, nextBtn;

    @FXML
    private Image selectedCard, backCard;

    @FXML
    private Label userMoney, botMoney, totalBetLabel;

    private ArrayList<ImageView> imgList, imgBotList;

    private boolean checkSelected;
    private boolean drawable;

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
        //battleBtn.setVisible(true);
    }

    public int getAmountBetFromBtn(String id) {
        for (int i = 10; i <= mostBet; i += 10) {
            if (id.equals("bet" + i)) {
                return i;
            }
            if (id.equals("unBet" + i)) {
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
                return user.hand.get(i).getPoint();
            }
        }
        return 0;
    }

    public void handOutCards() throws IllegalAccessException {
        for (int i = 0; i < Card.Value.values().length; i++) {
            user.hand.add(deck.drawCard());
            bot.hand.add(deck.drawCard());
        }
        user.sortByPoint();
        bot.sortByPoint();
    }

    public void initVariable() {
        deck = new Deck();
        user = new User("Copter");
        bot = new Enemy();

        mostBet = 100;
        betMoneySelected = 0;

        botAction = "";

        checkSelected = false;
        drawable = true;
        bot.playerTurn = true;
        user.playerTurn = false;

        backCard = new Image("Resource/Card/back.png");

//        updateMoney();
//        userMoney.setScaleX(2);
//        userMoney.setScaleY(2);
//        botMoney.setScaleX(2);
//        botMoney.setScaleY(2);
//        totalBetLabel.setScaleX(2);
//        totalBetLabel.setScaleY(2);

        removeCard(battleCardView);
        removeCard(selectedCardView);
        //removeCard(botSelectedCardView);
//        removeBtn(drawBtn);
//        removeBtn(undoBtn);
//        removeBtn(battleBtn);
//        removeBtn(battleBtn);

        removeBtn(undoBtn);
    }

    public void initCardImageView() {
        imgList = new ArrayList<>();
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

        imgBotList = new ArrayList<>();
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

    /////////////////////////////////////////Battle///////////////////////////////////

    public void battle() {
        if (user.betPoint > bot.betPoint) {
            user.win(totalBet);
            user.plusScore(battleCard.getPoint());
            System.out.println("User Wins");
        } else if (user.betPoint < bot.betPoint) {
            bot.win(totalBet);
            bot.plusScore(battleCard.getPoint());
            System.out.println("Bot Wins");
        } else {
            user.win(user.betMoney);
            bot.win(bot.betMoney);
            System.out.println("Draw");
        }
    }

    public void battleBtn() {
        if(checkSelected && user.betMoney != 0){
            battle();
            battleReset();
//            updateMoney();
            checkGameOver();
        }
    }

    public void checkGameOver() {
        if(user.money <= 0 || bot.money <= 0) {
            System.out.println("GAME OVER");
        }
    }

    public void battleReset() {
        user.betMoney = 0;
        bot.betMoney = 0;
        totalBet = 0;

        checkSelected = false;
        drawable = true;

//        updateMoney();
        removeCard(selectedCardView);
        removeCard(battleCardView);
        removeCard(botSelectedCardView);
        removeBtn(undoBtn);
    }

    public void call() {
        if(user.playerTurn) {
            if(bot.betMoney > user.betMoney) {
                totalBet += bot.betMoney - user.betMoney;
                user.bet(bot.betMoney - user.betMoney);
                switchTurn();
                battleBtn();
            }
            else System.out.println("Can't call");
        }
        else System.out.println("Not your turn");
    }

    public void botCall() {
        if(bot.playerTurn) {
            if (user.betMoney > bot.betMoney) {
                totalBet += user.betMoney - bot.betMoney;
                user.bet(user.betMoney - bot.betMoney);
                switchTurn();
                battleBtn();
            }
        }
    }

    public void draw() throws IllegalAccessException {
        if(drawable) {
            if(user.playerTurn) {
                battleCard = deck.drawCard();
                battleCardView.setImage(battleCard.getImage());
                //botSelectedCardView.setImage(backCard);
                //updateMoney();
                renderCard(battleCardView);
                //renderCard(botSelectedCardView);
                drawable = false;
            }
            if(bot.playerTurn) {
                battleCard = deck.drawCard();
                battleCardView.setImage(battleCard.getImage());
                botSelectedCardView.setImage(backCard);
                botSelectedCard = bot.botChooseCard(battleCard.getPoint(), bot.hand);
                //updateMoney();
                renderCard(battleCardView);
                renderCard(botSelectedCardView);
                drawable = false;
                switchTurn();
            }
        }
        else System.out.println("Can't draw more");
    }

    public void fold() {
        if(user.playerTurn) {
            bot.win(totalBet);
            bot.plusScore(battleCard.getPoint());
            System.out.println("Bot Wins");
            switchTurn();
            battleReset();
        }
        else System.out.println("Not your turn");
    }

    public void botFold() {

    }

    public void raise() {
        if(user.playerTurn && betMoneySelected != 0) {
            user.bet(betMoneySelected);
            betMoneySelected = 0;
            botAction = bot.botChooseActionFirst(battleCard.getPoint());
            if(botAction.equals("Call")) botCall();
            for(int i = 0; i <= 120; i += 10) {
                if(botAction.equals("Raise" + i)) {
                    botRaise(i);
                }
            }
        }
        else System.out.println("Not your turn");
    }

    public void botRaise(int amount) {

    }

    public void switchTurn() {
        user.playerTurn = !user.playerTurn;
        bot.playerTurn = !bot.playerTurn;
    }

    ////////////////////////////////////Render && Remove//////////////////////////////


    public void renderBtn(Button btn) {
        btn.setVisible(true);
    }

    public void renderCard(ImageView imgView) {
        imgView.setVisible(true);
    }

    public void renderCardImageViewStart() {
        for (int i = 0; i < imgList.size(); i++) {
            imgList.get(i).setImage(user.hand.get(i).getImage());
        }

        for (ImageView imageView : imgBotList) {
            imageView.setImage(backCard);
        }
    }

    public void removeBtn(Button btn) {
        btn.setVisible(false);
    }

    public void removeCard(ImageView imgView) {
        imgView.setVisible(false);
    }

    /////////////////////////////////////BUTTON//////////////////////////////////////

    public void betBtn() {
        if(user.playerTurn) {
            totalBet += betMoneySelected;
            betMoneySelected = 0;
        }
//      updateMoney();
        removeBtn(betBtn);
    }

    public void pressCloseButton() {
        Stage window = (Stage) closeButton.getScene().getWindow();
        window.close();
    }

    public void pressMinimizeButton() {
        Stage window = (Stage) minimizeButton.getScene().getWindow();
        window.setIconified(true);
    }

    public void selectBetBtn(ActionEvent e) {
        String id = ((Button) e.getSource()).getId();
        if (user.playerTurn) {
            user.bet(getAmountBetFromBtn(id));
            betMoneySelected += getAmountBetFromBtn(id);
//            updateMoney();
            System.out.println(user.getBetMoney());
        } else System.out.println("Not your turn");
    }

    public void selectUnBetBtn(ActionEvent e) {
        String id = ((Button) e.getSource()).getId();
        if (user.playerTurn) {
            user.unBet(getAmountBetFromBtn(id));
            betMoneySelected -= getAmountBetFromBtn(id);
            if(betMoneySelected < 0) betMoneySelected = 0;
//            updateMoney();
            System.out.println(user.getBetMoney());
        } else System.out.println("Not your turn");
    }

    public void selectCardBtn(ActionEvent e) {
        if (!checkSelected && user.playerTurn) {
            String id = ((Button) e.getSource()).getId();
            indexSelectedCard = getObjectFromBtn(id);
            user.setBetPoint(getValueFromCard(id));
            System.out.println(getValueFromCard(id));
            selectedCard = getImageFromCard(id);
            selectedCardView.setImage(backCard);
            renderCard(selectedCardView);
            removeCard(imgList.get(getObjectFromBtn(id)));
            selectedBtn = (Button) e.getSource();
            removeBtn(selectedBtn);
            renderBtn(undoBtn);
            checkSelected = true;
        } else System.out.println("Can't select");
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
            renderBtn(selectedBtn);
            removeBtn(undoBtn);
            user.betPoint = 0;
            checkSelected = false;
        } else System.out.println("No card on battle");
    }

    public void nextBtn() {
        switchTurn();
        //Bot place bet here.
        switchTurn();
    }

//    public void updateMoney() {
//        userMoney.setText("Money : " + user.money);
//        botMoney.setText("Money : " + bot.money);
//        totalBetLabel.setText("TOTALBET : " + totalBet);
//    }
}
