package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager extends Tile {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNumber[] [];

    public Tile[] water;

    public TileManager(GamePanel gp) {

        this.gp = gp; 
        tile = new Tile[10];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/cliffMap.txt");
    }

    public void getTileImage() {
        setup(0, "dirt", false);
        setup(1, "grass", false);
        setup(2, "hay", false);
        setup(3, "sand", false);
        setup(4, "wall", true);
        setup(5, "water/water-0", true);
        setup(6, "wood", false);
        setup(7, "portal", false);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+imageName+".png"));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        waterSpriteCounter ++;
            if (waterSpriteCounter > 6) {
                waterSpriteNumber ++;
                if(waterSpriteNumber > 15) {
                    waterSpriteNumber = 0;
                }
                waterSpriteCounter = 0;
            }
        try {
            switch(waterSpriteNumber) {
            case 0: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-0.png")); break;
            case 1: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-1.png")); break;
            case 2: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-2.png")); break;
            case 3: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-3.png")); break;
            case 4: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-4.png")); break;
            case 5: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-5.png")); break;
            case 6: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-6.png")); break;
            case 7: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-7.png")); break;
            case 8: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-8.png")); break;
            case 9: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-9.png")); break;
            case 10: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-10.png")); break;
            case 11: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-11.png")); break;
            case 12: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-12.png")); break;
            case 13: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-13.png")); break;
            case 14: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-14.png")); break;
            case 15: tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-15.png")); break;
            }
            UtilityTool utilityTool = new UtilityTool();
            tile[5].image = utilityTool.scaleImage(tile[5].image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = number;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNumber = mapTileNumber[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNumber].image, screenX, screenY, null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
