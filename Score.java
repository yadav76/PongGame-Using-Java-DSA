package PongGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// To Draw Score on Screen we have to use Rectangle to we are Extending Rectangle class here
public class Score extends Rectangle{

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;  // store score of player one
    int player2;  // store score of player tow
    Score(int GAME_WIDTH,int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;  // to set the height and width for score
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    // Function To draw the Score on Screen
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas",Font.PLAIN,60));

        // To draw the Line in Center of the Screen
        g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);

        // To draw Scores of both players on Screen
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10),(GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10),(GAME_WIDTH/2)+20, 50);

        // Here I am passing Two values of Score because I have to calculate TENS and DiGIT Score of both Players.
    }
}
