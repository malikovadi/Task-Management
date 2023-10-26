package com.example.task_management;

import java.time.LocalDate;

public interface Task
{
    void createTask(String taskName, String taskDescription);
    void setTaskName(String taskName);
    void setTaskDescription(String taskDescription);
    void markAsCompleted();
    void setPriority(Priority priority);
    void setDeadline(LocalDate date);
    boolean isCompleted();
}