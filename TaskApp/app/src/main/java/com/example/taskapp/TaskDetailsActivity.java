package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import android.widget.Toast;
import java.text.ParseException;




import java.util.Date;

public class TaskDetailsActivity extends AppCompatActivity
{
    private EditText titleEditText, descEditText, dueDateEditText;
    private Button deleteButton;
    private Task selectedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        initWidgets();
        checkForEditTask();
    }

    private void initWidgets()
    {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        dueDateEditText = findViewById(R.id.dueDateEditText);
        deleteButton = findViewById(R.id.deleteTaskButton);
    }

    private void checkForEditTask()
    {
        Intent previousIntent = getIntent();

        int passedTaskID = previousIntent.getIntExtra(Task.Task_EDIT_EXTRA, -1);
        selectedTask = Task.getTaskForID(passedTaskID);

        if (selectedTask != null) {
            titleEditText.setText(selectedTask.getTitle());
            descEditText.setText(selectedTask.getDescription());
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            dueDateEditText.setText(dateFormat.format(selectedTask.getDueDate())); // Set due date text
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveTask(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());
        String dueDateStr = String.valueOf(dueDateEditText.getText());

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date dueDate = dateFormat.parse(dueDateStr);
            if (selectedTask == null) {
                int id = Task.taskArrayList.size();
                Task newTask = new Task(id, title, desc, dueDate, null);
                Task.taskArrayList.add(newTask);
                sqLiteManager.addTaskToDatabase(newTask);
            } else {
                selectedTask.setTitle(title);
                selectedTask.setDescription(desc);
                selectedTask.setDueDate(dueDate);
                sqLiteManager.updateTaskInDB(selectedTask);
            }
            finish();
        } catch (ParseException e) {
            e.printStackTrace(); // Remove this line
            // Handle parsing exception
            Toast.makeText(this, "Invalid due date format", Toast.LENGTH_SHORT).show();
        }
    }



    public void deleteTask(View view)
    {
        selectedTask.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateTaskInDB(selectedTask);
        finish();
    }
}