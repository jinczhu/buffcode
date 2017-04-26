package com.example.matt.allears;

/**
 * Created by Josh Voskamp.
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;


public class TextMorseActivity extends ActionBarActivity {
    private EditText textToTranslate;
    private Vibrator vibrator;
    private Translator translator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_morse);//sets the text to morse activity to the screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//selects the default orientation of the activity
        Bitmap bm = BitmapFactory.decodeStream(getResources().openRawResource(R.raw.straight));//loads the logo
        //ImageView image = (ImageView) findViewById(R.id.imageView2);//finds the imageview that will hold the image
        //image.setImageBitmap(bm);//sets the logo
        InputStream morse = getResources().openRawResource(R.raw.morse);//loads the csv file
        translator = new Translator(CSVFile.readCSV(morse));//creates a translator using the alphabet supplied by the csv
        textToTranslate = (EditText) findViewById(R.id.editText);//locates the display text
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//creates the vibratior
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_morse, menu);
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

    public void translate(View view) {
        vibrator.cancel();//Cancels any previous vibrations
        long[] pattern = translator.CodeToTime(translator.TextToMorseCode(textToTranslate.getText().toString()));//generates the vibration pattern
        vibrator.vibrate(pattern, -1);//Starts the vibration according to the given pattern. Does not repeat.
    }

    @Override
    public void onStop(){
        vibrator.cancel();//Cancels the vibration
        super.onStop();//Executes the regular onStop code
    }
}
