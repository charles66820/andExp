package fr.magicorp.andexp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();// Get the Intent
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);// get EXTRA_MESSAGE send in MainActivity

        TextView textView = findViewById(R.id.textViewTitle); // get textViewTitle
        textView.setText(message); // change textViewTitle text
    }
}
