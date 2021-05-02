package Game;

import java.util.ArrayList;
import java.util.Collections;

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
        this.money = 1000;
        this.betMoney = 0;
        this.playerTurn = false;
        this.score = 0;
        this.betPoint = 0;
        this.hand = new ArrayList<>();
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
        if(this.money >= amount) {
            this.betMoney += amount;
            this.money -= amount;
        }
        else System.out.println("Can't bet more");
    }

    public void unBet(int amount) {
        if(this.betMoney >= 0) {
            this.betMoney -= amount;
            this.money += amount;
            if(this.betMoney < 0) {
                this.money += this.betMoney;
                this.betMoney = 0;
            }
        }
    }

    public int getBetPoint() {
        return this.betPoint;
    }

    public void setBetPoint(int amount) {
        this.betPoint = amount;
    }

    public void win(int amount) {
        this.money += amount;
    }

    public void sortByPoint() {
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getPoint() > hand.get(j).getPoint()) {
                    Collections.swap(hand, i, j);
                }
            }
        }
    }

}
