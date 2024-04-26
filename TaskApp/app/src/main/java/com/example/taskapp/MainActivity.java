package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView taskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setTaskAdapter();
        setOnClickListener();
    }

    private void initWidgets() {
        taskListView = findViewById(R.id.taskListView);
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateTaskListArray();
    }

    private void setTaskAdapter() {
        TaskAdapter taskAdapter = new TaskAdapter(getApplicationContext(), Task.nonDeletedTasks());
        taskListView.setAdapter(taskAdapter);
    }

    private void setOnClickListener() {
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Task selectedTask = (Task) taskListView.getItemAtPosition(position);
                Intent editTaskIntent = new Intent(getApplicationContext(), TaskDetailsActivity.class);
                editTaskIntent.putExtra(Task.Task_EDIT_EXTRA, selectedTask.getId());
                startActivity(editTaskIntent);
            }
        });
    }

    public void newTask(View view) {
        Intent newTaskIntent = new Intent(this, TaskDetailsActivity.class);
        startActivity(newTaskIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTaskAdapter();
    }
}
