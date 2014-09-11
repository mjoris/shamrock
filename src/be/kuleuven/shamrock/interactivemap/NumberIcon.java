package be.kuleuven.shamrock.interactivemap;

/*
* REFERRED ICONS ORIGINALLY DESIGNED/PUBLISHED BY Nicolas Mollet
 * UNDER CC 3.0 BY-SA License
* http://mapicons.nicolasmollet.com/
* */
public class NumberIcon extends Icon {
    public enum Color {
        RASPBERRY_RED("raspberry"),
        BRIGHT_ORANGE("brightorange"),
        EMERALD_GREEN("emerald");

        private final String text;
        private Color(final String text) { this.text = text;}
        @Override
        public String toString() {return text;}
    }

    public NumberIcon(LeafletMap linkedMap, Color color, int number) {
        super(linkedMap, "mollet-number/" + color + "/number_" + number + ".png", 16, 37, 0, -29);
    }

}
