package com.example.task_management;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDB {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/tasks";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "";

    public int createTask(TaskForDB task) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "INSERT INTO Tasks (taskName, TaskDescription, deadline, taskType, completed, priority) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, task.getTaskName());
                statement.setString(2, task.getTaskDescription());
                statement.setObject(3, task.getDeadline());
                statement.setString(4, task.getTaskType());
                statement.setBoolean(5, task.isCompleted());
                statement.setString(6, task.getPriority().toString());
                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }
        throw new SQLException("Creating task failed, no ID obtained.");
    }

    public void updateTask(TaskForDB task) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "UPDATE Tasks SET taskName = ?, TaskDescription = ?, deadline = ?, taskType = ?, completed = ?, priority = ? WHERE taskID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, task.getTaskName());
                statement.setString(2, task.getTaskDescription());
                statement.setObject(3, task.getDeadline());
                statement.setString(4, task.getTaskType());
                statement.setBoolean(5, task.isCompleted());
                statement.setString(6, task.getPriority().toString());
                statement.setInt(7, task.getTaskID());

                statement.executeUpdate();
            }
        }
    }

    public List<TaskForDB> getAllTasks() throws SQLException {
        List<TaskForDB> tasks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT * FROM Tasks";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        tasks.add(getTaskFromResultSet(resultSet));
                    }
                }
            }
        }
        return tasks;
    }

    private TaskForDB getTaskFromResultSet(ResultSet resultSet) throws SQLException {
        TaskForDB task = new TaskForDB();
        task.setTaskID(resultSet.getInt("taskID"));
        task.setTaskName(resultSet.getString("taskName"));
        task.setTaskDescription(resultSet.getString("TaskDescription"));
        task.setDeadline(resultSet.getObject("deadline", LocalDate.class));
        task.setTaskType(resultSet.getString("taskType"));
        task.setCompleted(resultSet.getBoolean("completed"));
        task.setPriority(Priority.valueOf(resultSet.getString("priority")));
        return task;
    }

    public void deleteCompletedTasks() throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "DELETE FROM Tasks WHERE completed = true";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        }
    }

    public void deleteTask(int taskID) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "DELETE FROM Tasks WHERE taskID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, taskID);

                statement.executeUpdate();
            }
        }
    }
}
