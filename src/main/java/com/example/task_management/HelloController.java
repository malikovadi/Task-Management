package com.example.task_management;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private ListView<Task> listView;
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    public void initialize() {
        // Initialize the ListView
        listView.setItems(tasks);
    }

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputDesc;

    @FXML
    private Label label;

    @FXML
    protected void onListViewSelected(){
        int i = listView.getSelectionModel().getSelectedIndex();
        label.setText(""+i);

        HomeworkTask ht = (HomeworkTask) tasks.get(i);

        inputName.setText(ht.getTaskName());
        inputDesc.setText(ht.getDescription());
    }


    @FXML
    protected void onSaveButtonClick(){
        HomeworkTask ht = new HomeworkTask();
        ht.createTask(inputName.getText(), inputDesc.getText());
        tasks.add(ht);
        inputDesc.setText("");
        inputName.setText("");
    }
}