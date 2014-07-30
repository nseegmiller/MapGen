import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Nick on 5/23/14.
 */
public class CreateMap {

    public static void main(String[] args) {

        MapProperties mapProperties = new MapProperties("config.properties");
        ArrayList<Miner> miners = new ArrayList<Miner>();

        miners.append(new Miner(mapProperties.mapWidth / 2, mapProperties.mapHeight / 2));

        while (true) {
            if (miners.length >= mapProperties.minerMax) {
                break;
            }

            int numMiners = miners.length;
            System.out.println("Num Miners: " + numMiners);

            for (Miner miner : miners) {
                double number = Math.random();
                System.out.println("Random chance: " + number);

                if (number < mapProperties.minerChance) {
                    Miner newMiner = new Miner(miner.getX(), miner.getY());

                }
//        if number < CHANCE_TO_SPAWN_MINER:
//        new_miner_x = self.x
//        new_miner_y = self.y
//        for dir in choices:
//        if dir == Directions.UP:
//        new_y = new_miner_y - 1
//        if new_y >= 0:
//        new_miner_y = new_y
//        break
//                elif dir == Directions.DOWN:
//        new_y = new_miner_y + 1
//        if new_y < MAP_HEIGHT:
//        new_miner_y = new_y
//        break
//                elif dir == Directions.LEFT:
//        new_x = new_miner_x - 1
//        if new_x >= 0:
//        new_miner_x = new_x
//        break
//                elif dir == Directions.RIGHT:
//        new_x = new_miner_x + 1
//        if new_x < MAP_WIDTH:
//        new_miner_x = new_x
//        break
//                print 'Spawning miner at: ' + str(new_miner_x) + ', ' + str(new_miner_y)
//        if map[new_miner_x][new_miner_y] == DEFAULT_TILE:
//        map[new_miner_x][new_miner_y] = DUG_TILE
//        miners.append(Miner(new_miner_x, new_miner_y))
            }
        }
    }
}
