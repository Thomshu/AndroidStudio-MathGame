package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NumQuestionsActivity extends AppCompatActivity {

    Button submitBtn;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_questions);

        submitBtn = findViewById(R.id.btnNumQSubmit);
        input = findViewById(R.id.etNumQ);

        //Passing it along
        Intent intent_op = getIntent();
        String operation = intent_op.getStringExtra("operation");

        Intent intent_q = new Intent(NumQuestionsActivity.this, QuestionActivity.class);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_q.putExtra("numQuestions", Integer.parseInt(input.getText().toString()));
                intent_q.putExtra("operation", operation);
                startActivity(intent_q);
            }
        });
    }
}