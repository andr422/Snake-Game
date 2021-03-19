public class Main {
    public static void main(String[] args) {
        Game game = new Game(10, 40);

        while (game.play) {
            game.updateBoard();
            game.render();
            game.getMove();
        }
    }
}