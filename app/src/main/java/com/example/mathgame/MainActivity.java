package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnSub, btnMul, btnDiv, btnMix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        btnSub = findViewById(R.id.buttonSub);
        btnDiv = findViewById(R.id.buttonDiv);
        btnMul = findViewById(R.id.buttonMul);
        btnMix = findViewById(R.id.buttonMix);

        //Intent intent_q = new Intent(MainActivity.this, QuestionActivity.class);

        //Part E
        Intent intent_options = new Intent(MainActivity.this, OptionsActivity.class);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_options.putExtra("operation", "addition"); //This is how we keep track of which operation we're doing
                startActivity(intent_options);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_options.putExtra("operation", "subtraction");
                startActivity(intent_options);
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_options.putExtra("operation", "multiplication");
                startActivity(intent_options);
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_options.putExtra("operation", "division");
                startActivity(intent_options);
            }
        });

        btnMix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_options.putExtra("operation", "mixed");
                startActivity(intent_options);
            }
        });
    }
}