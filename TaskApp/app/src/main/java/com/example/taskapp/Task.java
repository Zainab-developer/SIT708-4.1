package com.example.taskapp;

import java.util.ArrayList;
import java.util.Date;

public class Task
{
    public static ArrayList<Task> taskArrayList = new ArrayList<>();
    public static String Task_EDIT_EXTRA =  "taskEdit";

    private int id;
    private String title;
    private Date dueDate;

    private String description;
    private Date deleted;

    public Task(int id, String title, String description, Date dueDate, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.deleted = deleted;
    }
    public Task(int id, String title, String description )
    {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static Task getTaskForID(int passedTaskID)
    {
        for (Task task : taskArrayList)
        {
            if(task.getId() == passedTaskID)
                return task;
        }

        return null;
    }

    public static ArrayList<Task> nonDeletedTasks()
    {
        ArrayList<Task> nonDeleted = new ArrayList<>();
        for(Task task : taskArrayList)
        {
            if(task.getDeleted() == null)
                nonDeleted.add(task);
        }

        return nonDeleted;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }
}
