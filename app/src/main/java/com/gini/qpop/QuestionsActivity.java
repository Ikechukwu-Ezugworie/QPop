package com.gini.qpop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gini.qpop.util.AppUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    private TextView questionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_questions);

        // Initialize TextView
        questionTextView = findViewById(R.id.question);
        try {
          JSONObject jsonObject = AppUtils.readJsonFile(this, "alternative.json");
          String question = jsonObject.optString(getLevel());
          Gson gson = new Gson();
          List<?> questions = gson.fromJson(question, List.class);
          System.out.println(questions);
          questionTextView.setText(question);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    //method to read from intent and get the buttonId
    private String getLevel() {
        return getIntent().getStringExtra("level");
    }

}