package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    Button nextQbtn, submitbtn, pauseResumebtn;
    TextView txtVQuest, varyingText;
    EditText answerInput;
    int num1, num2;
    int score = 0;
    int count = 0; //For Part E) to keep track of how many questions have been asked
    int randomOperation;
    String cur_operation;

    //For Part G)
    String operation_symbol;
    ArrayList<String> QandA = new ArrayList<String>();

    //Part H)
    boolean noMistake;
    boolean failed = false; //for flagging if we made a mistake or not

    //Part I)
    boolean takeChances;
    int cur_life = 3;

    //For Assignment 6 Q1 PtI)
    boolean timeTrial;
    CountDownTimer timer;
    long countTimer = 60000;

    //For Assignment 6 Q1 Pt II)
    long countTimertillFinished = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //TODO/Future work, currently using back button to go back to previous menu can cause unintended bugs
        //E.g. going back when you start a time trial, that time trial time is still ticking in the background

        //nextQbtn = findViewById(R.id.nextBtn);
        submitbtn = findViewById(R.id.submitBtn);
        answerInput = findViewById(R.id.answerInput);
        txtVQuest = findViewById(R.id.questTextView);
        varyingText = findViewById(R.id.varyingText); //This varyingText variable displays varying text like CurLife or the TimeTrial time counter

        //Assignment 6 Q1 Pt II)
        pauseResumebtn = findViewById(R.id.pauseResumeBtn);
        pauseResumebtn.setVisibility(View.GONE); //Set its visibility invisible by default unless we're in time trial

        //Part A, right after clicking addition
        //questionAddition();

        //For keeping track of which operation we're doing
        Intent intent_operation = getIntent();
        String operation = intent_operation.getStringExtra("operation");

        //For Part E), receiving the number of questions the user wants to do
        int numQuestions = intent_operation.getIntExtra("numQuestions", 0);

        //For Part H)
        noMistake = intent_operation.getBooleanExtra("noMistake", false);

        //For Part I)
        takeChances = intent_operation.getBooleanExtra("takeChances", false);
        if (takeChances == true){
            varyingText.setText("Current life = " + cur_life);
        }

        Intent intent_summary = new Intent(QuestionActivity.this, SummaryActivity.class);


        //This is so we know which one to call properly depending on which button we pressed in MainActivity
        if (operation.equals("addition")){
            questionAddition();
            cur_operation = "addition";
        }
        else if (operation.equals("subtraction")){
            questionSubtraction();
            cur_operation = "subtraction";
        }
        else if (operation.equals("multiplication")){
            questionMultiplication();
            cur_operation = "multiplication";
        }
        else if (operation.equals("division")){
            questionDivision();
            cur_operation = "division";
        }
        else{ //when operation equals mixed
            questionMixed();
            if (randomOperation == 0){ //We will define 0 = addition, 1 = subtraction, 2 = multiplication, 3 = division
                questionAddition();
                cur_operation = "addition";
            }
            else if (randomOperation == 1){
                questionSubtraction();
                cur_operation = "subtraction";
            }
            else if (randomOperation == 2){
                questionMultiplication();
                cur_operation = "multiplication";
            }
            else{ //randomOperation == 3
                questionDivision();
                cur_operation = "division";
            }
        }

        //For Assignment 6 Q1 PtI)
        //If we came from the time trial button click, then this variable is true
        //Used: https://developer.android.com/reference/android/os/CountDownTimer#java
        timeTrial = intent_operation.getBooleanExtra("timeTrial", false);
        if (timeTrial == true){
            //Assignment 6 Q1 Pt II)
            pauseResumebtn.setVisibility(View.VISIBLE);
            pauseResumebtn.setText("PAUSE");
            //Calling our user made Timer function
            timerStart(countTimer); //countTimer currently set at 60000 aka 60 seconds
        }

        //Assignment 6 Q1 Pt II)
        //Making the pause resume button only a single button
        pauseResumebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pauseResumebtn.getText().toString().equals("PAUSE")){ //Aka when we click on the button and it says "PAUSE"
                    pauseResumebtn.setText("RESUME");
                    timerPause();
                }
                else{ //pauseResumebtn.getText().toString().equals("RESUME")
                    pauseResumebtn.setText("PAUSE");
                    timerResume();
                }
            }
        });

        //Removed for Part E), no more next button, automatically goes to the next question itself
