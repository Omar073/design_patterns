package Flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Flyweight Pattern – Forest example
// Shows how many Tree objects can share a small number of TreeType flyweights.
// Intrinsic state: TreeType (name, color) is shared; extrinsic state: Tree (x, y) is unique.
// Roles:
//   - Flyweight: TreeType holds intrinsic state (name, color)
//   - Flyweight factory: TreeFactory caches TreeType by key
//   - Context: Tree holds extrinsic state (x, y) plus a TreeType reference
//   - Client: Forest creates many Trees while reusing limited TreeTypes

// Flyweight: shared tree type (intrinsic state)
class TreeType {
    private final String name;
    private final String color;

    public TreeType(String name, String color) {
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
// This is like a tree registry that stores the tree types and returns the tree type if it already exists.
class TreeFactory {
    // key is the name_color of the tree type
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color) {
        String key = name + "_" + color;
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

    public void plantTree(int x, int y, String name, String color) {
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
    private static final int TREES_TO_DRAW = 10;
    private static final int TREE_TYPES = 2;

    public static void main(String[] args) {
        Forest forest = new Forest();

        for (int i = 0; i < Math.floor((double) TREES_TO_DRAW / TREE_TYPES); i++) {
            // Set coordinates to change for each iteration, ensuring no two trees are planted in the same spot
            int x1 = 100 + i * 150;
            int y1 = 150 + i * 150;
            forest.plantTree(x1, y1, "Summer Oak", "green");

            int x2 = 200 + i * 150;
            int y2 = 250 + i * 150;
            forest.plantTree(x2, y2, "Autumn Oak", "orange");
        }

        forest.draw();
    }
}
