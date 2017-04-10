package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by devinajimine on 4/9/17.
 */

public class cannonBalls
{
    private int x;
    private int y;
    private int radius;
    private Paint color;

    /** the circle's dimensions must be defined at construction */
    public cannonBalls(int x, int y, int radius, Paint color)
    {
        this.x = x;
        this.y = y;
        this.radius=radius;
        this.color = color;

    }

    public void drawMe(Canvas canvas) {

        canvas.drawCircle(x, y, radius, color);  //main circle

    }


}
