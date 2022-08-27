package PongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// GameFrame is Frame around the Painting

// Here we are Extanding JFram so we can Treat are GameFrame as JFrame
// GameFrame has all minimize button and Close Button that all stuff are written here
public class GameFrame extends JFrame{

    GamePanel panel;
    GameFrame(){
        panel = new GamePanel();
        this.add(panel); // to add our panel to Frame
        this.setTitle("Pong Game");
        this.setResizable(false); // we do not want to people resize it so the value is false
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // when we click on "X" button our app will exit
        this.pack(); // we don't have to set size to our GameFrame it accomodate size of GamePanel
        this.setVisible(true);  // so we can actually see this
        this.setLocationRelativeTo(null); // to appear game screen in middle of the DeskTop
    }

}
