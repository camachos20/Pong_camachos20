package com.example.camachos20.pong_camachos20;

import android.graphics.*;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Boolean.FALSE;


/**
 * class that animates a ball to shoot out randomly and bounce off the
 * 3 walls or paddle or exit the screen
 *
 * @author Stephanie Camacho
 * @version 21 March 2018
 */

public class BallAnimator implements Animator {

    // instance variables
    private boolean reverseX = false;
    private boolean reverseY = false;
    private Random gen = new Random();
    private ArrayList<Ball> ballArr = new ArrayList<>(); //arrayList to hold balls

    private int BALL_RADIUS = 40;
    private int WIDTH = 2000;
    private int HEIGHT = 1400;
    public BallAnimator() {

        //creates a new ball object with randomized position and velocity
        Ball firstBall = new Ball(xRand(), yRand(), xVelocity(), yVelocity());
        ballArr.add(firstBall); //adds newly created ball to arrayList

    }


    /**
     * generates a random int for the ball's x position
     *
     * @return randomized x position
     */
    public int xRand() {
        /**
         External Citation
         Date:     21 March 2018
         Problem: forgot how Random.nextInt() worked
         Resource: https://docs.oracle.com/javase/
         8/docs/api/java/util/Random.html#nextInt--
         Solution: it generates a random int from 0 (inclusive)
         to the bound (exclusive)
         */
        return gen.nextInt(1960)+BALL_RADIUS; //returns a random int within the canvas width
    }

    public int yRand() {
        return gen.nextInt(1360)+BALL_RADIUS; //returns a random int within the canvas height
    }

    /**
     * generates a random int for the ball's velocity in the x direction
     *
     * @return randomized x velocity
     */
    public int xVelocity () {
        /**
         External Citation
         Date:     21 March 2018
         Problem: couldn't figure out how to generate both negative and
         positive random numbers
         Resource: https://stackoverflow.com/questions/3938992/
         how-to-generate-random-positive-and-negative-numbers-in-java
         Solution: realized I'm just dumb and probably could've figured it out
         */


        return gen.nextInt(61) - 30; //returns a random int from -30 to 30


    }

    /**
     * generates a random int for the ball's velocity in the y direction
     *
     * @return randomized y velocity
     */
    public int yVelocity () {
        return gen.nextInt(61) - 30; //returns a random int from -30 to 30

    }

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
    public int backgroundColor() {return Color.BLACK;}


    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {

        //check if ball hits any of the walls or the paddle and bounces them in
        //the opposite direction (consistent with physics)
        for (int i = 0; i < ballArr.size(); i++) {

            // check if ball hits left wall
            if (ballArr.get(i).xPos < BALL_RADIUS) {

                //slows ball slightly to represent friction
                //negative sign reflects change in direction
                ballArr.get(i).xVelocity *= -0.9;

            }

            // check if ball hits right wall
            else if (ballArr.get(i).xPos > WIDTH - BALL_RADIUS) {

                //slows ball slightly to represent friction
                //negative sign reflects change in direction
                ballArr.get(i).xVelocity *= -0.9;

            }

            //check if ball hits top wall
            else if (ballArr.get(i).yPos < BALL_RADIUS) {

                // slows ball slightly to represent friction
                //negative sign represent change in direction
                ballArr.get(i).yVelocity *= -0.9;

            }

            // check if ball hits paddle
            else if (ballArr.get(i).yPos > HEIGHT - 400 + BALL_RADIUS
                    && ballArr.get(i).xPos > 855
                    && ballArr.get(i).xPos < 1145) {

                //slows ball slightly to represent friction
                //negative sign reflects change in direction
                ballArr.get(i).yVelocity *= -0.9;

            }


            //updates position based on new (slower) velocity
            ballArr.get(i).xPos += ballArr.get(i).xVelocity;
            ballArr.get(i).yPos += ballArr.get(i).yVelocity;

        }



        //paint for ball
        Paint mintPaint = new Paint();
        mintPaint.setColor(Color.rgb(153,255,204)); //mint color

        /**
         External Citation
         Date:     21 March 2018
         Problem: didn't know how to get from arrayList
         Resource: https://beginnersbook.com/2013/12/java-arraylist-
                    get-method-example/
         Solution: used ArrayList.get(index) to get the ball
         objects in the arrayList
         */

        //draws the balls in the ballArr arrayList
        for (int i = 0; i < ballArr.size(); i++) {
            g.drawCircle(ballArr.get(i).xPos, ballArr.get(i).yPos,
                    BALL_RADIUS , mintPaint);
        }


        //Draw paddle at bottom of screen
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        g.drawRect(850, g.getHeight()-20 , 1150, g.getHeight(), whitePaint);

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
     * add a new ball object to the screen when the screen is tapped
     */
    public void onTouch(MotionEvent event) {

        /**
         External Citation
         Date:     21 March 2018
         Problem: was not familiar with MotionEvent class
         Resource: https://developer.android.com/reference/android/
                    view/MotionEvent.html
         Solution: found that I need to use getAction() and ACTION_DOWN
         */

        //check if screen was pressed
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            Ball anotherBall =
                    new Ball(xRand(), yRand(), xVelocity(),yVelocity());
            ballArr.add(anotherBall);

        }
    }



}//class BallAnimator


