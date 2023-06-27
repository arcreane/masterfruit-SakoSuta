package com.example.fruit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button guessNum = findViewById(R.id.guessNum);
        guessNum.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, com.example.fruit.GuessNum.class));
        });

    }
}