public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        console.askToPlay();
        while (console.wantToPlay) {
            console.play(console.getRules());
        }
    }
}