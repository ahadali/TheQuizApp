package com.example.android.thequizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button firstButton = findViewById(R.id.First_Section_Button);

        Button secondButton = findViewById(R.id.Second_Section_Button);

        Button thirdButton = findViewById(R.id.Third_Section_Button);


        /* Intent for going to the first section of the quiz  */
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent firstIntent = new Intent(MainActivity.this, FirstSectionActivity.class);
                startActivity(firstIntent);
            }
        });


        /* Intent for going to the second section of the quiz  */
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent = new Intent(MainActivity.this, SecondSectionActivity.class);
                startActivity(secondIntent);
            }
        });


        /* Intent for going to the third section of the quiz  */
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thirdIntent = new Intent(MainActivity.this, ThirdSectionActivity.class);
                startActivity(thirdIntent);
            }
        });


    }

}
