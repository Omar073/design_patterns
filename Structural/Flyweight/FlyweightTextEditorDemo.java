package Flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight Pattern – Text editor example
// Demonstrates sharing CharacterGlyph objects (intrinsic state: symbol)
// while passing position (row, column) as extrinsic state on each draw call.
// Roles:
//   - Flyweight: CharacterGlyph stores intrinsic symbol
//   - Flyweight factory: GlyphFactory caches glyphs per character
//   - Client: main() passes extrinsic position to draw()

// Flyweight
interface Glyph {
    void draw(int row, int column);
}

// Concrete Flyweight
class CharacterGlyph implements Glyph {
    private final char symbol; // intrinsic

    public CharacterGlyph(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void draw(int row, int column) {
        System.out.println("Drawing '" + symbol + "' at (" + row + "," + column + ")");
    }
}

// Flyweight Factory
class GlyphFactory {
    private static final Map<Character, Glyph> cache = new HashMap<>();

    public static Glyph getGlyph(char symbol) {
        Glyph glyph = cache.get(symbol);
        if (glyph == null) {
            glyph = new CharacterGlyph(symbol);
            cache.put(symbol, glyph);
        }
        return glyph;
    }
}

// Client / Demo
public class FlyweightTextEditorDemo {
    public static void main(String[] args) {
        String text = "HELLO FLYWEIGHT";
        int row = 0;

        for (int column = 0; column < text.length(); column++) {
            char c = text.charAt(column);
            if (c == ' ') {
                continue; // skip spaces for drawing
            }
            Glyph glyph = GlyphFactory.getGlyph(c);  // shared intrinsic state
            glyph.draw(row, column);                 // extrinsic state: position
        }
    }
}


