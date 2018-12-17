package com.example.user.kculture;



import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.display.R;
import com.google.android.gms.maps.GoogleMap;

import android.location.Location;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

    private GoogleMap mMap;

    //private GeoDataClient mGeoDataClient;
    //private PlaceDetectionClient mPlaceDetectionClient;

    public static final String PREFS_NAME="LanguageType";
    String name;
    double latitude = 0.0;
    double longitude = 0.0;
    public DBHelper mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDB = new DBHelper(this);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        TextView txView = ((TextView)findViewById(R.id.info));

        boolean lang = settings.getBoolean("English", false);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            long pkey = extras.getLong("pkey");
            Log.d("SQL", "Pkey : "+Long.toString(pkey));
            Cursor res = mDB.getData(pkey);
            if( res != null && res.moveToFirst() )
            {

                if(lang) {
                    name = res.getString(2);
                    getSupportActionBar().setTitle(res.getString(2));
                    txView.setText("\n"+res.getString(4));

                }
                else
                {
                    name = res.getString(1);
                    getSupportActionBar().setTitle(res.getString(1));
                    txView.setText("\n"+res.getString(3));

                }
                // korea 1,3

                // eng 2,4

                int imgID = extras.getInt("pic");
                Drawable img = getApplicationContext().getResources().getDrawable(imgID);
                txView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, img);


                // latitude
                this.latitude = res.getDouble(5);
                // longitude
                this.longitude = res.getDouble(6);

            }
            // title
            /*
            String value = extras.getString("position");
            ((TextView)findViewById(R.id.info)).setText(value);
            getSupportActionBar().setTitle(value);
            */

            // info

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Snackbar.make(view, "Map", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Toast.makeText(InfoActivity.this,
                        "Latitude: " + latitude + " Longitude : " + longitude, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);

                //String value = myData[position].name;
                //String value = mAdapter.getName(position);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);

                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }
}
