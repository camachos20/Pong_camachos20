package com.example.camachos20.pong_camachos20;

import android.graphics.*;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
    private ArrayList<Ball> ballArr = new ArrayList<>(); //arrayList to hold balls

    private Random gen = new Random();
    private int fingerXpos = 0; //used to store x position of user touches
    private int ballCount = 0;
    private int score = 0;
    private int BALL_RADIUS = 40;
    private int WIDTH;
    private int HEIGHT;
    private int WALL_WIDTH = 100;
    private final double SLOW_DOWN = 0.9;
    private final double SPEED_UP = 1.01;
    private int paddleWidth;
    private  int paddleLeft = ((WIDTH-(2*WALL_WIDTH))/2 - paddleWidth/2);
    private  int paddleRight = ((WIDTH-(2*WALL_WIDTH))/2 + paddleWidth/2);



    private final int PADDLE_WIDTH_MEDIUM = 300;
    private final int PADDLE_WIDTH_EASY = 500;
    private final int PADDLE_WIDTH_HARD = 150;
    private final int PADDLE_HEIGHT = 50;









    public BallAnimator() {

        //creates a new ball object with randomized position and velocity
        Ball firstBall = new Ball(0, 0, randXVelocity(), randYVelocity());
        ballArr.add(firstBall); //adds newly created ball to arrayList
        ballCount++;


    }


    /**
     * Interval between animation frames: .03 seconds (i.e., about 33 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 10;
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

        WIDTH = g.getWidth();
        HEIGHT = g.getHeight();

        drawWalls(g);
        drawPaddle(g);
        drawBalls(g);
        drawScore(g);





        //check if ball hits any of the walls or the paddle and bounces them in
        //the opposite direction (consistent with physics)
        for (int i = 0; i < ballCount; i++) {

            //check if ball is in left range and moving left
            if (ballArr.get(i).xPos < BALL_RADIUS + WALL_WIDTH &&
                    ballArr.get(i).xVelocity < 0)
            {
                //check if it is in top left corner & moving up
                // then reverse both x and y direction
                if (ballArr.get(i).yPos < BALL_RADIUS + WALL_WIDTH &&
                        ballArr.get(i).yVelocity< 0)
                {
                    ballArr.get(i).xVelocity *=-SLOW_DOWN;
                    ballArr.get(i).yVelocity *=-SLOW_DOWN;
                }
                //check if it is in bottom left corner & moving down
                //then reverse both x and y direction
                else if (ballArr.get(i).yPos > HEIGHT - BALL_RADIUS - WALL_WIDTH
                        && ballArr.get(i).yVelocity > 0)
                {
                    ballArr.get(i).xVelocity *=-SLOW_DOWN;
                    ballArr.get(i).yVelocity *=-SLOW_DOWN;
                }
                else
                {
                    ballArr.get(i).xVelocity *=-SLOW_DOWN; //reverse only the x direction
                }

                //update ball x pos with new velocity
                ballArr.get(i).xPos += ballArr.get(i).xVelocity;
                ballArr.get(i).yPos += ballArr.get(i).yVelocity;
            }


            //check if ball is in right range and moving right
            if (ballArr.get(i).xPos > WIDTH - WALL_WIDTH - BALL_RADIUS &&
                    ballArr.get(i).xVelocity > 0)
            {
                //Check if it is in top right corner & moving up
                // then reverse both x and y dir
                if (ballArr.get(i).yPos < BALL_RADIUS + WALL_WIDTH &&
                        ballArr.get(i).yVelocity < 0)
                {
                    ballArr.get(i).xVelocity *= -SLOW_DOWN;
                    ballArr.get(i).yVelocity *= -SLOW_DOWN;
                }
                //check if it is in bottom right corner & moving down
                //then reverse both x and y dir
                else if (ballArr.get(i).yPos > HEIGHT - BALL_RADIUS - WALL_WIDTH
                        && ballArr.get(i).yVelocity > 0)
                {
                    ballArr.get(i).xVelocity *= -SLOW_DOWN;
                    ballArr.get(i).yVelocity *= -SLOW_DOWN;
                }
                else
                {
                    ballArr.get(i).xVelocity *=-SLOW_DOWN; //reverse only the x direction
                }
            }

            //check if ball hits the top and is moving up
            if (ballArr.get(i).yPos < BALL_RADIUS + WALL_WIDTH &&
                    ballArr.get(i).yVelocity < 0)
            {
                //check if it hits left top corner and is moving left
                //then reverse both x and y directions
                if (ballArr.get(i).xPos < BALL_RADIUS + WALL_WIDTH &&
                        ballArr.get(i).xVelocity < 0)
                {
                    ballArr.get(i).xVelocity *= -SLOW_DOWN;
                    ballArr.get(i).yVelocity *=-SLOW_DOWN;
                }
                //checks if it hits top right corner and is moving right
                //then reveres both x and y directions
                else if (ballArr.get(i).xPos > WIDTH - BALL_RADIUS - WALL_WIDTH
                        && ballArr.get(i).xVelocity > 0)
                {
                    ballArr.get(i).xVelocity *= -SLOW_DOWN;
                    ballArr.get(i).yVelocity *=-SLOW_DOWN;
                }
                else
                {
                    ballArr.get(i).yVelocity *=-SLOW_DOWN; //reverse only the y direction
                }

                //update ball x pos with new velocity
                ballArr.get(i).xPos += ballArr.get(i).xVelocity;
                ballArr.get(i).yPos += ballArr.get(i).yVelocity;
            }

            //check if ball is moving down
            if (ballArr.get(i).yVelocity > 0)
            {
                //check if ball hits paddle and speeds it up and reverses y dir
                if (ballArr.get(i).yPos >
                        (HEIGHT - WALL_WIDTH - BALL_RADIUS - PADDLE_HEIGHT))
                {
                    if (ballArr.get(i).xPos < paddleRight + (2*BALL_RADIUS)
                            && ballArr.get(i).xPos > paddleLeft - (2*BALL_RADIUS))
                    {
                        ballArr.get(i).yVelocity *=-SPEED_UP;
                        score+=1;
                    }
                    //update ball x pos with new velocity
                    ballArr.get(i).xPos += ballArr.get(i).xVelocity;
                    ballArr.get(i).yPos += ballArr.get(i).yVelocity;

                }

                //check if ball exited bottom of screen
                else
                {
                    ballArr.remove(i);
                    if (ballArr.size() < 8)
                    {
                        Ball newBall = new Ball(xRand(), yRand(),
                                randXVelocity(),randYVelocity());
                        ballArr.add(newBall);

                        //update ball x pos with new velocity
                        ballArr.get(i).xPos += ballArr.get(i).xVelocity;
                        ballArr.get(i).yPos += ballArr.get(i).yVelocity;

                        //update score
                        score-=2;
                    }

                }




            }

        }

    }//tick

    //returns a random int within the canvas width
    public int xRand()
    {
        int x = (gen.nextInt(WIDTH-(2*WALL_WIDTH)))+BALL_RADIUS;
        return x;
    }

    //returns a random int within the canvas height
    public int yRand()
    {
        int y = gen.nextInt((HEIGHT-(2*WALL_WIDTH)))+BALL_RADIUS;
        return y;
    }

    /**
     * generates a random int for the ball's velocity in the x direction
     *
     * @return randomized x velocity
     */
    public int randXVelocity () {
        /**
         External Citation
         Date:     21 March 2018
         Problem: couldn't figure out how to generate both negative and
         positive random numbers
         Resource: https://stackoverflow.com/questions/3938992/
         how-to-generate-random-positive-and-negative-numbers-in-java
         Solution: realized I'm just dumb and probably could've figured it out
         */

        int temp1 = gen.nextInt(10) + 10;//returns a random int from 10 TO 19
        int temp2 = gen.nextInt(10) + 10; //returns a random int from 10 TO 19

        if (gen.nextInt(2)==0)
        {
            return temp1;
        }
        else
        {
            return temp2;
        }


    }

    /**
     * generates a random int for the ball's velocity in the y direction
     *
     * @return randomized y velocity
     */
    public int randYVelocity () {

        int temp1 = gen.nextInt(10) + 10;//returns a random int from 10 TO 19
        int temp2 = gen.nextInt(10) + 10; //returns a random int from 10 TO 19

        if (gen.nextInt(2)==0)
        {
            return temp1;
        }
        else
        {
            return temp2;
        }
    }

    /**
     *draws the paddle at bottom of screen based on where finger x position is
     *
     * @param g
     *
     *
     * @return void
     */
    public void drawPaddle(Canvas g)
    {
        Paint whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        g.drawRect(paddleLeft,HEIGHT-PADDLE_HEIGHT-WALL_WIDTH,paddleRight,HEIGHT+WALL_WIDTH,whitePaint);

    }

    /**
     *draws the walls
     *
     * @return void
     */
    public void drawWalls (Canvas g)
    {
        Paint wallPaint = new Paint();
        wallPaint.setColor((Color.GREEN));
        g.drawRect(0,0,WIDTH,WALL_WIDTH, wallPaint); //top wall
        g.drawRect(0,0,WALL_WIDTH,HEIGHT, wallPaint); //left wall
        g.drawRect(WIDTH-WALL_WIDTH, 0, WIDTH,HEIGHT, wallPaint); //right wall
    }
    /**
     *draws the balls in the ballArr arrayList
     *
     * @return void
     */
    public void drawBalls (Canvas g)
    {
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

        for (int i = 0; i < ballArr.size(); i++) {
            g.drawCircle(ballArr.get(i).xPos, ballArr.get(i).yPos,
                    BALL_RADIUS , mintPaint);
        }

    }

    public void drawScore(Canvas g){
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        //Set the text size
        white.setTextSize(100.0f);
        //Print the player's score
        g.drawText("Score: "+Integer.toString(score), WIDTH - WIDTH/4, HEIGHT/6, white);

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
         Solution: found that I need to use getAction() and ACTION_MOVE
         */

        //check if screen was pressed
        if (event.getAction() == MotionEvent.ACTION_MOVE) {

            if (event.getX() < WIDTH - (paddleWidth / 2) &&
                    event.getX() > paddleWidth / 2)
            {
                fingerXpos = (int) event.getX();
            }
            else if (event.getX() > WIDTH - (paddleWidth / 2))
            {
                fingerXpos = paddleWidth / 2;
            }
            else if (event.getX() < paddleWidth / 2)
            {
                fingerXpos = WIDTH - (paddleWidth / 2);
            }
        }

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Ball newBall = new Ball(xRand(),yRand(),randXVelocity(),randYVelocity());
            ballArr.add(newBall);

        }
    }


    public int getPADDLE_WIDTH_MEDIUM() {
        return PADDLE_WIDTH_MEDIUM;
    }

    public int getPADDLE_WIDTH_EASY() {
        return PADDLE_WIDTH_EASY;
    }

    public int getPADDLE_WIDTH_HARD() {
        return PADDLE_WIDTH_HARD;
    }

    public void setPaddleWidth(int paddleWidth) {
        this.paddleWidth = paddleWidth;
    }


}//class BallAnimator


