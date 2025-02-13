package object;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,128,128);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int xAdjuster, yAdjuster = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX + xAdjuster;
        int screenY = worldY - gp.player.worldY + gp.player.screenY + yAdjuster;

        if(worldX + gp.tileSize*2 > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize*2 > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, solidArea.width, solidArea.height, null);
        }
    }

    public void update() {

    }
}
