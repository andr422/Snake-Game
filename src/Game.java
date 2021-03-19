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
            setRandomAppleLocation();
        } while (!checkAppleLocation());
    }

    public void setRandomAppleLocation (){
        int maxXAxis = (this.width-1);
        int maxYAxis = (this.height-1);
        double doubleXAxis = Math.random() * maxXAxis;
        double doubleYAxis = Math.random() * maxYAxis;
        this.apple.location[0] = (int)doubleYAxis;
        this.apple.location[1] = (int)doubleXAxis;
    }



    public void startingPositionSnake(int height, int width) {
        int startingX = (width/2);
        int startingY = (height/2);
        for (int i = 0; i < setSnakeLength(); i++) {
            this.snake.snakeBody.put(i, new int[]{startingY, (startingX-i)});
        }
    }

    public int setSnakeLength() {
        return (this.width/4);
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
        switch (input) {
            case "a":
                return (!(this.snake.head()[1]==(0)));
            case "s":
                return (!(this.snake.head()[0]==(this.height-1)));
            case "w":
                return (!(this.snake.head()[0]==(0)));
            case "d":
                return (!(this.snake.head()[1]==(this.width-1)));
            default:
                return false;
        }
    }


    //checkAppleLocation and checkNoCrash are glitchy
    public boolean checkAppleLocation() {
        if (this.snake.head()[0]==this.apple.location[0]&&this.snake.head()[1]==this.apple.location[1]) {
            return false;
        } else {
            return true;
        }
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
    public void getMove (){
        this.snake.appleEaten = false;
        String input;
        boolean promptMove = true;
        while (promptMove) {
            System.out.println("Please enter a move - Either w, s, d, or a");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.equals("a")||input.equals("s")||input.equals("d")||input.equals("w")) {
                if (this.checkInBounds(input)) {
                    if (this.checkNoCrash()) {
                        promptMove = false;
                        if (!this.checkAppleLocation()) {
                            this.getAppleLocation();
                            this.snake.appleEaten = true;
                            this.score +=1;
                        }
                        this.snake.move(input);
                    } else {
                        System.out.println("You crashed into yourself! You died.");
                        this.printScore();
                        promptMove = false;
                        this.play = false;
                    }
                } else {
                    System.out.println("That move is out of bounds! You died.");
                    this.printScore();
                    promptMove = false;
                    this.play = false;
                }
            } else {
                System.out.println("That isn't a valid move!");
            }
        }
    }
}
