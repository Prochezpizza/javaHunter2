package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Key extends SuperObject {
    public Obj_Key() {
        solidArea.height = 256;
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        } catch(IOException e) {
            e.printStackTrace();

        }
    }
}
