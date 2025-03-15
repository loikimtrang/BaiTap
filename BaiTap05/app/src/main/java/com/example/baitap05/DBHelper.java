package com.example.baitap05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "task_db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, task_name TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }

    public long addTask(String taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("task_name", taskName);

        long taskId = db.insert("tasks", null, values);
        db.close();
        return taskId;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT id, task_name FROM tasks", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                tasks.add(new Task(id, name));
            }
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return tasks;
    }


    public void deleteTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete("tasks", "id = ?", new String[]{String.valueOf(taskId)});
        } finally {
            db.close();
        }
    }

    public void updateTask(int taskId, String newTaskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("task_name", newTaskName);

        try {
            db.update("tasks", values, "id = ?", new String[]{String.valueOf(taskId)});
        } finally {
            db.close();
        }
    }
}
