package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by devinajimine on 4/2/17.
 *
 * TODO Working on creating a custom made cannon to use in the animation
 *
 */

public class cannon
{
    //Instance variables
    private int X = 0;
    private int Y = 0;
    private int angle;

    Paint darkGray = new Paint();

    //Constuctor

    public cannon(int X, int Y)
    {
        this.X = X;
        this.Y = Y;

    }

    //draws a cannon
    public void onDraw(Canvas canvas) {

        darkGray.setColor(Color.DKGRAY);
        darkGray.setStyle(Paint.Style.FILL_AND_STROKE);
        darkGray.setStrokeWidth(5);
        Path cannon = new Path();
        cannon.moveTo(50+X,550+Y);
        cannon.lineTo(500+X,550+Y);
        cannon.lineTo(500+X,620+Y);
        cannon.lineTo(50+X,620+Y);
        canvas.drawPath(cannon,darkGray);//draw cannon


        Path triangle = new Path();
        triangle.moveTo(150,600);
        triangle.lineTo(200,750);
        triangle.lineTo(100,750);
        canvas.drawPath(triangle,darkGray);//draw stand for the cannon
    }

    //so we can see where we are shooting the cannon ball from
    public int getAngle()
    {
        return this.angle;
    }

    //return the x cor center point of the cannon
    public int getCannonX()
    {
        return 0;
    }
    //returns the y cor center point of the cannon
    public int getCannonY()
    {
        return 0;
    }


}

