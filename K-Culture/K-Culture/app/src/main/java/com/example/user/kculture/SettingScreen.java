package com.example.user.kculture;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.user.display.R;

import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

public class SettingScreen extends AppCompatActivity {
    public static final String PREFS_NAME="LanguageType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean lang = settings.getBoolean("English", false);

        if(lang)
        {
            ((RadioButton)findViewById(R.id.radioButton)).setChecked(true);
            ((RadioButton)findViewById(R.id.radioButton2)).setChecked(false);
        }
        else
        {
            ((RadioButton)findViewById(R.id.radioButton)).setChecked(false);
            ((RadioButton)findViewById(R.id.radioButton2)).setChecked(true);
        }
    }
    /*
    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences.settings=getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putBoolean("English",mSilentMode);
        editor.commit();

            }
            */
    public void OnRadioButtonClicked(View view) {
        boolean checked=((RadioButton)view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton: //eng
                if(checked) {
                    Toast.makeText(getApplicationContext(),
                            ((RadioButton) view).getText(),
                            Toast.LENGTH_SHORT).show();
                    //save
                    SharedPreferences settings =getSharedPreferences(PREFS_NAME,0);
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putBoolean("English",true);
                    editor.commit();

                    String languageToLoad="en";
                    Locale locale=new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config=new Configuration();
                    config.locale=locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());
                    //this.setContentView(R.layout.content_main2);
                }
                break;
            case R.id.radioButton2: //kor
                if(checked) {
                    Toast.makeText(getApplicationContext(),
                            ((RadioButton) view).getText(),
                            Toast.LENGTH_SHORT).show();
                    SharedPreferences settings=getSharedPreferences(PREFS_NAME,0);
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putBoolean("English",false);
                    editor.commit();
                }
        }
    }

}

