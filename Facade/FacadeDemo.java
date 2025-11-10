// Facade Pattern – provide a unified, higher-level interface to a subsystem
// Home theater example: Amplifier, Tuner, Screen, DvdPlayer wrapped by HomeTheaterFacade

class Amplifier { void on(){ System.out.println("Amp on"); } void off(){ System.out.println("Amp off"); } void setVolume(int v){ System.out.println("Amp volume " + v); } }
class Tuner { void on(){ System.out.println("Tuner on"); } void off(){ System.out.println("Tuner off"); } }
class Screen { void down(){ System.out.println("Screen down"); } void up(){ System.out.println("Screen up"); } }
class DvdPlayer { void on(){ System.out.println("DVD on"); } void off(){ System.out.println("DVD off"); } void play(String movie){ System.out.println("Playing " + movie); } }

class HomeTheaterFacade {
    private final Amplifier amp; private final Tuner tuner; private final Screen screen; private final DvdPlayer dvd;
    HomeTheaterFacade(Amplifier a, Tuner t, Screen s, DvdPlayer d){ this.amp=a; this.tuner=t; this.screen=s; this.dvd=d; }
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        screen.down();
        amp.on(); amp.setVolume(7);
        tuner.on();
        dvd.on(); dvd.play(movie);
    }
    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        dvd.off();
        tuner.off();
        amp.off();
        screen.up();
    }
}

public class FacadeDemo {
    private static void withoutFacade() {
        System.out.println("== Without Facade ==");
        Amplifier amp = new Amplifier();
        Tuner tuner = new Tuner();
        Screen screen = new Screen();
        DvdPlayer dvd = new DvdPlayer();
        screen.down();
        amp.on(); amp.setVolume(7);
        tuner.on();
        dvd.on(); dvd.play("Inception");
        // tear down ...
        dvd.off(); tuner.off(); amp.off(); screen.up();
    }
    private static void withFacade() {
        System.out.println("\n== With Facade ==");
        HomeTheaterFacade facade = new HomeTheaterFacade(new Amplifier(), new Tuner(), new Screen(), new DvdPlayer());
        facade.watchMovie("Inception");
        facade.endMovie();
    }
    public static void main(String[] args) {
        withoutFacade();
        withFacade();
    }
}


