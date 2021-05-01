package Game;

import Game.Player;

public class User extends Player {
    private String name;

    public User(String name) {
        this.name = name;
        this.playerTurn = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
