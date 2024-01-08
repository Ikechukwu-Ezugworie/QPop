package com.gini.qpop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button1, button2, button3, button4, button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add functionalities to all buttons to open the QuestionsActivity
        // Initialize buttons
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
// Set onClickListeners for each button
        button1.setOnClickListener(view -> launchQuestionsActivity("level1"));

        button2.setOnClickListener(view -> launchQuestionsActivity("level2"));

        button3.setOnClickListener(view -> launchQuestionsActivity("level3"));

        button4.setOnClickListener(view -> launchQuestionsActivity("level4"));

        button5.setOnClickListener(view -> launchQuestionsActivity("level5"));
    }



    // Method to launch QuestionsActivity with a unique parameter
    private void launchQuestionsActivity(String level) {
        Intent intent = new Intent(this, SampleActivity.class);
        intent.putExtra("level", level);
        startActivity(intent);
    }
}