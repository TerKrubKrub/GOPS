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
    private Deck deck, deckTemp;
    private User user;
    private Enemy bot;
    private Card battleCard, botSelectedCard;

    private int mostBet;
    private int indexSelectedCard;
    private int totalBet;
    private int betMoneySelected;
    private int drawCount;

    private String botAction;

    @FXML
    private ImageView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12, card13, card14, selectedCardView, battleCardView,
            cardBot0, cardBot1, cardBot2, cardBot3, cardBot4, cardBot5, cardBot6, cardBot7, cardBot8, cardBot9, cardBot10, cardBot11, cardBot12, cardBot13, cardBot14,
            botSelectedCardView;

    @FXML
    private Button drawBtn, undoBtn, selectedBtn, closeButton, minimizeButton;

    @FXML
    private Image selectedCard, backCard;

    @FXML
    private Label userBetMoney, botBetMoney, totalBetLabel, userBattlePt, botBattlePt, userMoney, botMoney, scoreLabel, betMoney, action;

    private ArrayList<ImageView> imgList, imgBotList;

    private boolean checkSelected;
    private boolean drawable;
    private boolean userFirst;
    private boolean addBet;
    private boolean removedCard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initVariable();
        initCardImageView();
        try {
            handOutCards();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        renderCardImageViewStart();
        drawBtn.setVisible(true);
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

    public int getObjectFromCard(Card card, ArrayList<Card> cards) {
        for (Card i : cards) {
            if(card == i){
                return cards.indexOf(i);
            }
        }
        return -1;
    }

    public void handOutCards() throws IllegalAccessException {
        for (int i = 0; i < Card.Value.values().length - 1; i++) {
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

        deck.reset();
        deck.shuffle();

        drawCount = 0;
        mostBet = 100;
        betMoneySelected = 0;

        botAction = "";

        checkSelected = false;
        drawable = true;
        bot.playerTurn = true;
        user.playerTurn = false;
        userFirst = true;
        addBet = true;
        removedCard = false;

        backCard = new Image("Resource/Card/back.png");

        updateLabel();

        removeCard(battleCardView);
        removeCard(selectedCardView);

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

    public void resetGame(){
        deckTemp = new Deck();
        deck = deckTemp;
        deck.shuffle();
        user.hand.clear();
        bot.hand.clear();
        try {
            handOutCards();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        renderCardImageViewStart();
    }

    public void battle() {
        if (user.betPoint > bot.betPoint) {
            user.win(totalBet);
            user.plusScore(battleCard.getPoint());
            System.out.println("User : " + user.betPoint + " Bot : " + bot.betPoint + " User Wins");
        } else if (user.betPoint < bot.betPoint) {
            bot.win(totalBet);
            bot.plusScore(battleCard.getPoint());
            System.out.println("User : " + user.betPoint + " Bot : " + bot.betPoint + " Bot Wins");
        } else {
            user.win(user.betMoney);
            bot.win(bot.betMoney);
            System.out.println("User : " + user.betPoint + " Bot : " + bot.betPoint + " Draw");
        }
        removedCard = false;
        drawable = true;
    }

    public void battleBtn() {
        if(checkSelected && user.betMoney >= 0){
            battle();
            showAllCardBattle();
            updateLabel();
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

        userFirst = !userFirst;
        user.playerTurn = userFirst;
        bot.playerTurn = !userFirst;

        updateLabel();

        removeCard(selectedCardView);
        removeCard(battleCardView);
        removeCard(botSelectedCardView);
        removeBtn(undoBtn);
    }

    public void call() {
        if(user.playerTurn && checkSelected) {
            if(bot.betMoney >= user.betMoney) {
                totalBet += (bot.betMoney - user.betMoney);
                user.bet(bot.betMoney - user.betMoney);
                switchTurn();
                removeBtn(undoBtn);
                if(!userFirst) battleBtn();
                else {
                    botSelectedCard = bot.botChooseCard(battleCard.getPoint(), bot.hand);
                    bot.betPoint = botSelectedCard.getPoint();
                    removeCard(imgBotList.get(getObjectFromCard(botSelectedCard, bot.hand)));
                    if(!removedCard) {
                        bot.hand.remove(botSelectedCard);
                        removedCard = true;
                    }
                    bot.sortByPoint();
                    botSelectedCardView.setImage(backCard);
                    renderCard(botSelectedCardView);
                    botAction = bot.botChooseActionSecond(bot.getPointAction());
                    botGetAction(botAction);
                    removeBtn(undoBtn);
                    System.out.println("botHand = " + bot.hand.size());
                }
            }
            else System.out.println("Can't call");
        }
        else System.out.println("Not your turn");
        updateLabel();
    }

    public void botCall() {
        if(bot.playerTurn) {
            if (user.betMoney >= bot.betMoney) {
                totalBet += (user.betMoney - bot.betMoney);
                bot.bet(user.betMoney - bot.betMoney);
                updateLabel();
                action.setText("BOT CALL!!!");
                System.out.println("Bot " + botAction);
                System.out.println("Bot bet : " + bot.betMoney);
                System.out.println("User bet : " + user.betMoney);
                switchTurn();
                if(userFirst) battleBtn();
            }
        }
    }

    public void draw() throws IllegalAccessException {
        if(drawCount == 15) resetGame();
        else{
            if(drawable) {
                drawCount++;
                battleReset();
                if(user.playerTurn) {
                    battleCard = deck.drawCard();
                    System.out.println(battleCard.getPoint());
                    battleCardView.setImage(battleCard.getImage());
                    renderCard(battleCardView);
                    drawable = false;
                    userFirst = true;
                }
                if(bot.playerTurn) {
                    battleCard = deck.drawCard();
                    System.out.println(battleCard.getPoint());
                    battleCardView.setImage(battleCard.getImage());
                    botSelectedCard = bot.botChooseCard(battleCard.getPoint(), bot.hand);
                    bot.betPoint = botSelectedCard.getPoint();
                    if(!removedCard) {
                        removeCard(imgBotList.get(getObjectFromCard(botSelectedCard, bot.hand)));
                        bot.hand.remove(botSelectedCard);
                        removedCard = true;
                    }
                    bot.sortByPoint();
                    botSelectedCardView.setImage(backCard);
                    botAction = bot.botChooseActionFirst(bot.getPointAction());
                    botGetAction(botAction);
                    renderCard(battleCardView);
                    renderCard(botSelectedCardView);
                    drawable = false;
                    userFirst = false;
                    System.out.println("botHand = " + bot.hand.size());
                }
            }
            else System.out.println("Can't draw more");
            System.out.println("deck = " + deck.getCardsInDeck());
        }
    }

    public void fold() {
        if(user.playerTurn && checkSelected) {
            bot.win(totalBet);
            bot.plusScore(battleCard.getPoint());
            System.out.println("Bot Wins");
            removeBtn(undoBtn);
            removedCard = false;
            drawable = true;
        }
        else System.out.println("Not your turn");
        updateLabel();
    }

    public void botFold() {
        if(bot.playerTurn) {
            user.win(totalBet);
            user.plusScore(battleCard.getPoint());
            action.setText("BOT FOLD, You win");
            System.out.println("User Wins");
            System.out.println("Bot " + botAction);
            removedCard = false;
            drawable = true;
        }
        else System.out.println("Not your turn");
        updateLabel();
    }

    public void raise() {
        if(user.playerTurn && betMoneySelected != 0 && (betMoneySelected + user.betMoney) > bot.betMoney && checkSelected) {
            user.bet(betMoneySelected);
            totalBet += betMoneySelected;
            betMoneySelected = 0;
            updateLabel();
            switchTurn();
            if(userFirst) {
                botSelectedCard = bot.botChooseCard(battleCard.getPoint(), bot.hand);
                bot.betPoint = botSelectedCard.getPoint();
                if(!removedCard) {
                    removeCard(imgBotList.get(getObjectFromCard(botSelectedCard, bot.hand)));
                    bot.hand.remove(botSelectedCard);
                    removedCard = true;
                }
                bot.sortByPoint();
                botSelectedCardView.setImage(backCard);
                renderCard(botSelectedCardView);
            }
            botAction = bot.botChooseActionSecond(bot.getPointAction());
            botGetAction(botAction);
            removeBtn(undoBtn);
            System.out.println("botHand = " + bot.hand.size());
        }
        else System.out.println("Can't raise");
    }

    public void botRaise(int amount) {
        if(bot.playerTurn) {
            if(bot.betMoney + amount > user.betMoney) {
                totalBet += amount;
                bot.bet(amount);
                switchTurn();
                updateLabel();
                action.setText("BOT RISE!!");
                System.out.println("Bot " + botAction);
                System.out.println(bot.betMoney);
            }
            else {
                bot.bet(50);
            }
        }
    }

    public void botGetAction(String action) {
        if(botAction.equals("Call")) botCall();
        for(int i = 0; i <= 120; i += 10) {
            if(botAction.equals("Raise" + i)) {
                botRaise(i);
            }
        }
        if(botAction.equals("Fold")) botFold();
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
            imgList.get(i).setVisible(true);
            imgList.get(i).setImage(user.hand.get(i).getImage());
        }

        for (ImageView imageView : imgBotList) {
            imageView.setVisible(true);
            imageView.setImage(backCard);
        }
    }

    public void removeBtn(Button btn) {
        btn.setVisible(false);
    }

    public void removeCard(ImageView imgView) {
        imgView.setVisible(false);
    }

    public void showAllCardBattle() {
        botSelectedCardView.setImage(botSelectedCard.getImage());
        selectedCardView.setImage(selectedCard);
    }

    public void removeAllCardBattle() {
        botSelectedCardView.setVisible(false);
        selectedCardView.setVisible(false);
        battleCardView.setVisible(false);
    }
    /////////////////////////////////////BUTTON//////////////////////////////////////

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
            if(addBet) {
                if(betMoneySelected <= user.money){
                    betMoneySelected += getAmountBetFromBtn(id);
                    updateLabel();
                }
                else System.out.println("Can't bet more");
            }
            else {
                if(betMoneySelected >= 0){
                    betMoneySelected -= getAmountBetFromBtn(id);
                    updateLabel();
                }
                else System.out.println("Can't bet less");
            }
        } else System.out.println("Not your turn");
    }

    public void addBetBtn() {
        addBet = true;
    }
    public void reduceBetBtn() {
        addBet = false;
    }

    public void selectCardBtn(ActionEvent e) {
        if (!checkSelected && user.playerTurn) {
            String id = ((Button) e.getSource()).getId();
            indexSelectedCard = getObjectFromBtn(id);
            user.setBetPoint(getValueFromCard(id));
            //System.out.println(getValueFromCard(id));
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

    public void updateLabel() {
        userMoney.setText(String.valueOf(user.money));
        botMoney.setText(String.valueOf(bot.money));
        userBetMoney.setText(String.valueOf(user.betMoney));
        botBetMoney.setText(String.valueOf(bot.betMoney));
        userBattlePt.setText(String.valueOf(user.getScore()));
        botBattlePt.setText(String.valueOf(bot.getScore()));
        betMoney.setText(String.valueOf(betMoneySelected));
        totalBetLabel.setText(String.valueOf(totalBet));
    }
}
