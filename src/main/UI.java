package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;

import object.Obj_Key;

public class UI {
    GamePanel gp;
    Font arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        Obj_Key key = new Obj_Key();
        keyImage = key.image;
    }

    public void ShowMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_80);
        g2.setColor(Color.yellow);
        g2.drawImage(keyImage, 60, 60, gp.tileSize/2, gp.tileSize, null);
        g2.drawString("x" + gp.player.hasKey, 130, 150);

        if(messageOn) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60));
            g2.setColor(Color.black);
            g2.drawString(message, 80, 250);

            messageCounter++;
            if(messageCounter > 100) {
                messageOn = false;
                messageCounter = 0;
            }
        }
    }
}
