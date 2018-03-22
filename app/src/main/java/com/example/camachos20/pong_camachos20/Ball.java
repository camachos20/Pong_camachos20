package com.example.camachos20.pong_camachos20;

/**
 * class that creates a ball object at given position with given velocity
 *
 * @author Stephanie Camacho
 * @version 21 March 2018
 */

public class Ball {

    //instance variables
    protected int xPos; //x position
    protected int yPos; //y position
    protected int xVelocity; //x velocity
    protected int yVelocity; //y velocity


    public Ball(int initXPos, int initYPos, int initXVel, int initYVel) {
        xPos = initXPos;
        yPos = initYPos;
        xVelocity = initXVel;
        yVelocity = initYVel;

    }
}
