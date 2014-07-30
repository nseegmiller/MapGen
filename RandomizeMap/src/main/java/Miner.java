import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Nick on 5/23/14.
 */
public class Miner {
    private int x, y;
    private boolean active;

    public Miner(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private ArrayList<Direction> getChoices() {
        ArrayList<Direction> choices = new ArrayList<Direction>();
        choices.add(Direction.UP);
        choices.add(Direction.DOWN);
        choices.add(Direction.LEFT);
        choices.add(Direction.RIGHT);
        return choices;
    }

    public boolean dig(int seed, Map map) {
        if (!active) {
            return false;
        }
        ArrayList<Direction> choices = getChoices();
        Collections.shuffle(choices, new Random(seed));

        boolean foundDirection = false;

        for (Direction dir : choices) {
            if (dir == Direction.UP) {
                int newY = y - 1;
                if (newY >= 0 && !map.isDug(x, newY)) {
                    y = newY;
                    foundDirection = true;
                    break;
                }
            }
            else if (dir == Direction.DOWN) {
                int newY = y + 1;
                if (newY < map.getHeight() && !map.isDug(x, newY)) {
                    y = newY;
                    foundDirection = true;
                    break;
                }
            }
            else if (dir == Direction.LEFT) {
                int newX = x - 1;
                if (newX >= 0 && !map.isDug(newX, y)) {
                    x = newX;
                    foundDirection = true;
                    break;
                }
            }
            else if (dir == Direction.RIGHT) {
                int newX = x + 1;
                if (newX < map.getWidth() && !map.isDug(newX, y)) {
                    x = newX;
                    foundDirection = true;
                    break;
                }
            }
        }

        if (!foundDirection) {
            active = false;
            return false;
        }
        else {
            map.dig(x, y);
            return false;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
