package com.example.baitap06;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap06.adapter.CategoryAdapter;
import com.example.baitap06.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel categoryViewModel;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        recyclerView = findViewById(R.id.rc_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(this, null);
        recyclerView.setAdapter(categoryAdapter);

        categoryViewModel.getCategoryList().observe(this, categories -> {
            if (categories != null) {
                categoryAdapter.updateCategories(categories);
            }
        });
        categoryViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        categoryViewModel.fetchCategories();
    }
}
