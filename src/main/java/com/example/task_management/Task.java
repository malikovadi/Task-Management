package com.example.task_management;

import java.util.Date;

public interface Task {
    void createTask(String taskName, String taskDescription);
    void setTaskName(String taskName);
    void setTaskDescription(String taskDescription);
    void markAsCompleted();
    void setPriority(Priority priority);
    void setDeadline(Date date);
}