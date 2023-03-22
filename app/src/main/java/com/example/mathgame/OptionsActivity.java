package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends AppCompatActivity {
    Button btnMakeAWish, btnNoMistake, btnTakeChances, btnTimeTrial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        btnMakeAWish = findViewById(R.id.buttonMakeAWish);
        btnNoMistake = findViewById(R.id.buttonNoMistake);
        btnTakeChances = findViewById(R.id.buttonTakeChances);
        btnTimeTrial = findViewById(R.id.buttonTimeTrial);

        //Passing it along
        Intent intent_op = getIntent();
        String operation = intent_op.getStringExtra("operation");

        Intent intent_num = new Intent(OptionsActivity.this, NumQuestionsActivity.class);

        //Part H)
        Intent intent_q = new Intent(OptionsActivity.this, QuestionActivity.class);

        btnMakeAWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_num.putExtra("operation", operation);
                startActivity(intent_num);
            }
        });

        //H)
        btnNoMistake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_q.putExtra("operation", operation);
                intent_q.putExtra("numQuestions", 999); //Just putting numQuestions to 999, as unlikely to reach this counter in the comparison count = numQuestions
                intent_q.putExtra("noMistake", true); //For setting the noMistake boolean variable to true in QuestionActivity
                startActivity(intent_q);
            }
        });

        //I)
        btnTakeChances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_q.putExtra("operation", operation);
                intent_q.putExtra("numQuestions", 999); //Just putting numQuestions to 999, as unlikely to reach this counter in the comparison count = numQuestions
                intent_q.putExtra("takeChances", true);
                startActivity(intent_q);
            }
        });

        btnTimeTrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}