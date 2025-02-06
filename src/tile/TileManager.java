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
    public int mapTileNumber0[][];
    public int mapTileNumber1[][];
    public int mapTileNumber2[][];
    public int mapTileNumber3[][];

    public Tile[] water;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[30];
        mapTileNumber0 = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNumber1 = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNumber2 = new int[gp.maxWorldCol][gp.maxWorldRow];
        mapTileNumber3 = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        
        loadMap("/res/maps/newTest/layer0.txt", 0);
        loadMap("/res/maps/newTest/layer1.txt", 1);
        loadMap("/res/maps/newTest/layer2.txt", 2);
    }

    public void getTileImage() {
        setup(0, "floor/floorTile001",  false);
        setup(1, "floor/floorTile002",  false);
        setup(2, "floor/floorTile003",  false);
        setup(3, "floor/floorTile004",  false);
        setup(4, "floor/floorTile005",  false);
        setup(5, "floor/floorTile006",  false);
        setup(6, "floor/floorTile007",  false);
        setup(7, "floor/floorTile008",  false);
        setup(8, "floor/floorTile009",  false);
        setup(9, "floor/floorTile010",  false);
        setup(10, "floor/floorTile011",  false);
        setup(11, "floor/floorTile012",  false);
        setup(12, "floor/floorTile013",  false);
        setup(13, "floor/floorTile014",  false);
        setup(14, "floor/floorTile015",  false);
        setup(15, "floor/floorTile016",  false);
        setup(16, "floor/floorTile017",  false);
        setup(17, "floor/floorTile018",  false);
        setup(18, "floor/floorTile101",  false);
        setup(19, "floor/floorTile102",  false);
        setup(20, "floor/floorTile103",  false);
        setup(21, "floor/floorTile104",  false);
        setup(22, "floor/floorTile201",  false);
        setup(23, "floor/floorTile202",  false);

        setup(28, "water/water-0", true);
        setup(29, "blankTile",  false);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        waterSpriteCounter+= (60/gp.FPS);
        if (waterSpriteCounter > 6) {
            waterSpriteNumber++;
            if (waterSpriteNumber > 15) {
                waterSpriteNumber = 0;
            }
            waterSpriteCounter = 0;
        }
        try {
            switch (waterSpriteNumber) {
                case 0: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-0.png")); break;
                case 1: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-1.png")); break;
                case 2: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-2.png")); break;
                case 3: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-3.png")); break;
                case 4: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-4.png")); break;
                case 5: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-5.png")); break;
                case 6: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-6.png")); break;
                case 7: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-7.png")); break;
                case 8: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-8.png")); break;
                case 9: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-9.png")); break;
                case 10: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-10.png")); break;
                case 11: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-11.png")); break;
                case 12: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-12.png")); break;
                case 13: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-13.png")); break;
                case 14: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-14.png")); break;
                case 15: tile[28].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water/water-15.png")); break;
            }
            UtilityTool utilityTool = new UtilityTool();
            tile[28].image = utilityTool.scaleImage(tile[28].image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int layer) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    switch(layer) {
                        case 0: mapTileNumber0[col][row] = number;
                        case 1: mapTileNumber1[col][row] = number;
                        case 2: mapTileNumber2[col][row] = number;
                        case 3: mapTileNumber3[col][row] = number;
                    }
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNumber0 = mapTileNumber0[worldCol][worldRow];
            int tileNumber1 = mapTileNumber1[worldCol][worldRow];
            int tileNumber2 = mapTileNumber2[worldCol][worldRow];
            int tileNumber3 = mapTileNumber3[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNumber0].image, screenX, screenY, null);
                g2.drawImage(tile[tileNumber1].image, screenX, screenY, null);
                g2.drawImage(tile[tileNumber2].image, screenX, screenY, null);
                g2.drawImage(tile[tileNumber3].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
