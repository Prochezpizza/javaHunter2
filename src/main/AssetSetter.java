package main;

import object.Obj_Key;
import object.Obj_Door;
import object.Obj_Cow;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 20 * gp.tileSize;
        gp.obj[0].worldY = 16 * gp.tileSize;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 20 * gp.tileSize;
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX = 7 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;

        gp.obj[4] = new Obj_Cow();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 15 * gp.tileSize;
    }
}
