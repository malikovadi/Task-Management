package com.example.task_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.Locale;

public class HelloController
{
    @FXML
    private RadioButton Homework;

    @FXML
    private RadioButton Meeting;

    @FXML
    private RadioButton Shopping;

    @FXML
    private ListView<Task> listView;

    @FXML
    private DatePicker Date;
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    public void initialize() {
        listView.setItems(tasks);
    }

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputDesc;

    @FXML
    private Label label;

    @FXML
    private CheckBox Completed;
    @FXML
    protected void onListViewSelected()
    {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0)
        {
            label.setText("" + selectedIndex);
            label.setVisible(true);

            Task selectedTask = listView.getItems().get(selectedIndex);

            if (selectedTask instanceof HomeworkTask)
            {
                HomeworkTask hw = (HomeworkTask) selectedTask;
                inputName.setText(hw.getTaskName());
                inputDesc.setText(hw.getDescription());
                Completed.setSelected(false);
                LocalDate selectedDate = hw.getDeadline();
                if (selectedDate != null) {
                    Date.setValue(selectedDate);
                }
            }
            else if (selectedTask instanceof MeetingTask)
            {
                MeetingTask mt = (MeetingTask) selectedTask;
                inputName.setText(mt.getTaskName());
                inputDesc.setText(mt.getDescription());
                LocalDate selectedDate = mt.getDeadline();
                Completed.setSelected(false);
                if (selectedDate != null) {
                    Date.setValue(selectedDate);
                }
            }
            else if (selectedTask instanceof ShoppingTask)
            {
                ShoppingTask sht = (ShoppingTask) selectedTask;
                inputName.setText(sht.getTaskName());
                inputDesc.setText(sht.getDescription());
                LocalDate selectedDate = sht.getDeadline();
                Completed.setSelected(false);
                if (selectedDate != null) {
                    Date.setValue(selectedDate);
                }
            }
            Completed.setVisible(true);
        }
        else
        {
            inputName.clear();
            inputDesc.clear();
            label.setVisible(false);
            Completed.setVisible(false);
            Date.setValue(null);
        }
    }
    @FXML
    protected void onSaveButtonClick()
    {
        if(Homework.isSelected())
        {
            HomeworkTask hw = new HomeworkTask();
            hw.createTask(inputName.getText(), inputDesc.getText());
            LocalDate deadline = Date.getValue();
            hw.setDeadline(deadline);
            tasks.add(hw);
        }
        else if(Meeting.isSelected())
        {
            MeetingTask mt = new MeetingTask();
            mt.createTask(inputName.getText(), inputDesc.getText());
            LocalDate deadline = Date.getValue();
            mt.setDeadline(deadline);
            tasks.add(mt);
        }
        else if(Shopping.isSelected())
        {
            ShoppingTask sht = new ShoppingTask();
            sht.createTask(inputName.getText(), inputDesc.getText());
            LocalDate deadline = Date.getValue();
            sht.setDeadline(deadline);
            tasks.add(sht);
        }
        inputDesc.setText(null);
        inputName.setText(null);
        Completed.setVisible(false);
    }

    @FXML
    protected void onCompleteClick()
    {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = listView.getItems().get(selectedIndex);
            selectedTask.markAsCompleted();
            refreshListView();
        }
    }
    private void refreshListView()
    {
        listView.refresh();
    }

    @FXML
    protected void onDeleteClick() {
        // Create a copy of the tasks list to avoid concurrent modification issues
        List<Task> tasksToRemove = new ArrayList<>();

        // Identify completed tasks and add them to the list of tasks to remove
        for (Task task : tasks) {
            if (task.isCompleted()) {
                tasksToRemove.add(task);
            }
        }

        // Remove completed tasks from the tasks list
        tasks.removeAll(tasksToRemove);

        // Refresh the ListView
        refreshListView();
    }
}
