package edu.up.cs371.ajimine19.cannonanimationhw3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * CannonMainActivity
 *
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 *
 * @author Andrew Nuxoll
 * @author devinajimine
 * The main activity creates the animation view in the GUI
 *
 */

public class CannonMainActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener
{
    /**
     * creates an AnimationCanvas containing a TestAnimator.
     */

    //instance variables
    private SeekBar angleSeeker;
    private Button fireButton;
    private cannonAnimator doAnimat;
    private TextView degrees;


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

        //fire button
        fireButton = (Button) findViewById(R.id.fireButton);
        fireButton.setOnClickListener(this);

        //set seek angle seekbar
        angleSeeker = (SeekBar) findViewById(R.id.angleSeek);
        angleSeeker.setOnSeekBarChangeListener(this);
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
}
