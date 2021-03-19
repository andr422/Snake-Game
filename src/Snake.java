import java.util.HashMap;

public class Snake {
    int[] direction;
    boolean appleEaten = false;


    HashMap<String, int[]> directions = new HashMap<>()
    {{
        put("s", new int[]{1, 0});
        put("w", new int[]{-1, 0});
        put("a", new int[]{0, -1});
        put("d", new int[]{0, 1});

    }};

    HashMap<Integer, int[]> snakeBody = new HashMap<>();
        //first int is y second int is x


    public int[] head() {
        return this.snakeBody.get(0);
    }


    public void setAppleEaten(boolean isAppleEaten) {
        this.appleEaten = isAppleEaten;
    }
    public void move(String move) {
        int[] directionToAdd = directions.get(move);
        for (int i = this.snakeBody.size(); i > 0; i--) {
            this.snakeBody.remove(i);
            this.snakeBody.put(i, this.snakeBody.get((i-1)));
        }
        this.snakeBody.put(0, new int[]{head()[0]+directionToAdd[0], head()[1]+directionToAdd[1]});
        if (!appleEaten) {
            this.snakeBody.remove((this.snakeBody.size()-1));
        }

    }


    public Snake() {
        this.direction = directions.get("d");
    }
}
