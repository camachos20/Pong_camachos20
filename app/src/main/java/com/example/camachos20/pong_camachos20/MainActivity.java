package com.example.camachos20.pong_camachos20;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * PongMainActivity
 *
 * This is the activity for the Pong game. It attaches a PongAnimator to
 * an AnimationSurface.
 *
 * @author Andrew Nuxoll
 * @author Steven R. Vegdahl
 * @version July 2013
 *
 */
public class MainActivity extends Activity implements View.OnClickListener{

    /**
     * creates an AnimationSurface containing a TestAnimator.
     */

    private BallAnimator myBallAnimator = new BallAnimator();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the animation surface with the animator
        AnimationSurface mySurface = (AnimationSurface)
                this.findViewById(R.id.animationSurface);
        mySurface.setAnimator(myBallAnimator);


        //references to  buttons
        Button easyButt = (Button) findViewById(R.id.buttonEasy);
        Button medButt = (Button) findViewById(R.id.buttonMedium);
        Button hardButt = (Button) findViewById(R.id.buttonDifficult);

        //Set listeners for buttons
        easyButt.setOnClickListener(this);
        medButt.setOnClickListener(this);
        hardButt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        //If easy level is clicked, change the paddle size to easy
        if(v.getId() == R.id.buttonEasy) {
            myBallAnimator.setPaddleWidth(myBallAnimator.getPADDLE_WIDTH_EASY());
        }
        //If medium level is clicked, change paddle to medium
        else if(v.getId() == R.id.buttonMedium) {
            myBallAnimator.setPaddleWidth(myBallAnimator.getPADDLE_WIDTH_MEDIUM());
        }
        else if(v.getId() == R.id.buttonDifficult) {
            myBallAnimator.setPaddleWidth(myBallAnimator.getPADDLE_WIDTH_HARD());
        }
    }
}




