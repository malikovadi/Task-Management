package com.example.task_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class HelloController {
    private int selectedIndex = -1;

    @FXML
    private CheckBox Completed;
    @FXML
    private RadioButton Homework;
    @FXML
    private RadioButton Meeting;
    @FXML
    private RadioButton Shopping;
    @FXML
    private RadioButton Low;
    @FXML
    private RadioButton Medium;
    @FXML
    private RadioButton High;
    @FXML
    private DatePicker Deadline;

    @FXML
    private Button Delete;
    @FXML
    private ListView<TaskForDB> listView;
    ObservableList<TaskForDB> tasks = FXCollections.observableArrayList();

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputDesc;

    @FXML
    private Label label;

    private TaskDB taskDB;

    @FXML
    public void initialize() {
        // Initialize the ListView
        listView.setItems(tasks);

        // Initialize TaskDB
        taskDB = new TaskDB();

        // Fetch tasks from the database and populate the ListView
        fetchTasksFromDatabase();
    }

    private void fetchTasksFromDatabase() {
        try {
            // Fetch all tasks
            List<TaskForDB> tasksFromDB = taskDB.getAllTasks();

            // Add fetched tasks to the ObservableList
            tasks.setAll(tasksFromDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onListViewSelected() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        label.setText(String.valueOf(selectedIndex));

        if (selectedIndex >= 0) {
            TaskForDB selectedTask = tasks.get(selectedIndex);

            inputName.setText(selectedTask.getTaskName());
            inputDesc.setText(selectedTask.getDescription());
            Deadline.setValue(selectedTask.getDeadline());

            // Set the appropriate radio button based on the task type
            String taskType = selectedTask.getTaskType();
            switch (taskType) {
                case "Homework":
                    Homework.setSelected(true);
                    break;
                case "Meeting":
                    Meeting.setSelected(true);
                    break;
                case "Shopping":
                    Shopping.setSelected(true);
                    break;
                default:
                    Homework.setSelected(false);
                    Meeting.setSelected(false);
                    Shopping.setSelected(false);
            }

            // Add a null check for getCompleted()
            Boolean completed = selectedTask.isCompleted();
            Completed.setSelected(Boolean.TRUE.equals(completed));
            Completed.setVisible(true);

            // Display the priority
            Priority priority = selectedTask.getPriority();
            if (priority != null) {
                switch (priority) {
                    case LOW:
                        Low.setSelected(true);
                        break;
                    case MEDIUM:
                        Medium.setSelected(true);
                        break;
                    case HIGH:
                        High.setSelected(true);
                        break;
                    default:
                        Low.setSelected(false);
                        Medium.setSelected(false);
                        High.setSelected(false);
                }
            }

        } else {
            // Clear all fields if no task is selected
            inputName.clear();
            inputDesc.clear();
            Deadline.setValue(null);
            Homework.setSelected(false);
            Meeting.setSelected(false);
            Shopping.setSelected(false);
            Completed.setVisible(false);
            // Clear priority selection
            Low.setSelected(false);
            Medium.setSelected(false);
            High.setSelected(false);
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        createOrUpdateTask();
    }

    private void createOrUpdateTask() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            // If an existing task is selected, update it
            updateTask(selectedIndex);
        } else {
            // If no task is selected or a new task is being created, save it
            createNewTask();
        }

        // Clear input fields and reset UI elements
        clearInputFields();
    }

    private void createNewTask() {
        TaskForDB newTask = new TaskForDB();
        newTask.setTaskName(inputName.getText());
        newTask.setDescription(inputDesc.getText());
        newTask.setDeadline(Deadline.getValue());

        newTask.setTaskType(
                Homework.isSelected() ? "Homework" :
                        Meeting.isSelected() ? "Meeting" :
                                Shopping.isSelected() ? "Shopping" :
                                        " "
        );

        newTask.setCompleted(Completed.isSelected());

        // Set Priority
        if (Low.isSelected()) {
            newTask.setPriority(Priority.LOW);
        } else if (Medium.isSelected()) {
            newTask.setPriority(Priority.MEDIUM);
        } else if (High.isSelected()) {
            newTask.setPriority(Priority.HIGH);
        }

        try {
            int generatedId;
            if (newTask.getTaskID() == 0) {
                // Save the new task to the database
                generatedId = taskDB.createTask(newTask);
            } else {
                // Update the existing task in the database
                taskDB.updateTask(newTask);
                generatedId = newTask.getTaskID();
            }

            // Set the generated ID to the task
            newTask.setTaskID(generatedId);

            if (selectedIndex >= 0) {
                // If updating an existing task, replace it in the ObservableList
                tasks.set(selectedIndex, newTask);
            } else {
                // If creating a new task, add it to the ObservableList
                tasks.add(newTask);
            }

            // Optionally, refresh the ListView to reflect the changes
            listView.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., show an error message
        }
    }

    private void clearInputFields() {
        inputName.clear();
        inputDesc.clear();
        Deadline.setValue(null);
        Homework.setSelected(false);
        Meeting.setSelected(false);
        Shopping.setSelected(false);
        Completed.setVisible(false);
        // Clear priority selection
        Low.setSelected(false);
        Medium.setSelected(false);
        High.setSelected(false);
    }

    private void updateTask(int selectedIndex) {
        TaskForDB selectedTask = tasks.get(selectedIndex);
        // Update other task details
        selectedTask.setTaskName(inputName.getText());
        selectedTask.setDescription(inputDesc.getText());
        selectedTask.setDeadline(Deadline.getValue());

        selectedTask.setTaskType(
                Homework.isSelected() ? "Homework" :
                        Meeting.isSelected() ? "Meeting" :
                                Shopping.isSelected() ? "Shopping" :
                                        " "
        );

        selectedTask.setCompleted(Completed.isSelected());

        // Update Priority
        if (Low.isSelected()) {
            selectedTask.setPriority(Priority.LOW);
        } else if (Medium.isSelected()) {
            selectedTask.setPriority(Priority.MEDIUM);
        } else if (High.isSelected()) {
            selectedTask.setPriority(Priority.HIGH);
        }
        try {
            // Update the task in the database
            taskDB.updateTask(selectedTask);
            // Refresh the ListView to reflect the changes
            listView.refresh();
            // Deselect the item in the ListView
            listView.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., show an error message
        }
    }

    @FXML
    protected void onCompleteClick() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TaskForDB selectedTask = tasks.get(selectedIndex);
            selectedTask.markAsComplete();
            listView.refresh();
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            TaskForDB selectedTask = tasks.get(selectedIndex);
            try {
                // Delete the task from the database
                taskDB.deleteTask(selectedTask.getTaskID());

                // Remove the task from the ObservableList
                tasks.remove(selectedTask);

                // Optionally, refresh the ListView to reflect the changes
                listView.refresh();
                Completed.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        clearInputFields();
    }
    @FXML
    protected void onDeleteCompletedButtonClick() {
        // Create a copy of the tasks list to avoid concurrent modification issues
        List<TaskForDB> tasksToRemove = new ArrayList<>();

        // Identify completed tasks and add them to the list of tasks to remove
        for (TaskForDB task : tasks) {
            if (task.isCompleted()) {
                tasksToRemove.add(task);
                try {
                    // Delete the task from the database
                    taskDB.deleteTask(task.getTaskID());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Remove completed tasks from the tasks list
        tasks.removeAll(tasksToRemove);

        // Refresh the ListView
        listView.refresh();
        Completed.setVisible(false);
        clearInputFields();
    }


}
