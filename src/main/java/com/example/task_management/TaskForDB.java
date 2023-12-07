package com.example.task_management;

import java.time.LocalDate;

public class TaskForDB{

    private int taskID;
    private String taskType;
    private String taskName;

    private String TaskDescription;
    private boolean completed;
    private Priority priority;
    private LocalDate deadline;

    public String getTaskDescription() {
        return TaskDescription;
    }
    public TaskForDB(){
    }
    public TaskForDB(int taskID) {
        this.taskID = taskID;
    }
    public TaskForDB(String taskName) {
        this.taskName = taskName;
    }
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void createTask(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.TaskDescription = taskDescription;
        this.completed = false;
    }


    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


    public void setTaskDescription(String taskDescription) {
        this.TaskDescription = taskDescription;
    }


    public void markAsComplete() {
        this.completed = true;
    }


    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    public void setDeadline(LocalDate date) {
        this.deadline = date;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return TaskDescription;
    }

    public void setDescription(String description) {
        this.TaskDescription = description;
    }

    public String toString(){
        String state;
        if (this.completed){
            state = "completed";
        }
        else
        {
            state = "not completed";
        }

        return this.taskType + ": " + this.taskName + " " + this.deadline + " " + state;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }

    public boolean getCompleted()
    {
        return completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
