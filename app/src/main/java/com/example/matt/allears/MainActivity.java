package com.example.matt.allears;

/**
 * Created by Matt.
 */

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//sets the main activity to the screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//selects the default orientation of the activity
        Bitmap bm = BitmapFactory.decodeStream(getResources().openRawResource(R.raw.straight));//loads the logo
        //ImageView image = (ImageView) findViewById(R.id.imageView);//finds the imageview that will hold the image
        //image.setImageBitmap(bm);//sets the logo
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Adds the action bar in the top right; adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
	//When called it creates a new text to morse activity
    public void goToTextMorse(View view){
        Intent intent = new Intent(this, TextMorseActivity.class);
        startActivity(intent);
    }
	//When called it creates a new taps to text activity
    public void goToTapsText(View view){
        Intent intent = new Intent(this, TapsTextActivity.class);
        startActivity(intent);
    }
}