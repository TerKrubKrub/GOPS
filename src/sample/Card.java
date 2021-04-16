package sample;

public class Card {
    enum Color{
        Red, Blue, Green;
        private static final Color[] colors = Color.values();
        public static Color getColor(int i) {
            return Color.colors[i];
        }
    }

    enum Value{
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;

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

    @Override
    public String toString() {
        return "Card{" + "color=" + color + ", value=" + value + '}';
    }

}
