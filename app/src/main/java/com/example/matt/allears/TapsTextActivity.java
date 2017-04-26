package com.example.matt.allears;

/**
 * Created by Josh Voskamp.
 */

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;

public class TapsTextActivity extends ActionBarActivity {
    private StopWatch tapTimer = new StopWatch();
    private StopWatch pauseTimer = new StopWatch();
    private ArrayList<Integer> times = new ArrayList<>();
    private Translator translator;
    private Button TapButton;
    private TextView DisplayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taps_text);//sets the taps to text activity to the screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//selects the default orientation of the activity
        Bitmap bm = BitmapFactory.decodeStream(getResources().openRawResource(R.raw.straight));//loads the logo
        //ImageView image = (ImageView) findViewById(R.id.imageView3);//finds the imageview that will hold the image
        //image.setImageBitmap(bm);//sets the logo
        InputStream morse = getResources().openRawResource(R.raw.morse);//loads the csv file
        translator = new Translator(CSVFile.readCSV(morse));//creates a translator using the alphabet supplied by the csv
        DisplayText = (TextView) findViewById(R.id.displayText);//locates the display text
        TapButton = (Button) findViewById(R.id.tap);//locates the tap button
        SetupTapButton();//attaches the buttons
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         // Adds the action bar in the top right; adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_taps_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Back button using the parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.credits) {//if you selected credits it creates a new credits activity
            Intent intent = new Intent(this, CreditsActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.help) {//if you selected help it creates a new help activity
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	//Attaches a OnTouchListener to the TapButton
    public void SetupTapButton() {
		//OnTouchListener
        TapButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {//If pressed
                    tapTimer.start();//start the tap timer
                    try {//used to stop errors caused by stopping the pause timer before it is started
                        pauseTimer.stop();//stops the pause timer
                        times.add((int) pauseTimer.getTime());//adds the time between the two taps
                        pauseTimer.reset();//resets the timer so it can be reused
                    } catch (IllegalStateException e) {
                        times.add(0);//adds a 0 pause time because the pause timer was never started
                    }
                    return false;//allows the button to keep recording presses
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {//if released
                    pauseTimer.start();//start the pause timer
                    tapTimer.stop();//stop the tap timer
                    times.add((int) tapTimer.getTime());//adds the time that the button was pressed for
                    tapTimer.reset();//resets the tap timer so that it can be reused
                }
                return false;//allows the button to keep recording presses
            }
        });
    }
	//displays the translation of the currently input sequence of taps
    public void translate(View view) {
        try {//prevent force closes caused by stopping an unstarted pause timer
            pauseTimer.stop();//stop the pause timer if it was running
            times.add((int) pauseTimer.getTime());//adds the pause time
            pauseTimer.reset();//resets the pause timer so that it can be reused
        } catch (IllegalStateException e) {
            //Do nothing
        }
        DisplayText.setText(translator.MorseCodeToText(translator.TimesToCode(times)));//computes and displays the translation
        times.clear();//clears the previous times to allow for another translation
    }
}
