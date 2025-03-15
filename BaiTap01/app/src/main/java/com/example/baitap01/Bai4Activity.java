package com.example.baitap01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bai4Activity extends AppCompatActivity {

    EditText edtDaySo, edtChan, edtLe;
    Button btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_4);

        edtDaySo = findViewById(R.id.edtDaySo);
        edtChan = findViewById(R.id.edtChan);
        edtLe = findViewById(R.id.edtLe);
        btnRandom = findViewById(R.id.btnRandom);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> randomNumbers = generateRandomNumbers(10, 1, 100);

                List<Integer> evenNumbers = new ArrayList<>();
                List<Integer> oddNumbers = new ArrayList<>();

                for (int num : randomNumbers) {
                    if (num % 2 == 0) {
                        evenNumbers.add(num);
                    } else {
                        oddNumbers.add(num);
                    }
                }

                edtDaySo.setText(randomNumbers.toString());
                edtChan.setText(evenNumbers.toString());
                edtLe.setText(oddNumbers.toString());
            }
        });
    }

    private List<Integer> generateRandomNumbers(int count, int min, int max) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int num = random.nextInt(max - min + 1) + min;
            numbers.add(num);
        }

        return numbers;
    }
}
