import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Nick on 5/23/14.
 */
public class MapProperties {

    public int seed;

    public int mapWidth;
    public int mapHeight;

    public int minerMax;
    public int minerChance;

    public int tileDug;
    public int tileDefault;

    public MapProperties(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(fileName);

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            this.seed = Integer.parseInt(prop.getProperty("seed"));

            this.mapWidth = Integer.parseInt(prop.getProperty("map.width"));
            this.mapHeight = Integer.parseInt(prop.getProperty("map.height"));

            this.minerChance = Integer.parseInt(proper.getProperty("miner.chance"));
            this.minerMax = Integer.parseInt(prop.getProperty("miner.max"));

            this.tileDefault = Integer.parseInt(prop.getProperty("tiles.default"));
            this.tileDug = Integer.parseInt(prop.getProperty("tiles.dug"));

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open the properties file");
        } catch (IOException ex) {
            System.out.println("Error reading the properties file");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
