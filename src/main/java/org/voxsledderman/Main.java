package org.voxsledderman;

import org.voxsledderman.ui.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);

        ImageIcon imageIcon = new ImageIcon("assets/icon.png");
        frame.setIconImage(imageIcon.getImage());

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();

       frame.setResizable(true);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);


        gamePanel.launch();


    }
}