//        nextQbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (operation.equals("addition")){
//                    questionAddition();
//                    cur_operation = "addition";
//                }
//                else if (operation.equals("subtraction")){
//                    questionSubtraction();
//                    cur_operation = "subtraction";
//                }
//                else if (operation.equals("multiplication")){
//                    questionMultiplication();
//                    cur_operation = "multiplication";
//                }
//                else if (operation.equals("division")){ //for now its division as the last one
//                    questionDivision();
//                    cur_operation = "division";
//                }
//                else{
//                   questionMixed();
//                    //We will define 0 = addition, 1 = subtraction, 2 = multiplication, 3 = division
//                    if (randomOperation == 0){
//                        questionAddition();
//                        cur_operation = "addition";
//                    }
//                    else if (randomOperation == 1){
//                        questionSubtraction();
//                        cur_operation = "subtraction";
//                    }
//                    else if (randomOperation == 2){
//                        questionMultiplication();
//                        cur_operation = "multiplication";
//                    }
//                    else{ //randomOperation == 3
//                        questionDivision();
//                        cur_operation = "division";
//                    }
//                }
//            }
//        });

        //Part F)
        long tStart = System.currentTimeMillis(); //time start

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float actualAnswer;

                if (cur_operation.equals("addition")){
                    actualAnswer = num1+num2;
                    operation_symbol = "+";
                }
                else if (cur_operation.equals("subtraction")){
                    actualAnswer = num1-num2;
                    operation_symbol = "-";
                }
                else if (cur_operation.equals("multiplication")){
                    actualAnswer = num1*num2;
                    operation_symbol = "*";
                }
                else{ //This is for division
                    actualAnswer = (float)num1/num2;
                    operation_symbol = "/";
                }
                if (!(cur_operation.equals("division"))){ //For all operations other than division
                    int userAnswer = Integer.parseInt(answerInput.getText().toString());
                    if (userAnswer == actualAnswer) {
                        //txtVQuest.setText("You are right!"); //won't appear anymore
                        score++;
                        QandA.add("" + num1+operation_symbol+num2 + " = " + userAnswer + " : (Correct)");
                    }
                    else{
                        //txtVQuest.setText("You are wrong!"); //won't appear anymore
                        QandA.add("" + num1+operation_symbol+num2 + " = " + userAnswer + " : (Wrong)");
                        failed = true;  //Part H)
                        if (takeChances == true){ //Part I)
                            cur_life--;
                            varyingText.setText("Current life = " + cur_life);
                        }
                    }
                }
                else{ //When the operation selected is division
                    float userAnswer = Float.parseFloat(answerInput.getText().toString());
                    if (Math.abs(userAnswer-actualAnswer) < 0.01) { //floating point comparison of answers within the 2 decimal range
                        //txtVQuest.setText("You are right!");
                        score++;
                        QandA.add("" + num1+operation_symbol+num2 + " = " + userAnswer + " : (Correct)");
                    }
                    else{
                        //txtVQuest.setText("You are wrong!");
                        QandA.add("" + num1+operation_symbol+num2 + " = " + userAnswer + " : (Wrong)");
                        failed = true; //Part H)
                        if (takeChances == true){ //Part I)
                            cur_life--;
                            varyingText.setText("Current life = " + cur_life);
                        }
                    }
                }

                //For Part E)
                count++;

                answerInput.setText("");
                txtVQuest.setText("");
                if (count == numQuestions || (failed == true && noMistake == true || (cur_life == 0 && takeChances == true))){ //numQuestions being the userInput number they wanted for how many Questions
                    intent_summary.putExtra("score", score); //passing score over to SummaryActivity

                    //Part F)
                    long tEnd = System.currentTimeMillis(); //end time
                    long tDelta = tEnd - tStart;
                    double elapsedSeconds = tDelta / 1000.0;
                    intent_summary.putExtra("time", elapsedSeconds);

                    //Part G)
                    intent_summary.putStringArrayListExtra("QandA", QandA);
                    startActivity(intent_summary);
                }
                else{
                    //NextButton functionality moved here instead (if-elseif-else clauses)
                    if (operation.equals("addition")){
                        questionAddition();
                        cur_operation = "addition";
                    }
                    else if (operation.equals("subtraction")){
                        questionSubtraction();
                        cur_operation = "subtraction";
                    }
                    else if (operation.equals("multiplication")){
                        questionMultiplication();
                        cur_operation = "multiplication";
                    }
                    else if (operation.equals("division")){ //for now its division as the last one
                        questionDivision();
                        cur_operation = "division";
                    }
                    else{
                        questionMixed();
                        //We will define 0 = addition, 1 = subtraction, 2 = multiplication, 3 = division
                        if (randomOperation == 0){
                            questionAddition();
                            cur_operation = "addition";
                        }
                        else if (randomOperation == 1){
                            questionSubtraction();
                            cur_operation = "subtraction";
                        }
                        else if (randomOperation == 2){
                            questionMultiplication();
                            cur_operation = "multiplication";
                        }
                        else{ //randomOperation == 3
                            questionDivision();
                            cur_operation = "division";
                        }
                    }
                }
            }
        });
    }

    public void questionAddition(){
        num1 = (int)(Math.random()*20);
        num2 = (int)(Math.random()*20);
        txtVQuest.setText("What is " +num1+ "+" + num2 + "?");
    }

    public void questionSubtraction(){
        num1 = (int)(Math.random()*20);
        num2 = (int)(Math.random()*20);
        if (num2 > num1){ //Part B, want the first number to be always greater than (or equal to) the second number
            int temp = num1;
            num1 = num2;
            num2 = temp; //simply swapping the numbers
        }
        txtVQuest.setText("What is " +num1+ "-" + num2 + "?");
    }

    public void questionMultiplication(){
        num1 = (int)(Math.random()*20);
        num2 = (int)(Math.random()*20);
        txtVQuest.setText("What is " +num1+ "*" + num2 + "?");
    }

    public void questionDivision(){
        num1 = (int)(Math.random()*20);
        num2 = (int)(Math.random()*20);
        txtVQuest.setText("What is " +num1+ "/" + num2 + "?");
    }

    public void questionMixed(){
        randomOperation = (int)(Math.random()*4); //returns numbers from 0-3 which we will use to represent the different possible operations
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //For Assignment 6 Part I and II
    //https://stackoverflow.com/questions/8306374/android-how-to-pause-and-resume-a-count-down-timer
    public void timerStart(long timeLengthMilli) {
        timer = new CountDownTimer(timeLengthMilli, 1000) { //60 seconds going down by 1 second each
            public void onTick(long millisUntilFinished) {
                countTimertillFinished = millisUntilFinished; //This helps us keep track of the timer at every tick, so if we ever pause it, its saved into this variable
                varyingText.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() { // When the countdown is finished
                Intent intent_summary = new Intent(QuestionActivity.this, SummaryActivity.class);
                intent_summary.putExtra("score", score);
                intent_summary.putStringArrayListExtra("QandA", QandA);
                intent_summary.putExtra("time", (double) countTimer / 1000); //Only putting this here because we display elapsed time on Summary page
                startActivity(intent_summary);
            }
        }.start();
    }

    //Function to call when we pause our timer
    public void timerPause() {
        timer.cancel();
        Toast.makeText(this, "Game is Paused", Toast.LENGTH_SHORT).show();
    }

    //Function to call when we resume our timer
    private void timerResume() {
        timerStart(countTimertillFinished); //Using that saved variable that keeps track of current time and resuming our timer starting at that specific time left
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}