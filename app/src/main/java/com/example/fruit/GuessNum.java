package com.example.fruit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessNum extends AppCompatActivity {
    private enum Difficulty {
        EASY(1, 10),
        MEDIUM(1, 50),
        HARD(1, 100);
        private final int min;
        private final int max;
        Difficulty(int min, int max) {
            this.min = min;
            this.max = max;
        }
        public int getMin() {
            return min;
        }
        public int getMax() {
            return max;
        }
    }

    private Difficulty difficulty;
    private EditText numberEditText;
    private Button buttonValidate;
    private ProgressBar progressBar;
    private TextView attempt;
    private TextView result;
    private TextView score;
    private RadioGroup radioDifficulty;
    private Button buttonStart;
    private CheckBox checkBoxDifficulty;
    private List<Integer> attempts;
    private int scores;
    private int targetNumber;
    private int remainingAttempts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_num);
        numberEditText = findViewById(R.id.numberEditText);
        buttonValidate = findViewById(R.id.buttonValidate);
        progressBar = findViewById(R.id.progressBar);
        attempt = findViewById(R.id.attempts);
        result = findViewById(R.id.result);
        score = findViewById(R.id.score);
        radioDifficulty = findViewById(R.id.radioDifficulty);
        buttonStart = findViewById(R.id.buttonStart);
        checkBoxDifficulty = findViewById(R.id.checkBoxDifficulty);
        attempts = new ArrayList<>();
        scores = 0;
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    private void startGame() {
        radioDifficulty.setVisibility(View.GONE);
        buttonStart.setVisibility(View.GONE);
        numberEditText.setVisibility(View.VISIBLE);
        buttonValidate.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        attempt.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        checkBoxDifficulty.setVisibility(View.VISIBLE);
        difficulty = DifficultySelected();
        targetNumber = RandomNumber(difficulty);
        remainingAttempts = 10;
        attempts.clear();
        scores = 0;
        progressBar.setMax(10);
        progressBar.setProgress(0);
        attempt.setText("Attempts: 0/10");
        score.setText("Score: 0");
        result.setText("");
        numberEditText.setText("");
        numberEditText.requestFocus();
    }

    private void validate() {
        String input = numberEditText.getText().toString();
        if (!input.isEmpty()) {
            int guessedNumber = Integer.parseInt(input);
            attempts.add(guessedNumber);

            if (guessedNumber == targetNumber) {
                scores += remainingAttempts;
                result.setText("You Win!");
                progressBar.setProgress(10);
            } else {
                if (guessedNumber < targetNumber) {
                    result.setText("More Bigger");
                } else {
                    result.setText("More Smaller");
                }

                remainingAttempts--;
                progressBar.setProgress(10 - remainingAttempts);
                attempt.setText("Attempts: " + (10 - remainingAttempts) + "/10");

                if (remainingAttempts == 0) {
                    result.append("\n\nGame over !");

                    radioDifficulty.setVisibility(View.VISIBLE);
                    buttonStart.setVisibility(View.VISIBLE);
                    numberEditText.setVisibility(View.GONE);
                    buttonValidate.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    attempt.setVisibility(View.GONE);
                    result.setVisibility(View.VISIBLE);
                    score.setVisibility(View.GONE);
                    checkBoxDifficulty.setVisibility(View.GONE);
                }
            }

            numberEditText.setText("");
            numberEditText.requestFocus();

            ScoreUpdate();
            updateDifficulty();
        }
    }

    private Difficulty DifficultySelected() {
        int selectedId = radioDifficulty.getCheckedRadioButtonId();
        if (selectedId == R.id.Easy) {
            return Difficulty.EASY;
        } else if (selectedId == R.id.Medium) {
            return Difficulty.MEDIUM;
        } else {
            return Difficulty.HARD;
        }
    }

    private int RandomNumber(Difficulty difficulty) {
        Random random = new Random();
        return random.nextInt(difficulty.getMax() - difficulty.getMin() + 1) + difficulty.getMin();
    }

    private void ScoreUpdate() {
        score.setText("Score: " + scores);
    }

    private void updateDifficulty() {
        if (checkBoxDifficulty.isChecked()) {
            radioDifficulty.setVisibility(View.VISIBLE);
            buttonStart.setVisibility(View.VISIBLE);
            numberEditText.setVisibility(View.GONE);
            buttonValidate.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            attempt.setVisibility(View.GONE);
            result.setVisibility(View.GONE);
            score.setVisibility(View.GONE);
            checkBoxDifficulty.setVisibility(View.GONE);
        }
    }
}
