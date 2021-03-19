import java.util.Scanner;


public class Game {
    int height;
    int width;
    char[][] board;
    Snake snake;
    boolean play;
    Apple apple;
    int score;


    public Game(int heightIn, int widthIn) {
        this.height = heightIn;
        this.width = widthIn;
        //height: 20 width: 80
        this.board = new char[height][width];
        this.snake = new Snake();
        this.startingPositionSnake(this.height, this.width);
        this.play = true;
        this.apple = new Apple();
        this.getAppleLocation();
        this.score = 0;
    }


    public void getAppleLocation() {
        do {
            int maxXAxis = (this.width-1);
            int maxYAxis = (this.height-1);
            double doubleXAxis = Math.random() * maxXAxis;
            double doubleYAxis = Math.random() * maxYAxis;
            this.apple.location[0] = (int)doubleYAxis;
            this.apple.location[1] = (int)doubleXAxis;
        } while (!checkAppleCrash());
    }


    public void startingPositionSnake(int height, int width) {
        int startingX = (width/2);
        int startingY = (height/2);
        for (int i = 0; i < (this.width/4); i++) {
            this.snake.snakeBody.put(i, new int[]{startingY, (startingX-i)});
        }
    }


    public void updateBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] = ' ';
            }
        }
        for (int[] coordinates : this.snake.snakeBody.values()) {
            this.board[coordinates[0]][coordinates[1]] = 'O';
        }
        this.board[this.apple.location[0]][this.apple.location[1]] = 'A';
    }


    public void render() {
        System.out.println("Score: "+this.score);
        System.out.println("  "+"_".repeat(this.width));
        for (int i = 0; i < this.height; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.board[i][j]);
                }
            System.out.println(" |");
        }
        System.out.println("  "+"_".repeat(this.width));
    }


    public boolean checkInBounds(String input) {
        return switch (input) {
            case "a" -> (!(this.snake.head()[1] == (0)));
            case "s" -> (!(this.snake.head()[0] == (this.height - 1)));
            case "w" -> (!(this.snake.head()[0] == (0)));
            case "d" -> (!(this.snake.head()[1] == (this.width - 1)));
            default -> false;
        };
    }


    //checkAppleLocation and checkNoCrash are glitchy
    public boolean checkAppleCrash() {
        //return true if right location
        for (int i = 1; i < this.snake.snakeBody.size(); i++) {
            if (this.apple.location[0]==this.snake.snakeBody.get(i)[0]&&this.apple.location[1]==this.snake.snakeBody.get(i)[1]) {
                return false;
            }
        }
        return true;
    }


    public boolean checkAppleEaten() {
        return (this.snake.head()[0]==this.apple.location[0]&&this.snake.head()[1]==this.apple.location[1]);
    }


    public boolean checkNoCrash() {
        for (int i = 1; i < this.snake.snakeBody.size(); i++) {
            if (this.snake.head()[0]==this.snake.snakeBody.get(i)[0]&&this.snake.head()[1]==this.snake.snakeBody.get(i)[1]) {
                return false;
            }
        }
        return true;
    }


    public void printScore() {
        if (score == 1) {
            System.out.println("You scored 1 point!");
        } else {
            System.out.println("You scored "+score+" points!");
        }
    }


    public void moveSequence (){
        String input;
        boolean promptMove = true;
        while (promptMove) {
            input = getPlayerMove();
            if (validMove(input)) {
                if (this.checkInBounds(input)) {
                    if (this.checkNoCrash()) {
                        if (checkAppleEaten()) {
                            System.out.println("You at an apple!");
                            this.getAppleLocation();
                            this.snake.move(input, true);
                            this.score +=1;
                        } else {
                            this.snake.move(input, false);
                        }
                        promptMove = false;
                    } else {
                        promptMove = crashSelf();
                    }
                } else {
                    promptMove = crashWall();
                }
            } else {
                System.out.println("That isn't a valid move!");
            }
        }
    }


    public String getPlayerMove() {
        System.out.println("Please enter a move - Either w, s, d, or a");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean validMove(String input) {
        return (input.equals("a")||input.equals("s")||input.equals("d")||input.equals("w"));
    }

    public boolean crashSelf() {
        System.out.println("You crashed into yourself! You died.");
        this.printScore();
        this.play = false;
        return false;
    }


    public boolean crashWall() {
        System.out.println("That move is out of bounds! You died.");
        this.printScore();
        this.play = false;
        return false;
    }
}