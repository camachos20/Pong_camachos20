package com.example.camachos20.pong_camachos20;

import android.graphics.*;
import android.view.MotionEvent;

import java.util.Random;

import static java.lang.Boolean.FALSE;


/**
 * class that animates a ball to shoot out randomly and bounce off the 3 walls or exit the screen
 *
 * @author Stephanie Camacho
 * @version 21 March 2018
 */

public class BallAnimator implements Animator {

    // instance variables
    private int countX; //x position of ball
    private int countY; //y position of ball
    private boolean reverseX = false;
    private boolean reverseY = false;
    private Random gen = new Random();

    private int screenWidth = 2048;
    private int screenLength = 1536;



    /**
     * Interval between animation frames: .03 seconds (i.e., about 33 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 30;
    }

    /**
     * The background color: black.
     *
     * @return the background color onto which we will draw the image.
     */
    public int backgroundColor() {
        // return the background color
        return Color.BLACK;
    }


    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {
        // bump our count either up or down by one, depending on whether
        // we are in "reverse mode"
        if (reverseX) {
            countX--;
        }
        else {
            countX++;
        }
        if(reverseY) {
            countY--;
        }
        else {
            countY++;
        }


        // Determine the pixel position of our ball.  Multiplying by 15
        // has the effect of moving 15 pixel per frame.  Modding by 600
        // (with the appropriate correction if the value was negative)
        // has the effect of "wrapping around" when we get to either end
        // (since our canvas size is 600 in each dimension).
        int numX = (countX*15)%600;
        if (numX < 0) numX += 600;

        int numY = (countY*15)%600;
        if (numY < 0) numY+=600;

        //check where ball is and determine whether it hits a wall and reverse the direction
        if (numX < 0 || numX > screenWidth)
        {
            reverseX = !reverseX;
        }
        if (numY < 0 || numY > screenLength)
        {
            reverseY = !reverseY;
        }




        // Draw the ball in the correct position.
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        g.drawCircle(numX, numY, 40, redPaint);
        redPaint.setColor(0xff0000ff);

        //Draw paddle at bottom of screen
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        g.drawRect(screenLength, 874 ,screenLength-20,1174, whitePaint);

    }

    /**
     * Tells that we never pause.
     *
     * @return indication of whether to pause
     */
    public boolean doPause() {
        return false;
    }

    /**
     * Tells that we never stop the animation.
     *
     * @return indication of whether to quit.
     */
    public boolean doQuit() {
        return false;
    }

    /**
     * reverse the ball's direction when the screen is tapped
     */
    public void onTouch(MotionEvent event) {
    }



}//class BallAnimator
