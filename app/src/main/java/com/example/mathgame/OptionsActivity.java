package com.example.mathgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                //Redid this part to do a dialog instead, thus NumQuestionsActivity is obselete now
                AlertDialog.Builder builder = new AlertDialog.Builder(OptionsActivity.this);
                builder.setTitle("How many questions do you want?");

                // Set up the input
                final EditText input = new EditText(OptionsActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_VARIATION_NORMAL);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String numQ = input.getText().toString();
                        if (numQ.trim().length() == 0){ //Don't want them to enter an empty course or empty spaces
                            dialog.cancel();
                            Toast.makeText(OptionsActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                            return; //Get out of this "Add" part of the code
                        }
                        intent_q.putExtra("operation", operation);
                        intent_q.putExtra("numQuestions", Integer.parseInt(input.getText().toString()));
                        startActivity(intent_q);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
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

        //Assignment 6 Part Q1 Pt1)
        btnTimeTrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_q.putExtra("operation", operation);
                intent_q.putExtra("numQuestions", 999); //Just putting numQuestions to 999, as unlikely to reach this counter in the comparison count = numQuestions
                intent_q.putExtra("timeTrial", true);
                startActivity(intent_q);
            }
        });
    }
}