package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by devinajimine on 4/2/17.
 * If I decide to make a custom cannonBall class
 */

public class cannonBalls
{
    private int x;
    private int y;
    private int radius;

    /** the circle's dimensions must be defined at construction */
    public cannonBalls( int x, int y, int radius, int color)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;

    }

    public void drawMe(Canvas canvas) {
        Paint black = new Paint();
        black.setColor(Color.BLACK);
        canvas.drawCircle(x, y, 20,black);  //main circle

    }



}
