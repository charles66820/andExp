package fr.magicorp.andexp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

public class api extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // theme
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity);
    }

    //http://api.dev.magicorp.net:8080/batrenis/v1/servers
}
