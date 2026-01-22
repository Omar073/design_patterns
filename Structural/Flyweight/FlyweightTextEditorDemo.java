package Flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight Pattern – Text editor example
// Demonstrates sharing TextCharacter objects (intrinsic state: symbol)
// while passing position (row, column) as extrinsic state on each draw call.
// Roles:
//   - Flyweight: TextCharacter stores intrinsic symbol
//   - Flyweight factory: CharacterFactory caches characters per symbol
//   - Client: main() passes extrinsic position to draw()

// Concrete Flyweight Object
class TextCharacter {
    private final char symbol; // intrinsic

    public TextCharacter(char symbol) {
        this.symbol = symbol;
    }

    public void draw(int row, int column) {
        System.out.println("Drawing '" + symbol + "' at (" + row + "," + column + ")");
    }
}

// Flyweight Factory
class CharacterFactory {
    private static final Map<Character, TextCharacter> cache = new HashMap<>();

    public static TextCharacter getCharacter(char symbol) {
        Character key = symbol;
        TextCharacter textCharacter = cache.get(key);
        if (textCharacter == null) {
            textCharacter = new TextCharacter(symbol);
            cache.put(key, textCharacter);
        }
        return textCharacter;
    }
}

// Client / Demo
public class FlyweightTextEditorDemo {
    public static void main(String[] args) {
        String text = "HELLO FLYWEIGHT!";
        int row = 0;

        for (int column = 0; column < text.length(); column++) {
            char c = text.charAt(column);
            if (c == ' ') {
                continue; // skip spaces for drawing
            }
            TextCharacter character = CharacterFactory.getCharacter(c); // shared intrinsic state
            character.draw(row, column); // extrinsic state: position
        }
    }
}
