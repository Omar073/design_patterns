package Flyweight;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Flyweight Pattern – Forest example
// Shows how many Tree objects can share a small number of TreeType flyweights.
// Intrinsic state: TreeType (name, color) is shared; extrinsic state: Tree (x, y) is unique.

// Flyweight: shared tree type (intrinsic state)
class TreeType {
    private final String name;
    private final Color color;

    public TreeType(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void draw(int x, int y) {
        System.out.println("Tree drawn at (" + x + "," + y + ") - "
            + name + " with color " + color + ".");
    }
}

// Context: holds extrinsic state and reference to flyweight
class Tree {
    private final int x;
    private final int y;
    private final TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}

// Flyweight Factory
class TreeFactory {
    // key is the name and color of the tree type
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, Color color) {
        String key = name + "_" + color.getRGB();
        TreeType result = treeTypes.get(key);
        if (result == null) {
            result = new TreeType(name, color);
            treeTypes.put(key, result);
        }
        return result;
    }
}

// Client that uses many small Tree objects
class Forest {
    private final List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color) {
        TreeType type = TreeFactory.getTreeType(name, color);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}

public class FlyweightForestDemo {
    private static final int CANVAS_SIZE = 500;
    private static final int TREES_TO_DRAW = 10;
    private static final int TREE_TYPES = 2;

    public static void main(String[] args) {
        Forest forest = new Forest();

        for (int i = 0; i < Math.floor((double) TREES_TO_DRAW / TREE_TYPES); i++) {
            int x1 = random(0, CANVAS_SIZE);
            int y1 = random(0, CANVAS_SIZE);
            forest.plantTree(x1, y1, "Summer Oak", Color.GREEN);

            int x2 = random(0, CANVAS_SIZE);
            int y2 = random(0, CANVAS_SIZE);
            forest.plantTree(x2, y2, "Autumn Oak", Color.ORANGE);
        }

        forest.draw();
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }
}
