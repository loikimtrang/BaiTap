package com.example.baitap01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
public class Bai5Activity extends AppCompatActivity {

    EditText edtChuoi, edtKq;
    Button btnDaoChuoi, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai_5);

        edtChuoi = findViewById(R.id.edtChuoi);
        edtKq = findViewById(R.id.edtKq);

        btnDaoChuoi = findViewById(R.id.btnDaoChuoi);
        btnClear = findViewById(R.id.btnClear);

        btnDaoChuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inpText;
                String inpTextReverse;

                inpText = edtChuoi.getText().toString();
                inpTextReverse = reverseAndUppercase(inpText);

                edtKq.setText(inpTextReverse);
                Toast toast = Toast.makeText(Bai5Activity.this, "Chuỗi đã đảo ngược: " + inpTextReverse, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 200);
                toast.show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtChuoi.setText(null);
                edtKq.setText(null);
            }
        });
    }

    private String reverseAndUppercase(String input) {
        String[] words = input.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        return reversed.toString().trim().toUpperCase();
    }
}