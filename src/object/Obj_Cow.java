package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Cow extends SuperObject {
    int timer = 0;
    int movingTimer = 0;
    double randomDirection = 0;
    String imageSetter = "/res/animals/cowRight.png";
    int movingSpeed = 0;
    public Obj_Cow() {
        solidArea.width = 160;
        name = "Cow";
        collision = true;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/animals/cowRight.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // Movement
        if(timer == 0) {
            movingTimer = (int)(Math.random() * 200);
            timer = (int)(Math.random() * 200) + 200;
            randomDirection = Math.random();
            movingSpeed = (int)(Math.random() * 0.8)+1;
        } else {
            timer--;
        }

        int direction;
        if(randomDirection < 0.25) {
            direction = 0;
        } else if(randomDirection < 0.5) {
            direction = 1;
        } else if(randomDirection < 0.72) {
            direction = 2;
        } else {
            direction = 3;
        }

        if(movingTimer >= 1) {
            switch(direction) {
                case 0: yAdjuster += movingSpeed * 0.8; break;
                case 1: yAdjuster -= movingSpeed * 0.8; break;
                case 2: xAdjuster += movingSpeed; imageSetter = "/res/animals/cowRight.png"; break;
                case 3: xAdjuster -= movingSpeed; imageSetter = "/res/animals/cowLeft.png"; break;
            }
            try {
                image = ImageIO.read(getClass().getResourceAsStream(imageSetter));
            } catch(IOException e) {
                e.printStackTrace();
            }
            }
            movingTimer --;
        solidArea.x = xAdjuster;
        solidArea.y = yAdjuster;
    }
}