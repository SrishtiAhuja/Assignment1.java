package com.example.assignment1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;

public class DisplayController {

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, String> taskName;

    @FXML
    private TableColumn<Task, String> taskDescription;

    @FXML
    private TableColumn<Task, String> taskStatus;

    private TaskManager taskManager;

    @FXML
    private void initialize() {
        taskManager = new TaskManager();
        taskManager.addInitialTasks();


        taskName.setCellValueFactory(cellData -> cellData.getValue().taskNameProperty());
        taskDescription.setCellValueFactory(cellData -> cellData.getValue().taskDescriptionProperty());
        taskStatus.setCellValueFactory(cellData -> cellData.getValue().taskStatusProperty());


        tableView.setItems(taskManager.getTasks());

        // for debugging
        for (Task task : tableView.getItems()) {
            System.out.println("Task Name: " + task.getTaskName());
            System.out.println("Task Description: " + task.getTaskDescription());
            System.out.println("Task Status: " + task.getTaskStatus());
        }
    }
    public ObservableList<Task> getTasks() {
        return tableView.getItems();
    }

    public void updateTasks(ObservableList<Task> updatedTasks) {
        // Clear the current data and add all the tasks from the updated list
        tableView.getItems().clear();
        tableView.getItems().addAll(updatedTasks);
    }


    @FXML
    private void edit() {

        Task selectedTask = tableView.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
            Parent root = loader.load();

            // Get the controller for the edit view
            EditController editController = loader.getController();
            editController.setTableView(tableView);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
