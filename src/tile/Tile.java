package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;

    public int waterSpriteNumber = 1;
    public int waterSpriteCounter = 0;

    public boolean teleport = false;

    public String mapSetter;
}

