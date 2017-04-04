package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.graphics.*;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

/**
 * Created by devinajimine on 4/2/17.
 */

/**
 * class that animates a ball repeatedly moving diagonally on
 * simple background
 *
 * @author Steve Vegdahl
 * @author Andrew Nuxoll
 * @version September 2012
 */
public class cannonAnimator implements Animator
{
    // instance variables
    private int count = 0; // counts the number of logical clock ticks
    private boolean goBackwards = false; // whether clock is ticking backwards
    private boolean fire = false;
    private int posX = 0;
    private int posY = 0;
    private int velocity = 150;
    private double angle = 45*3.14/180;
    private final double GRAVITY = 9.8;
    private target targetTest;
    Random myRand = new Random();
    private int daColor;


    /**
     * Interval between animation frames: .03 seconds (i.e., about 33 times
     * per second).
     *
     * @return the time interval between frames, in milliseconds.
     */
    public int interval() {
        return 30;
    }


    //create different




    /**
     * The background color: a light blue.
     *
     * @return the background color onto which we will draw the image.
     */
    public int backgroundColor() {
        // create/return the background color
        return Color.rgb(180, 200, 255);
    }

    /**
     * Tells the animation whether to go backwards.
     *
     * @param b true iff animation is to go backwards.
     */
    public void goBackwards(boolean b) {
        // set our instance variable
        goBackwards = b;
    }

    /**
     * Action to perform on clock tick
     *
     * @param g the graphics object on which to draw
     */
    public void tick(Canvas g) {
        // bump our count either up or down by one, depending on whether
        // we are in "backwards mode".


        daColor = Color.rgb(myRand.nextInt(256), myRand.nextInt(256),
                myRand.nextInt(256));
        if(targetTest.containsPoint(posX +175,posY+1250))
        {
            daColor = Color.GREEN;
        }
        targetTest = new target("", daColor, 1000, 500, 100);
        targetTest.drawMe(g);
        target targetTest2 = new target("", Color.RED, 800, 900, 75);
        targetTest2.drawMe(g);




        if(fire)
        {
            count++;

            posX = (int)(velocity*Math.cos(angle)*count);
            posY = (int)(-((velocity*Math.sin(angle)*count) - (.5*GRAVITY*count*count)));
            // Draw the ball in the correct position.
            Paint redPaint = new Paint();
            redPaint.setColor(Color.RED);
            g.drawCircle(posX +175, posY+1250, 60, redPaint);
            Log.i("angle value:", angle + "");

        }




        Paint colorB = new Paint();
        colorB.setColor(Color.BLACK);
        cannon canonTest = new cannon(0,0);


        g.save();
        g.rotate((float)(-(angle*180/3.14)+90),100,1200);
        g.drawRect(0,1150,250,1500, colorB);
        g.restore();


        // Determine the pixel position of our ball.  Multiplying by 15
        // has the effect of moving 15 pixel per frame.  Modding by 600
        // (with the appropriate correction if the value was negative)
        // has the effect of "wrapping around" when we get to either end
        // (since our canvas size is 600 in each dimension).




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
    public void onTouch(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            goBackwards = !goBackwards;
        }
    }

    public void fire()
    {
        fire = true;
        count =0;
    }


    public void setAngle(int angle) {
        this.angle = angle * 3.14 / 180;
    }
}

