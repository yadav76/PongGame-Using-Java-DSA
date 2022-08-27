package PongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// To Draw Ball on Screen we have to use Rectangle to we are Extending Rectangle class here
public class Ball extends Rectangle {

    Random random;  // to move our ball Randomly
    int xVelocity; // How fast Ball moves on X axis
    int yVelocity; // How fast Ball moves on Y axis
    int initialSpeed = 2;

    Ball(int x,int y,int width,int height){
        // Since Ball is sub class of Rectangle class so I have to pass all above parameters to Rectangle class to use there properties
        super(x,y,width,height);
        random = new Random();
        int randomXDirection = random.nextInt(2);  // Here I set bound to 2 so there is only tow value for these
        // 0 and 1. If 0 then ball moves in X direction and if 1 then Ball moves in Y direction
        if(randomXDirection==0){
            randomXDirection--;
        }
        setXDirection(randomXDirection*initialSpeed);  // to set the X direction movement of the Ball

        int randomYDirection = random.nextInt(2);
        if(randomYDirection==0){
            randomYDirection--;
        }
        setYDirection(randomYDirection*initialSpeed);  // I Have to call setYDirection() function to set Y Direction of Ball
    }

    // The Ball is moving in both X and Y Direction so we have to define both X and Y Direction funciton for Ball.
    // The Ball is have to move in random direction so parameter taken in setYDirection is "randomXDirection"
    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }

    // The Ball is have to move in random direction so parameter taken in setYDirection is "randomYDirection"
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }

    // function to move the ball
    public void move(){
        x += xVelocity;   // to move ball in X Direciton
        y += yVelocity;   // to move ball in Y Direciton
    }

    // Function to draw ball on Screen
    public void draw(Graphics g){
        g.setColor(Color.WHITE);  // to set color of Ball
        g.fillOval(x,y,height,width);  // Since we are importing Rectangle class but shape of ball is Cirecle so
        // I am using fillOval(); method to make shape of Ball to circle. height and weight for Ball is same because
        // I passed both as BALL_DIAMETER in GAMEPANEL class
    }
}
