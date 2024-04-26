package com.example.taskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task>
{
    public TaskAdapter(Context context, List<Task> tasks)
    {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_cell, parent, false);

        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDesc);
        TextView dueDate = convertView.findViewById(R.id.cellDueDate); // Added due date TextView

        title.setText(task.getTitle());
        desc.setText(task.getDescription());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dueDate.setText(dateFormat.format(task.getDueDate())); // Display due date

        return convertView;
    }

}
