package edu.orangecoastcollege.cs273.nhoang53.ocmusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventTitleTextView;
    private TextView eventDetailTextView;

    private ImageView eventImageView;
    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Connect View and Controller
        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDetailTextView = (TextView) findViewById(R.id.eventDetailsTextView);
        eventImageView = (ImageView) findViewById(R.id.eventImageView);

        // Get data from Intent
        Intent detailsIntent = getIntent();
        String title = detailsIntent.getStringExtra("Title");
        String detail = detailsIntent.getStringExtra("Details");
        String imageFileName = title.replace(" ", "") + ".jpeg";

        // Load the image from the assets folder
        AssetManager am = context.getAssets();
        /// Try to load the image file
        try{
            InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(image);
        } catch (IOException ex){
            Log.e("OC Music events", "Cannot load image:" + imageFileName + ex);
        }

        // Load title and detail
        eventTitleTextView.setText(title);
        eventDetailTextView.setText(detail);


    }

}
