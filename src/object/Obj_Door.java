package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Door extends SuperObject {
    public Obj_Door() {
        solidArea.width = 256;
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/doors.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}