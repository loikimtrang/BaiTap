package com.example.baitap05;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskClickListener {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> tasks;
    private DBHelper dbHelper;
    private FloatingActionButton fabAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        tasks = dbHelper.getAllTasks();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(taskAdapter);

        fabAddTask = findViewById(R.id.fabAddTask);
        fabAddTask.setOnClickListener(v -> showAddTaskDialog());
    }

    @Override
    public void onDeleteTask(int position) {
        if (position < 0 || position >= tasks.size()) return;

        dbHelper.deleteTask(tasks.get(position).getId());

        tasks.remove(position);
        taskAdapter.notifyItemRemoved(position);
        taskAdapter.notifyItemRangeChanged(position, tasks.size());

        Toast.makeText(this, "Task deleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditTask(int position) {
        EditText editText = new EditText(this);
        editText.setText(tasks.get(position).getName());
        editText.setSelection(editText.getText().length());

        new AlertDialog.Builder(this)
                .setTitle("Edit Task")
                .setView(editText)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newTaskName = editText.getText().toString().trim();
                    if (!newTaskName.isEmpty()) {
                        dbHelper.updateTask(tasks.get(position).getId(), newTaskName);
                        tasks.get(position).setName(newTaskName);
                        taskAdapter.notifyItemChanged(position);
                        Toast.makeText(MainActivity.this, "Task updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a task name", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    private void showAddTaskDialog() {
        EditText editText = new EditText(this);
        editText.setHint("Enter task name");

        new AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setView(editText)
                .setPositiveButton("Save", (dialog, which) -> {
                    String taskName = editText.getText().toString().trim();
                    if (!taskName.isEmpty()) {
                        long taskId = dbHelper.addTask(taskName);
                        if (taskId != -1) {
                            tasks.add(new Task((int) taskId, taskName));
                            taskAdapter.notifyItemInserted(tasks.size() - 1);
                            Toast.makeText(MainActivity.this, "Task added successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error adding task!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter a task name", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
