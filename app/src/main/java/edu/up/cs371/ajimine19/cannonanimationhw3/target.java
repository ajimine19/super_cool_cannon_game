package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by devinajimine on 4/2/17.
 *
 *Summary: This target class draws the targets and extends customElement in order to get
 * check if the ball hits the target
 */

public class target extends customElement
{
    //instance variables
    private int x;
    private int y;
    private int radius;
    private int color;
    Random myRand = new Random();
    private boolean hit = false;

    //takes in the x,y,radius and if it is hit than highlight
    public target(String name, int color, int x, int y, int radius)
    {
            super(name, color);
            this.color = color;
            this.x = x;
            this.y = y;
            this.radius = radius;
    }

    public void drawMe(Canvas canvas) {
        //paints targets as random

        Paint daColor = new Paint();
        daColor.setColor(color);
        Paint red = new Paint();
        red.setColor(Color.RED);

        //if the target is hit the targets changes colors
        if(hit)
        {
            daColor.setColor(Color.rgb(myRand.nextInt(256), myRand.nextInt(256),
                    myRand.nextInt(256)));
        }
        canvas.drawCircle(x, y, radius,daColor );  //main circle
        drawHighlight(canvas);

        canvas.drawCircle(x,y,radius/2,red);
        drawHighlight(canvas);
    }

    /** for ease of calculation, just draw a box around the circle and see if the point is in that */

    public boolean containsPoint(int x, int y) {
        //Calculate the distance between this point and the center
        int xDist = Math.abs(x - this.x);
        int yDist = Math.abs(y - this.y);
        int dist = (int)Math.sqrt(xDist*xDist + yDist*yDist);  //Thanks, Pythagoras :)
        return (dist < this.radius + TAP_MARGIN);
    }//containsPoint

    public int getSize() {
        return (int)(Math.PI * this.radius * this.radius);
    }

    //if the target is hit highlight target
    @Override
    public void drawHighlight(Canvas canvas) {
        canvas.drawCircle(x, y, radius, highlightPaint);
        canvas.drawCircle(x, y, radius, outlinePaint);  //keep outline so it stands out
    }

    //if the target is hit
    public void setHit()
    {
        hit = true;
    }

    public void moveIT(Canvas g)
    {
        this.y = this.y + 10;
        if((this.y+radius)==g.getHeight() || (this.y+radius) ==0)
        {
            this.y = - (this.y);
        }
    }
}
