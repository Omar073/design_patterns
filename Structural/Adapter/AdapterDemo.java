// Adapter Pattern – convert one interface to another clients expect
// Object adapter: adapt SquarePeg to RoundPegTarget (RoundHole expects RoundPegTarget)
// Class adapter (conceptual in Java): using inheritance for adaptation (limited by single inheritance)
// Roles:
//   - Target: RoundPegTarget interface expected by RoundHole
//   - Adaptee: SquarePeg with incompatible getWidth()
//   - Adapter: SquarePegAdapter converts width to an equivalent radius
//   - Client: RoundHole uses only the target interface and is unaware of SquarePeg

class RoundHole {
    private final double radius;

    RoundHole(double radius) {
        this.radius = radius;
    }

    public boolean fits(RoundPegTarget peg) {
        // Client code works against RoundPegTarget, unaware of squares
        return peg.getRadius() <= radius;
    }
}

// Target interface expected by RoundHole
interface RoundPegTarget {
    double getRadius();
}

class RoundPeg implements RoundPegTarget {
    private final double radius;

    RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

// Adaptee with different interface
class SquarePeg {
    private final double width;

    SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}

// Object Adapter – wrap SquarePeg and present RoundPegTarget
class SquarePegAdapter implements RoundPegTarget {
    private final SquarePeg squarePeg;

    SquarePegAdapter(SquarePeg peg) {
        this.squarePeg = peg;
    }

    public double getRadius() {
        // compute minimal circle radius that fits the square
        return (squarePeg.getWidth() * Math.sqrt(2) / 2.0);
    }
}

public class AdapterDemo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPegTarget round = new RoundPeg(5);
        System.out.println("Round fits: " + hole.fits(round));

        SquarePeg smallSquare = new SquarePeg(5);
        SquarePeg bigSquare = new SquarePeg(10);

        // Wrap square pegs so the hole can treat them as round targets
        RoundPegTarget smallAdapter = new SquarePegAdapter(smallSquare);
        RoundPegTarget bigAdapter = new SquarePegAdapter(bigSquare);
        System.out.println("Small square fits: " + hole.fits(smallAdapter));
        System.out.println("Big square fits: " + hole.fits(bigAdapter));
    }
}
