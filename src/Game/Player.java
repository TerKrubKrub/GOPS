package Game;

import java.util.ArrayList;

public class Player {
    protected int money;
    protected ArrayList<Card> hand;
    protected int betMoney;
    protected boolean playerTurn;
    protected int score;
    protected int betPoint;

    public Player() {
        initPlayer();
    }

    public void initPlayer() {
        this.money = 100;
        this.betMoney = 0;
        this.playerTurn = false;
        this.score = 0;
        this.betPoint = 0;
        this.hand = new ArrayList<Card>();
    }

    public int getMoney() {
        return this.money;
    }

    public boolean isPlayerTurn() {
        return this.playerTurn;
    }

    public int getScore() {
        return this.score;
    }

    public int getBetMoney() {
        return this.betMoney;
    }

    public void plusScore(int amount) {
        this.score += amount;
    }

    public void bet(int amount) {
        this.betMoney += amount;
        this.money -= amount;
    }

    public int getBetPoint() {
        return this.betPoint;
    }

    public void setBetPoint(int amount) {
        this.betPoint = amount;
    }
}
