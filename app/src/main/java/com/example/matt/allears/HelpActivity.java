package com.example.matt.allears;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class HelpActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);//Sets the help activty to the screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//Selects the default orientation of the Activity
        Bitmap bm = BitmapFactory.decodeStream(getResources().openRawResource(R.raw.morsecode));//loads the logo
        ImageView image = (ImageView) findViewById(R.id.imageView4);//finds the image view that is supposed to hold the logo
        image.setImageBitmap(bm);//sets the logo
    }
}