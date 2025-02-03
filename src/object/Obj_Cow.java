package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Cow extends SuperObject {
    public Obj_Cow() {
        name = "Cow";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/animals/cowLeft.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}