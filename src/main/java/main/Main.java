package main;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {

     JFrame window = new JFrame();
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setResizable(false);
     window.setTitle("RPG");

     GamePanel gp = new GamePanel();
     gp.initialisation();
     window.add(gp);

     window.pack();

     window.setLocationRelativeTo(null);
     window.setVisible(true);




    }










}
