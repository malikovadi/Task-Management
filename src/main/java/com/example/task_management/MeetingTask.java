package com.example.task_management;

import javafx.fxml.FXML;
import java.time.LocalDate;
import java.util.Date;

public class MeetingTask implements Task
{
    private String taskName;
    private String description;
    private boolean completed;
    private Priority priority;
    private LocalDate deadline;

    @Override
    public boolean isCompleted()
    {
        return completed;
    }

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
    public void setDeadline(LocalDate date)
    {
        this.deadline = date;
    }

    public LocalDate getDeadline() {
        return deadline;
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
        if(completed)
        {
            return "Meeting: "+ this.taskName + " completed " + deadline;
        }
        return "Meeting: "+ this.taskName + " not completed " + deadline;
    }
}
