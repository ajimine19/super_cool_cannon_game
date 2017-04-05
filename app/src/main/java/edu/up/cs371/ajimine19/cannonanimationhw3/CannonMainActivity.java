package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * CannonMainActivity
 *
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object. Overall I completed the requirments for HW3 part A,
 * and I have code that i am working on for part B as well.
 *
 * I do have TODO's for part B
 *
 * @author Andrew Nuxoll
 * @author devinajimine
 * The main activity creates the animation view in the GUI
 *
 */

public class CannonMainActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener
{
    //instance variables
    private TextView degrees;
    private TextView gravity;

    private SeekBar angleSeeker;
    private SeekBar gravitySeek;

    private Button fireButton;
    private Button resetButton;

    private cannonAnimator doAnimat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates an animation canvas and place it in the main layout
        doAnimat = new cannonAnimator();
        AnimationCanvas myCanvas = new AnimationCanvas(this, doAnimat);
        LinearLayout mainLayout = (LinearLayout) this
                .findViewById(R.id.topLevelLayout);
        mainLayout.addView(myCanvas);

        degrees = (TextView)findViewById(R.id.angleText);
        gravity = (TextView)findViewById(R.id.gravityText);

        //fire button
        fireButton = (Button) findViewById(R.id.fireButton);
        fireButton.setOnClickListener(this);

        //TODO implement a reset button
        //reset Button
        //resetButton = (Button) findViewById(R.id.resetButton);
        //fireButton.setOnClickListener(new ResetButtonListener());

        //set seek angle seekbar
        angleSeeker = (SeekBar) findViewById(R.id.angleSeek);
        angleSeeker.setOnSeekBarChangeListener(this);

        //velocty seek bar
        //velocitySeek = (SeekBar) findViewById(R.id.gravitySeekBar);
        //velocitySeek.setOnSeekBarChangeListener(this);
    }

    /**
     * This is the default behavior (empty menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cannon_main, menu);
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b)
    {
        doAnimat.setAngle(seekBar.getProgress());
        degrees.setText(""+seekBar.getProgress());


        //TODO implement velocity seek bar
        //doAnimat.setGravity(seekBar.getProgress());
        //velocity.setText(""+seekBar.getProgress());
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    //When the fire button is clicked
    @Override
    public void onClick(View view) {

        doAnimat.fire();
    }

    //TODO implement a reset button
    /*
    private class ResetButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view)
        {

            doAnimat.reset();
        }
    }
    */

}
