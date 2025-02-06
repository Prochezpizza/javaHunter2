package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    int standCounter = 0;

    double xSpeed, ySpeed;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 24;
        solidArea.y = 48;
        solidArea.width = 80;
        solidArea.height = 80;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 15;
        speed = 6;
        direction = "up";
    }

    public void getPlayerImage() {
        up1 = setup("orca");
        up2 = setup("orca");
        down1 = setup("orca");
        down2 = setup("orca");
        left1 = setup("orca");
        left2 = setup("orca");
        right1 = setup("orca");
        right2 = setup("orca");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        int defaultSpeed = speed;
        speed = (60/gp.FPS)*speed;

        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {
            collisionLeftOn = collisionRightOn = collisionUpOn = collisionDownOn = false;
            gp.collisionChecker.checkTile(this);
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (keyHandler.upPressed == true && keyHandler.downPressed == false) {
                ySpeed = speed * -1;
            } else if (keyHandler.upPressed == false && keyHandler.downPressed == true) {
                ySpeed = speed;
            } else {
                ySpeed = 0;
            }

            if (keyHandler.leftPressed == true && keyHandler.rightPressed == false) {
                xSpeed = speed * -1;
            } else if (keyHandler.leftPressed == false && keyHandler.rightPressed == true) {
                xSpeed = speed;
            } else {
                xSpeed = 0;
            }

            if (xSpeed != 0 && ySpeed != 0) {
                xSpeed = xSpeed / Math.sqrt(2);
                ySpeed = ySpeed / Math.sqrt(2);
            }

            if (collisionUpOn == false && keyHandler.upPressed) {
                direction = "up";
                worldY += ySpeed;
            }
            if (collisionDownOn == false && keyHandler.downPressed) {
                direction = "down";
                worldY += ySpeed;
            }
            if (collisionLeftOn == false && keyHandler.leftPressed) {
                direction = "left";
                worldX += xSpeed;
            }
            if (collisionRightOn == false && keyHandler.rightPressed) {
                direction = "right";
                worldX += xSpeed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter > 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
        speed = defaultSpeed;
    }

    public void pickUpObject(int i) {
        if(i != 418) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.ShowMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.ShowMessage("Door opened!");
                    } else {
                        gp.ui.ShowMessage("You need a key!");
                    }
                    break;
                case "Cow":
                    gp.ui.ShowMessage("Moo!");
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (spriteNum == 1) {
            switch (direction) {
                case "up": image = up1; break;
                case "down": image = down1; break;
                case "left": image = left1; break;
                case "right": image = right1; break;
            }
        } else {
            switch (direction) {
                case "up": image = up2; break;
                case "down": image = down2; break;
                case "left": image = left2; break;
                case "right": image = right2; break;
            }
        }

        g2.drawImage(image, screenX, screenY, null);
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY+solidArea.y, solidArea.width, solidArea.height);
    }
}
