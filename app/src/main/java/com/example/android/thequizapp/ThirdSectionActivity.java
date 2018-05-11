package com.example.android.thequizapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ThirdSectionActivity extends AppCompatActivity {
    /* Total time needed for Countdown */
    private static final long START_TIME_IN_MILLIS = 120000;
    /* Total score */
    public final int totalScore = 4;
    /* Correct answer 1 */
    private final int Q1_ANSWER = R.id.correct_Answer1;
    /* Correct answer 2 */
    private final int Q2_ANSWER = R.id.correct_Answer2;
    /* Correct answer 3 */
    private final String Q3_ANSWER = "2.0";
    /* TextView for Countdown */
    private TextView mTextViewCountDown;
    /* Start and Submit Button */
    private Button mButtonStartOrSubmit;
    /* Variable for countdown timer */
    private CountDownTimer mCountDownTimer;
    /* boolean for running countdown timer */
    private boolean mTimerRunning;
    /* Left time in countdown */
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    /* Color State for Countdown Timer */
    private ColorStateList textColorDefaultCd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_section);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartOrSubmit = findViewById(R.id.button_start_submit);

        final RadioGroup rg1 = findViewById(R.id.radio_group_one);

        final RadioGroup rg2 = findViewById(R.id.radio_group_two);

        final CheckBox c1 = findViewById(R.id.first_checkbox), c2 = findViewById(R.id.second_checkbox), c3 = findViewById(R.id.third_checkbox), c4 = findViewById(R.id.fourth_checkbox);

        /*Disabling Radio Buttons before start of timer */
        for (int i = 0; i < rg1.getChildCount(); i++) {
            rg1.getChildAt(i).setEnabled(false);
        }

        for (int i = 0; i < rg2.getChildCount(); i++) {
            rg2.getChildAt(i).setEnabled(false);
        }

        /*Disabling Check Boxes before start of timer */
        c1.setEnabled(false);
        c2.setEnabled(false);
        c3.setEnabled(false);
        c4.setEnabled(false);

        textColorDefaultCd = mTextViewCountDown.getTextColors();

        /* Performing onClickListener Event on Start Button */
        mButtonStartOrSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimerRunning) {
                    submit();
                } else {
                    startTimer();
                    /*Enabling Radio Buttons after start of timer */
                    for (int i = 0; i < rg1.getChildCount(); i++) {
                        rg1.getChildAt(i).setEnabled(true);
                    }

                    for (int i = 0; i < rg2.getChildCount(); i++) {
                        rg2.getChildAt(i).setEnabled(true);
                    }
                    /*Enabling Check Boxes after start of timer */
                    c1.setEnabled(true);
                    c2.setEnabled(true);
                    c3.setEnabled(true);
                    c4.setEnabled(true);
                }

            }
        });
    }

    private void startTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateScore();
                finish();

            }
        }.start();

        mTimerRunning = true;
        mButtonStartOrSubmit.setText("submit");
    }


    private void submit() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        /* Alert Message created before submitting the quiz */
        AlertDialog.Builder a_builder = new AlertDialog.Builder(ThirdSectionActivity.this);
        a_builder.setMessage("Submit the quiz?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateScore();
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        startTimer();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert !!!");
        alert.show();
    }


    /* Alert Message created when back button is pressed */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mTimerRunning) {
            submit();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /* Updating and displaying countdown text in proper format */
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);

        if (mTimeLeftInMillis < 60000) {
            mTextViewCountDown.setTextColor(Color.RED);
        } else {
            mTextViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    public void updateScore() {

        int numberOfQuestionsCorrect = 0;

        if (checkQuestion1()) {
            numberOfQuestionsCorrect++;
        }

        if (checkQuestion2()) {
            numberOfQuestionsCorrect++;
        }

        if (checkQuestion3()) {
            numberOfQuestionsCorrect++;
        }

        if (checkQuestion4()) {
            numberOfQuestionsCorrect++;
        }

        Context context = getApplicationContext();
        CharSequence text = "You got " + numberOfQuestionsCorrect + " out of " + totalScore;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    private boolean checkQuestion1() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group_one);

        if (rg.getCheckedRadioButtonId() == Q1_ANSWER) {
            return true;
        }

        return false;
    }

    private boolean checkQuestion2() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group_two);

        if (rg.getCheckedRadioButtonId() == Q2_ANSWER) {
            return true;
        }

        return false;
    }


    private boolean checkQuestion3() {
        EditText et = (EditText) findViewById(R.id.correct_Answer3);

        return et.getText().toString().equalsIgnoreCase(Q3_ANSWER);
    }


    private boolean checkQuestion4() {
        CheckBox c1 = (CheckBox) findViewById(R.id.first_checkbox);
        CheckBox c2 = (CheckBox) findViewById(R.id.second_checkbox);
        CheckBox c3 = (CheckBox) findViewById(R.id.third_checkbox);
        CheckBox c4 = (CheckBox) findViewById(R.id.fourth_checkbox);

        if (c1.isChecked() && c2.isChecked() && !c3.isChecked() && !c4.isChecked()) {
            return true;
        }

        return false;
    }

}