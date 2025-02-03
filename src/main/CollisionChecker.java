package main;

import entity.Entity;

public class CollisionChecker extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public CollisionChecker(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
    }

    public void checkTile(Entity entity) {
        // Finding Relative Position of Entity to the World
        //        Top Y
        // Left X      Right X
        //       Bottom Y
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Set Rows and Columns
        int collider0, collider1, collider2, collider3, collider4, collider5, collider6, collider7, collider8, collider9, collider10, collider11, collider12, collider13, collider14, collider15;
        
        int column0, column1, column2, column3, column4;
        int row0, row1, row2, row3, row4;

        column0 = (entityLeftX - entity.speed) / gp.tileSize;
        column1 = (entityLeftX + ((entityRightX - entityLeftX)/5)) / gp.tileSize;
        column2 = (entityLeftX + ((entityRightX - entityLeftX)/2)) / gp.tileSize;
        column3 = (entityLeftX + ((entityRightX - entityLeftX)/5*4)) / gp.tileSize;
        column4 = (entityRightX + entity.speed) / gp.tileSize;

        row0 = (entityTopY - entity.speed) / gp.tileSize;
        row1 = (entityTopY + ((entityBottomY - entityTopY)/5)) / gp.tileSize;
        row2 = (entityTopY + ((entityBottomY - entityTopY)/2)) / gp.tileSize;
        row3 = (entityTopY + ((entityBottomY - entityTopY)/5*4)) / gp.tileSize;
        row4 = (entityBottomY + entity.speed) / gp.tileSize;

        // Colliders (See CollisionMap.txt)
        collider0  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column0][row0]].collision ? 1 : 0;
        collider1  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column1][row0]].collision ? 1 : 0;
        collider2  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column2][row0]].collision ? 1 : 0;
        collider3  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column3][row0]].collision ? 1 : 0;
        collider4  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column4][row0]].collision ? 1 : 0;
        collider5  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column4][row1]].collision ? 1 : 0;
        collider6  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column4][row2]].collision ? 1 : 0;
        collider7  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column4][row3]].collision ? 1 : 0;
        collider8  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column4][row4]].collision ? 1 : 0;
        collider9  = gp.tileManager.tile[gp.tileManager.mapTileNumber[column3][row4]].collision ? 1 : 0;
        collider10 = gp.tileManager.tile[gp.tileManager.mapTileNumber[column2][row4]].collision ? 1 : 0;
        collider11 = gp.tileManager.tile[gp.tileManager.mapTileNumber[column1][row4]].collision ? 1 : 0;
        collider12 = gp.tileManager.tile[gp.tileManager.mapTileNumber[column0][row4]].collision ? 1 : 0;
        collider13 = gp.tileManager.tile[gp.tileManager.mapTileNumber[column0][row3]].collision ? 1 : 0;
        collider14 = gp.tileManager.tile[gp.tileManager.mapTileNumber[column0][row2]].collision ? 1 : 0;
        collider15 = gp.tileManager.tile[gp.tileManager.mapTileNumber[column0][row1]].collision ? 1 : 0;

        // Checking Multiple Collisions
        if (collider0 + collider1 + collider2 + collider3 + collider4 > 1) { entity.collisionUpOn = true; }
        if (collider12 + collider13 + collider14 + collider15 + collider0 > 1) { entity.collisionLeftOn = true; }
        if (collider4 + collider5 + collider6 + collider7 + collider8 > 1) { entity.collisionRightOn = true; }
        if (collider8 + collider9 + collider10 + collider11 + collider12 > 1) { entity.collisionDownOn = true; }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 418;
        String xDirection = "Left";
        String yDirection = "Up";

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                if (keyHandler.upPressed == true && keyHandler.downPressed == false) {
                    yDirection = "up";
                } else if (keyHandler.upPressed == false && keyHandler.downPressed == true) {
                    yDirection = "down";
                }
    
                if (keyHandler.leftPressed == true && keyHandler.rightPressed == false) {
                    xDirection = "left";
                } else if (keyHandler.leftPressed == false && keyHandler.rightPressed == true) {
                    xDirection = "right";
                }

                switch(yDirection) {
                    case "up":
                        entity.solidArea.y -= entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionUpOn = true;
                                entity.solidArea.y += entity.speed; 
                            }
                            if(player == true) {
                                index = i; 
                            }
                        }
                        break;
                    case "down": 
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionDownOn = true;
                                entity.solidArea.y -= entity.speed; 
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    default: break;
                }

                switch(xDirection) {
                    case "left": 
                        entity.solidArea.x -= entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionLeftOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right": 
                        entity.solidArea.x += entity.speed; 
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision) {
                                entity.collisionRightOn = true;
                                
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}


