package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
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
 * Part A Notes: April 4th
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object. Overall I completed the requirments for HW3 part A,
 * and I have code that i am working on for part B as well.
 *
 * Part B Notes: April 6th
 * My cannon game should be fully functional with the following capabilities:
 * -The cannon ball should stop when it hits the ground (where it resets so you can fire again)[3]
 * -Animate the cannon being destroyed if it shoots itself (firing straight up
 into the air will cause the cannon to explode with a cloud of red/yellow/orange)[7]
 * -Have the targets create an animated explosion (or similar effect) when the
 cannonball hits them (If the target is hit the target will explode and disappear) [5]
 * -Modify the targets so that they move around on the screen until they are hit by
 a cannonball.[8]
 * -Allow the user to change the gravity and/or the wind speed. [7]
 *
 * TODO figure out reset button functionality
 * -Reset button capabilities
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
    private MediaPlayer cannonSound;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Creates an animation canvas and place it in the main layout
        doAnimat = new cannonAnimator();
        AnimationCanvas myCanvas = new AnimationCanvas(this, doAnimat);
        LinearLayout mainLayout = (LinearLayout) this
                .findViewById(R.id.animation_screen);
        mainLayout.addView(myCanvas);

        degrees = (TextView)findViewById(R.id.angleText);
        gravity = (TextView)findViewById(R.id.gravityText);

        //fire button
        fireButton = (Button) findViewById(R.id.fireButton);
        fireButton.setOnClickListener(this);

        //TODO implement a reset button
        //reset Button
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        //set seek angle seekbar
        angleSeeker = (SeekBar) findViewById(R.id.angleSeek);
        angleSeeker.setOnSeekBarChangeListener(this);

        //velocty seek bar
        gravitySeek = (SeekBar) findViewById(R.id.gravitySeekBar);
        gravitySeek.setOnSeekBarChangeListener(this);

        //audio button listener and instantiation
        cannonSound = MediaPlayer.create(this,R.raw.cannon);



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
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.angleSeek) {
            doAnimat.setAngle(seekBar.getProgress());
            degrees.setText("" + seekBar.getProgress());
        }
        else if (seekBar.getId() == R.id.gravitySeekBar)
        {
            doAnimat.setGravity(seekBar.getProgress());
            gravity.setText("" + seekBar.getProgress());
        }
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    //When the button is clicked
    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.fireButton:
                cannonSound.start();
                doAnimat.fire();
                break;
            /*
            *http://stackoverflow.com/questions/13813626/android-reset-button-permanently-disabled-another-button
            * How to create a reset button to end the activity and start again
            *
             */
            case R.id.resetButton:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
