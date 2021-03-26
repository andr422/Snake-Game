import java.util.Scanner;


public class Console {
    boolean wantToPlay;


    public Console(){
    }


    public void play(boolean rules) {
        //true for switch sides false for normal
        Game game = new Game(10, 40);
        if (rules) {
            while (game.play) {
                game.updateBoard();
                game.render();
                game.moveSequenceTwo();
            }
        } else {
            while (game.play) {
                game.updateBoard();
                game.render();
                game.moveSequence();
            }
        }
    }


    public boolean getRules() {
        System.out.println("Type 1 for Snake and 2 for Snake 2");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return (scanner.nextInt()==2);
        }
        return false;
    }


    public void askToPlay() {
        this.wantToPlay = false;
        System.out.println("Type 1 to play Snake or 0 to stop the program!");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            if (scanner.nextInt()==1) {
                this.wantToPlay = true;
            }
        }
    }
}
