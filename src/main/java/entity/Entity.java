package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.management.BufferPoolMXBean;

public class Entity {


    public int locationX;
    public int locationY;
    public int savedLocationX;
    public int savedLocationY;

    public int speed;
    public BufferedImage up1,up2,down1,down2,peace,left1,left2,right1,right2,stand,up3,down3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = true;
}
