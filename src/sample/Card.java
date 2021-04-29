package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    enum Color{
        Red, Blue, Green;
        private static final Color[] colors = Color.values();
        public static Color getColor(int i) {
            return Color.colors[i];
        }
    }

    enum Value{
        One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Eleven(11), Twelve(12),
        Thirteen(13), Fourteen(14), Fifteen(15);

        final int score;
        private Value(int score){
            this.score = score;
        }
        private static final Value[] values = Value.values();
        public static Value getValue(int i){
            return Value.values[i];
        }

    }

    private final Color color;
    private final Value value;

    public Card(final Color color, final Value value){
        this.color = color;
        this.value = value;
    }

    public Color getColor(){
        return this.color;
    }

    public Value getValue(){
        return this.value;
    }

    public int getScore(){
        return this.value.score;
    }

    @Override
    public String toString() {
        return "Card{" + "color=" + color + ", value=" + value + '}';
    }

}
