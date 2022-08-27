package PongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// To Draw paddles on Screen we have to use Rectangle to we are Extending Rectangle class here
public class Paddle extends Rectangle {

    int id;  // one id of player one and one id for player 2
    int yVelocity; // How fast Paddle is moving up and down when we press the keys keys
    int speed = 10;

    Paddle(int x,int y,int PADDLE_WIDTH,int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);  //Paddle is SubClass of Reactangle Class to we have to pass all
        // parameters of Paddle class Rectangle class using super keyword
        this.id = id;  // to Assign "id" to Rectangle class
    }

    // Here we are connect ActionListeners of KeyPressed and KeyRelased Method. when key is pressed then what happens
    // with paddle that is here defined
    public void keyPressed(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);            // To move paddle1 to Upwards
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(speed);            // To move paddle1 to DownWords
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-speed);            // To move paddle1 to Upwards
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);            // To move paddle1 to DownWords
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(0);            // To move paddle1 to Upwards
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);            // To move paddle1 to DownWords
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);            // To move paddle1 to Upwards
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);            // To move paddle1 to DownWords
                    move();
                }
                break;
        }
    }

    // The paddles are only moving in YDirection so we don't need to define XDirection Function for Paddles
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void move(){
        y = y + yVelocity;
    }

    // to draw the 2 paddles on Screen we use draw() method
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.BLUE);
        }
        if(id==2){
            g.setColor(Color.RED);
        }
        g.fillRect(x,y,width,height); // to Fill the above both Paddle Rectangles
    }
}
