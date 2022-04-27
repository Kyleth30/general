package tile;

import controls.MouseController;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    MouseController mCtrl;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp, MouseController mCtrl) {
        this.gp = gp;
        this.mCtrl = mCtrl;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldColumns][gp.maxWorldRows];
        getTileImage();
        loadMap();
    }
    public void loadMap() {

        try {
            InputStream is = getClass().getResourceAsStream("/map/world.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldColumns && row < gp.maxWorldRows) {

                String line = br.readLine();

                while(col < gp.maxWorldColumns) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldRows) {
                    col = 0;
                    row ++;
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D g2) {

        int worldColumns = 0;
        int worldRows = 0;
        int x = 0;
        int y = 0;


        while (worldColumns < gp.maxWorldColumns && worldRows < gp.maxWorldRows) {

            int tileNum = mapTileNum[worldColumns][worldRows];

            int worldX = worldColumns * gp.tileSize;
            int worldY = worldRows * gp.tileSize;
            int mapX = worldX - gp.player.locationX + gp.player.displayX;
            int mapY = worldY - gp.player.locationY + gp.player.displayY;

            if (worldX + gp.tileSize > gp.player.locationX - gp.player.displayX &&
                worldX - gp.tileSize < gp.player.locationX + gp.player.displayX &&
                worldY + gp.tileSize > gp.player.locationY - gp.player.displayY &&
                worldY - gp.tileSize < gp.player.locationY + gp.player.displayY ) {

                g2.drawImage(tile[tileNum].image, mapX, mapY, gp.tileSize, gp.tileSize, null);

            }
            worldColumns++;
            if(worldColumns == gp.maxWorldColumns) {
                worldColumns = 0;

                worldRows++;

            }
        }
    }
}