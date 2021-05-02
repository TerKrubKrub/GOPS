package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.Objects;

public class Card {

    public enum Color{
        red, blue, green;
        private static final Color[] colors = Color.values();
        public static Color getColor(int i) {
            return Color.colors[i];
        }
    }

    public enum Value{
        Zero(0), One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Eleven(11), Twelve(12),
        Thirteen(13), Fourteen(14), Fifteen(15);

        final int score;
        Value(int score){
            this.score = score;
        }
        private static final Value[] values = Value.values();
        public static Value getValue(int i) {
            return Value.values[i];
        }
    }

    private final Color color;
    private final Value value;
    private final Image img;
    private ImageView imgView;

    public Card(final Color color, final Value value, final Image img) {
        this.color = color;
        this.value = value;
        this.img = img;
    }

    public Color getColor(){
        return this.color;
    }

    public Value getValue(){
        return this.value;
    }

    public Image getImage() {
        return this.img;
    }

    public ImageView getImgView() {
        return this.imgView;
    }

    public int getPoint(){
        return this.value.score;
    }

    @Override
    public String toString() {
        return "Card{" + "color=" + color + ", value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return color == card.color && value == card.value;
    }
}
