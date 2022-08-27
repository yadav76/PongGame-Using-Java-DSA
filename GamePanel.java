package PongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Game Panel is act like Canvas where we are painting

// Because fo Extending JPanel Our GamePanel use all properties of JPanel
public class GamePanel extends JPanel implements Runnable {
    // Runnable makes these Interface on Thread

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*(0.5555));  // IF I change my width then my height will adjusted auto
    // Here I am multiplying GAME_WIDTH with (0.5555) because it make a Professional PigPong Game Table.

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT); // to create Dimensions for Game

    static final int BALL_DIAMETER = 20; // Higher the Number Higher the Ball Diameter
    static final int PADDLE_WIDTH = 25;  // Height for Paddles
    static final int PADDLE_HEIGHT = 100;  // Width for Paddles

    // using static Keyword is when we create multiple instances of GamePanel class they all share same GAME_WIDTH
    // using final keyword probites us to make changes in GAME_WIDTH variable in later stage and it's good practise to use final keyword

    Thread gameThread;  // Thread is coming from runnable Thread we defined above
    Image image;
    Graphics graphics;
    Random random;    // to move the ball Randomally I am using this random
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GamePanel(){
        newPaddle();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT); //To use these we have to pass these Two parameters in Score Cunstructor
        this.setFocusable(true);  // Focus on is there is any key is pressed from keyboard
        this.addKeyListener(new AL()); // To Focus on KeyStrokes from AL Inner Class ActionListener
        this.setPreferredSize(SCREEN_SIZE);  // To Set Dimensions

        // To Start the Thread We have to Intialize It and Start is
        gameThread = new Thread(this);  // I have Implemented Runnable Interface to I have to pass "this"
        gameThread.start();
    }

    public void newBall(){
        // Whenever I click these method A New Ball is created and the ball moves in random direction
        random = new Random();  // Intantiatting the random method that we Declared above

        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
        // to create new Ball. I have to make ball at middle of the screen so I am using GAME_WIDTH/2 and GAME_HEIGHT/2
        // Here Width and Height of the Ball is same because it is round shape.
    }

    public void newPaddle(){
        // we call these method whenever we want to reset level or Game
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public void paint(Graphics g){
        // to paint stuff of screen
        image = createImage(getWidth(),getHeight());  // passing two function to get width and height of Image
        graphics = image.getGraphics();
        draw(graphics);  // To draw our Graphics that we made above by attching our Image with Graphics
        g.drawImage(image,0,0,this);  //where x=0 and y=0 from Where our game is start and this for our GamePanel
    }

    public void draw(Graphics g){
        // to draw things
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);  // to draw the ball
        score.draw(g);  // to Draw the Score
    }

    public void move(){
        // to move the things
        paddle1.move();  // to move paddle1 work smoothly
        paddle2.move();  // to move paddle1 work smoothly
        // move() method is extending the JSwing class because of that we don't have to write move method by own
        // move() method automatically use the JSwing move() method to run both paddles smoothly
        ball.move();  // to move ball smoothly
    }

    public void checkCollision(){
        // to check Collisions for the Ball to stop at up and Bottom of Screen
        if(ball.y<=0){
            ball.setYDirection(-ball.yVelocity);  // Here I am adding "-" to reverse the yVelocity when Ball Hits
            // upper side of Screen
        }
        if(ball.y>=GAME_HEIGHT-BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);  // Here I am adding "-" to reverse the yVelocity when Ball Hits
            // lower side of Screen
        }

        // Check Collision for When Ball touches with Both Paddles
        // Since Ball Class Extending Rectangle class so we can use intersect() method of Rectangle class to check the
        // Collision between Paddle and Ball

        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity); // to make xVelocity positive after Collision
            ball.xVelocity++;  // for Increasing the speed after collision
            if(ball.yVelocity>0){
                ball.yVelocity++;
            }else{
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity); // to make xVelocity positive after Collision
            ball.xVelocity++;  // for Increasing the speed after collision
            if(ball.yVelocity>0){
                ball.yVelocity++;
            }else{
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity); // adding "-" to flip the xVelocity Because It is opposite side
            ball.setYDirection(ball.yVelocity);
        }

        // to check Collisions
        // to stop both paddles at window edge
        if(paddle1.y<=0){
            paddle1.y=0;  // for stopping paddle1 at up side
        }
        if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;  // for stopping paddle1 at down side
        }
        if(paddle2.y<=0){
            paddle2.y=0;  // for stopping paddle2 at up side
        }
        if(paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;  // for stopping paddle2 at down side
        }

        // Give Player 1 point and Creates new Ball and new Paddles
        if(ball.x<=0){   // for increasing the score of player2
            score.player2++;
            newPaddle();
            newBall();
            System.out.println("Player2 "+score.player2);
        }

        if(ball.x>=GAME_WIDTH-BALL_DIAMETER){   // for increasing the score of player1
            score.player1++;
            newPaddle();
            newBall();
            System.out.println("Player1 "+score.player1);
        }
    }

    public void run(){
        // to run things
        // to Create Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
//                System.out.println("TEST"); // To Test all Things are Working Properly
            }
        }
    }

    // Inner Class for Action Listener
    public class AL extends KeyAdapter{
        // AL = Action Listener
        // These class we made for taking input from Keyboard
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

}
