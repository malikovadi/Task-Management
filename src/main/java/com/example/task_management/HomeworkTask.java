package com.example.task_management;

import java.util.Date;

public class HomeworkTask implements Task
{
    private String taskName;
    private String description;
    private boolean completed;
    private Priority priority;
    private Date  deadline;

    @Override
    public void createTask(String taskName, String taskDescription)
    {
        this.taskName = taskName;
        this.description = taskDescription;
        this.completed = false;
    }

    @Override
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    @Override
    public void setTaskDescription(String taskDescription)
    {
        this.description = taskDescription;
    }

    @Override
    public void markAsCompleted()
    {
        this.completed = true;
    }

    @Override
    public void setPriority(Priority priority)
    {
        this.priority = priority;
    }

    @Override
    public void setDeadline(Date date)
    {
        this.deadline = date;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String toString()
    {
        return "Homework: "+ this.taskName + " "+this.completed;
    }
}
