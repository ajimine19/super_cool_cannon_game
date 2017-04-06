package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.graphics.*;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.MotionEvent;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Random;

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
    private int move = 0;
    private boolean goBackwards = false; // whether clock is ticking backwards
    private boolean fire = false;
    private boolean hitMeSelf = false;
    private boolean reset = false;
    private int posX = 0;
    private int posY = 0;
    private int velocity = 150;
    private double angle = 45*3.14/180;
    private double GRAVITY = 9.8;
    private ArrayList<target> tars;
    private ArrayList<target> removedTargets;
    private target targetTest, targetTest2,targetTest3, targetTest4, targetTest5, targetTest6,
            targetTest7, targetTest8 ;
    private target extargetTest, extargetTest2,extargetTest3, extargetTest4, extargetTest5, extargetTest6,
            extargetTest7, extargetTest8 ;
    Random myRand = new Random();
    Random randomExp = new Random();
    Random randomExp2 = new Random();
    Random randomExp3 = new Random();
    private Paint highlightPaint = new Paint();
    private Paint greyhighlight = new Paint();





    //constructor to call on intialized methods
    public cannonAnimator()
    {
        tars = new ArrayList<>();
        removedTargets = new ArrayList<>();
        intialTars();
        intialPaints();
    }

    private void intialPaints() {

        highlightPaint.setColor(Color.YELLOW);
        highlightPaint.setStyle(Paint.Style.STROKE);
        highlightPaint.setStrokeWidth(5); // nice wide, visible line
        highlightPaint.setShadowLayer(5, 1, 1, Color.BLACK);

        greyhighlight.setColor(Color.LTGRAY);
        greyhighlight.setStyle(Paint.Style.STROKE);
        greyhighlight.setStrokeWidth(5); // nice wide, visible line
        greyhighlight.setShadowLayer(5, 1, 1, Color.BLACK);

    }

    //intialize the targets
    public void intialTars()
    {
        //TODO implement a reset button
        if(reset)
        {
            extargetTest = new target("", Color.MAGENTA, 1700, -1800, 50);
            extargetTest2 = new target("", Color.MAGENTA, 800, -1000, 75);
            extargetTest3 = new target("", Color.MAGENTA, 1200, 300, 100);
            extargetTest4 = new target("", Color.MAGENTA, 1700, -900, 90);
            extargetTest5 = new target("", Color.MAGENTA, 100, -500, 50);
            extargetTest6 = new target("", Color.MAGENTA, 1400, 0, 75);
            extargetTest7 = new target("", Color.MAGENTA, 700, -10, 100);
            extargetTest8 = new target("", Color.MAGENTA, 1300, -1600, 150);


            tars.add(extargetTest);
            tars.add(extargetTest2);
            tars.add(extargetTest3);
            tars.add(extargetTest4);
            tars.add(extargetTest5);
            tars.add(extargetTest6);
            tars.add(extargetTest7);
            tars.add(extargetTest8);
        }
        else
        {
            targetTest = new target("", Color.WHITE, 1700, -1800, 100);
            targetTest2 = new target("", Color.WHITE, 800, -1000, 75);
            targetTest3 = new target("", Color.WHITE, 1200, 300, 100);
            targetTest4 = new target("", Color.WHITE, 1700, -900, 90);
            targetTest5 = new target("", Color.WHITE, 300, -500, 50);
            targetTest6 = new target("", Color.WHITE, 1400, 0, 75);
            targetTest7 = new target("", Color.WHITE, 700, -10, 100);
            targetTest8 = new target("", Color.WHITE, 1300, -1600, 150);
        }

        //stores it inside the arraylist
        tars.add(targetTest);
        tars.add(targetTest2);
        tars.add(targetTest3);
        tars.add(targetTest4);
        tars.add(targetTest5);
        tars.add(targetTest6);
        tars.add(targetTest7);
        tars.add(targetTest8);

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
        return Color.rgb(168,255,232);
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
        move++;
        for(target t : tars)
        {
            t.drawMe(g);

            t.moveBitch(g);

        }

        //set Colors
        Paint yellow = new Paint();
        Paint red = new Paint();
        Paint magenta = new Paint();
        yellow.setColor(Color.YELLOW);
        red.setColor(Color.RED);
        magenta.setColor(Color.MAGENTA);

        //checks to see if the canon is hit
        target destroyed = new target("",Color.YELLOW,115,1400,70);
        destroyed.drawMe(g);

        if(fire)
        {
            //calculates for gravity and sets the positions
            //Log.i("posX", posX+"");
            //Log.i("posY", posY+"");
            posX = (int)(velocity*Math.cos(angle)*count)+125;
            posY = (int)(-((velocity*Math.sin(angle)*count) - (.5*GRAVITY*count*count)))+1125;

            // Draw the ball in the correct position.
            //TODO Paint
            Paint redPaint = new Paint();
            redPaint.setColor(Color.rgb(myRand.nextInt(256), myRand.nextInt(256),
                    myRand.nextInt(256)));


            g.drawCircle(posX , posY, 60, redPaint);
            //Log.i("angle value:", angle + "");

            //iterate count to add multiple ticks
            count++;

            //Checks to see if the balls hits the the target
            //if the target is hit the setHit method will return a true so the target change color
        }

        //TODO Paint
        Paint colorB = new Paint();
        colorB.setColor(Color.BLACK);

        //rotates the canvas
        g.save();
        g.rotate((float)(-(angle*180/3.14)+90),100,1150);
        g.drawRect(0,1000,200,1250, colorB);
        g.restore();

        //TODO Paint
        Paint baseColor = new Paint();
        baseColor.setColor(Color.RED);

        //draws base
        Path triangle = new Path();
        triangle.moveTo(0,1000);
        triangle.lineTo(300,1350);
        triangle.lineTo(0,1350);
        g.drawPath(triangle,baseColor);

        //checks to see if the ball goes out of bounds
        if(posX>=g.getWidth()-100 || posY >= g.getHeight() )
        {
            fire = false;
        }

        //if the canon hits itself with a cannon ball an expxlosing will occer
        if(destroyed.containsPoint(posX,posY))
        {
            for (int i = 1; i<30; i++)
            {
                g.drawCircle( randomExp.nextInt(500), 900+randomExp.nextInt(200), randomExp.nextInt(50), yellow);
                g.drawCircle( randomExp2.nextInt(500), 1000+randomExp2.nextInt(200), randomExp2.nextInt(80), red);
                g.drawCircle( randomExp3.nextInt(400), 1100+randomExp3.nextInt(200), randomExp3.nextInt(30), magenta);
            }
        }

        //goes through all targets
        //checks to see if the target contains the point
        //if it does the target will explode and disapear
        for (target z : tars) {
            if (z.containsPoint(posX, posY))
            {
                z.setHit();
                removedTargets.add(z);
                for (int i = 1; i<30; i++)
                {
                    g.drawCircle( posX+randomExp.nextInt(500)-100, posY-randomExp.nextInt(200), randomExp.nextInt(50), yellow);
                    g.drawCircle( posX+randomExp2.nextInt(500)-100, posY+randomExp2.nextInt(200), randomExp2.nextInt(80), red);
                    g.drawCircle( posX+randomExp3.nextInt(400)-100, posY+randomExp3.nextInt(200), randomExp3.nextInt(30), magenta);
                }
                fire = false;
            }
        }
        //removes the targets
        tars.removeAll(removedTargets);
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
    //its is important to set count back to 0 so it can be implemented again
    public void fire()
    {
        fire = true;
        count = 0;
    }

    //TODO implement a reset button

    public void reset()
    {
        reset = true;
    }


    //method to change into radians
    public void setAngle(int angle) {
        this.angle = angle * 3.14 / 180;
    }

    public void setGravity(int gravity)
    {
        this.GRAVITY = gravity;
    }
}

