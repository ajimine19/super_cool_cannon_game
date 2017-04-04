package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.graphics.*;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.MotionEvent;

import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * Created by devinajimine on 4/2/17.
 * Summary: Class that creates the animations. Below draws the animations and constantly
 * continues to save and restore the canvas.
 *
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
    private ArrayList<target> tars;

    //constructor to call on intialized methods
    public cannonAnimator()
    {
        tars = new ArrayList<>();
        intialTars();
    }

    //intialize the targets
    public void intialTars()
    {
        target targetTest = new target("", Color.RED, 100, 500, 100);
        target targetTest2 = new target("", Color.RED, 800, 900, 75);

        tars.add(targetTest);
        tars.add(targetTest2);
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

        //draws all the targets out
        for(target t : tars)
        {
            t.drawMe(g);
        }

        if(fire)
        {
            //calculates for gravity and sets the positions
            posX = (int)(velocity*Math.cos(angle)*count);
            posY = (int)(-((velocity*Math.sin(angle)*count) - (.5*GRAVITY*count*count)));

            // Draw the ball in the correct position.
            //TODO Paint
            Paint redPaint = new Paint();
            redPaint.setColor(Color.RED);


            g.drawCircle(posX +125, posY+1125, 60, redPaint);
            //Log.i("angle value:", angle + "");

            //iterate count to add multiple ticks
            count++;

            //Checks to see if the balls hits the the target
            //if the target is hit the setHit method will return a true so the target change color

        }

        for (target z : tars) {
            if (z.containsPoint(posX + 125, posY + 1100))
            {
                z.setHit();

            }
            else
            {

            }
        }

        //TODO working on custom Canon
        //cannon canonTest = new cannon(100,100);

        //TODO Paint
        Paint colorB = new Paint();
        colorB.setColor(Color.BLACK);

        //rotates the canvas
        g.save();
        g.rotate((float)(-(angle*180/3.14)+90),100,1150);
        g.drawRect(0,1000,200,1250, colorB);
        g.restore();

        //TODO PAint
        Paint baseColor = new Paint();
        baseColor.setColor(Color.RED);

        //draws base
        Path triangle = new Path();
        triangle.moveTo(0,1000);
        triangle.lineTo(300,1350);
        triangle.lineTo(0,1350);
        g.drawPath(triangle,baseColor);


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

    //when the button is pressed count ticks changes
    public void fire()
    {
        fire = true;
        count =0;
    }

    //method to change into radians
    public void setAngle(int angle) {
        this.angle = angle * 3.14 / 180;
    }
}

