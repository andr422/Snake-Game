public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        do {
            console.askToPlay();
            while (console.wantToPlay) {
                console.play(console.getRules());
            }
        } while (console.wantToPlay);
    }
}