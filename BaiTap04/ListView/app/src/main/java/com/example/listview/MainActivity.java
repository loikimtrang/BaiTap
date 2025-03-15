package com.example.listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] data = {"Android", "iOS", "Windows", "Linux", "macOS", "java", "c#", "c++", "Android", "iOS", "Windows", "Linux", "macOS", "java", "c#", "c++", "Android", "iOS", "Windows", "Linux", "macOS", "java", "c#", "c++"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = data[position];
            Toast.makeText(MainActivity.this, "Bạn đã chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
        });
    }
}
