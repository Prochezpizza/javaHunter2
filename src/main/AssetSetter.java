package main;

import object.Obj_Key;
import object.Obj_Door;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Obj_Key();
        gp.obj[0].worldX = 20 * gp.tileSize;
        gp.obj[0].worldY = 18 * gp.tileSize;

        gp.obj[1] = new Obj_Key();
        gp.obj[1].worldX = 24 * gp.tileSize;
        gp.obj[1].worldY = 14 * gp.tileSize;

        gp.obj[2] = new Obj_Door();
        gp.obj[2].worldX = 11 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new Obj_Door();
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;
    }
}
