package entity;

import controls.KeyboardControler;
import controls.MouseController;
import main.GamePanel;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class Player extends Entity {


    GamePanel gp;
    MouseController mCtrl;
    KeyboardControler kCtrl;
    public final int displayX;
    public final int displayY;
    public int desiredLocationX;
    public int desiredLocationY;



    public Player(GamePanel gp, MouseController mCtrl, KeyboardControler kCtrl) {
        this.gp = gp;
        this.mCtrl = mCtrl;
        this.kCtrl = kCtrl;

        defaultValues();
        getPlayerImage();

        displayX = gp.screenWidth/2 - gp.tileSize/2;
        displayY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle(8,16,32,32);

    }

    public void defaultValues() {


        locationX = 465;
        locationY = 333;
        savedLocationX = 0;
        savedLocationY = 0;

        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {

        try {

            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));


        } catch (IOException e) {
            e.printStackTrace();
            if (up1 == null) {
                System.out.println("loaded");
            }
        }

    }
    public void update() {



        desiredLocationX = savedLocationX + mCtrl.x - displayX;
        desiredLocationY = savedLocationY + mCtrl.y - displayY;
        //System.out.println(locationX + "   " + locationY);
        if (mCtrl.click) {
            savedLocationX = locationX;
            savedLocationY = locationY;

            System.out.println(savedLocationX);
            System.out.println(savedLocationY);

         //   System.out.println("DESIRED" + desiredLocationX);
         //  System.out.println("DESIRED" + desiredLocationY);
        }



        boolean moving;
        boolean lockX = Math.abs(locationX - desiredLocationX) <= speed + 1;
        boolean lockY = Math.abs(locationY - desiredLocationY) <= speed + 1;



        if(!lockX && locationX > desiredLocationX) {
            direction = "left";
        } else if (!lockX && locationX < desiredLocationX){
            direction = "right";
        } else if (!lockY && locationY > desiredLocationY) {
            direction = "up";
        } else if (!lockY && locationY < desiredLocationY) {
            direction = "down";
        }

        collisionOn = false;
        gp.collisionChecker.checkTile(this);


        if(collisionOn == false) {
        if (!lockX && locationX > desiredLocationX) {
            locationX -= 3;
            moving = true;

        }
        else if (!lockX && locationX < desiredLocationX) {
            locationX += 3;
            moving = true;

        }
        else if (!lockY && locationY > desiredLocationY) {
            locationY -= 3;
            moving = true;

        }
        else if (!lockY && locationY < desiredLocationY) {
            locationY += 3;
            moving = true;


        } else {
            moving = false;
        }



            if (moving == true) {
                spriteCounter++;
                if (spriteCounter > 20) {
                    if (spriteNum == 1) {
                        spriteNum = 0;
                    } else {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }



        }












//        if (kCtrl.moveUp == true) {
//            direction = "up";
//            locationY -= speed;
//            moving = true;
//        }
//        else if (kCtrl.moveDown == true) {
//
//            direction = "down";
//            locationY += speed;
//            moving = true;
//        }
//        else if (kCtrl.moveLeft == true) {
//            direction = "left";
//            locationX -= speed;
//            moving = true;
//        }
//        else if (kCtrl.moveRight == true) {
//            direction = "right";
//            locationX += speed;
//            moving = true;
//        } else {
//            moving = false;
//        }




    }

    public void draw(Graphics2D g2) {

       BufferedImage image = null;

        switch(direction) {

            case "up":
                if(spriteNum == 1) {
                    image = up3;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                } else {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,displayX,displayY,gp.tileSize,gp.tileSize,null);
        g2.draw(solidArea);
    }
}
