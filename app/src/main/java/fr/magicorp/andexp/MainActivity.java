package fr.magicorp.andexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.andexp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // theme
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        // render activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class); // instantiate Intent with an new activity
        EditText editText = (EditText) findViewById(R.id.editText); // get text field
        String message = editText.getText().toString();// get field content
        intent.putExtra(EXTRA_MESSAGE, message); // send msg to activity
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onComposeAction(MenuItem mi) {
        Intent intent = new Intent(this, api.class); // instantiate Intent with an new activity
        startActivity(intent);
    }
}
