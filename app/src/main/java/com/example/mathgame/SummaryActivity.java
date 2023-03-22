package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {

    TextView txtSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        txtSummary = findViewById(R.id.txtSummary);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        //Part F)
        double elapsedSeconds = intent.getDoubleExtra("time", 0);

        //Part G)
        ArrayList<String> QandA = intent.getStringArrayListExtra("QandA");
        String QandASummary = "";
        for(String QandAItem : QandA){
            QandASummary += QandAItem + "\n";
        }
        txtSummary.setText("!!!GAME OVER!!!\nTime Taken: " + elapsedSeconds + " Seconds\n\nYourScore: " + score + "\n\n" + QandASummary);
    }
}