// Facade Pattern – Without Facade Example
// Demonstrates the complexity of using subsystem components directly

class Amplifier {
    void on() {
        System.out.println("Amp on");
    }

    void off() {
        System.out.println("Amp off");
    }

    void setVolume(int v) {
        System.out.println("Amp volume " + v);
    }
}

class Tuner {
    void on() {
        System.out.println("Tuner on");
    }

    void off() {
        System.out.println("Tuner off");
    }
}

class Screen {
    void down() {
        System.out.println("Screen down");
    }

    void up() {
        System.out.println("Screen up");
    }
}

class DvdPlayer {
    void on() {
        System.out.println("DVD on");
    }

    void off() {
        System.out.println("DVD off");
    }

    void play(String movie) {
        System.out.println("Playing " + movie);
    }
}

public class WithoutFacadeDemo {
    public static void main(String[] args) {
        System.out.println("== Without Facade ==");
        Amplifier amp = new Amplifier();
        Tuner tuner = new Tuner();
        Screen screen = new Screen();
        DvdPlayer dvd = new DvdPlayer();

        // Client must know the exact sequence of operations
        screen.down();
        amp.on();
        amp.setVolume(7);
        tuner.on();
        dvd.on();
        dvd.play("Movie");

        // Tear down
        dvd.off();
        tuner.off();
        amp.off();
        screen.up();

        System.out.println("\n⚠ Without facade, client must know subsystem details and correct sequence");
    }
}
