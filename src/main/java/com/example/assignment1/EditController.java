package com.example.assignment1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
public class EditController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField statusField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    private TaskManager taskManager = TaskManager.getInstance();

    private TableView<Task> tableView;

    public void setTableView(TableView<Task> tableView) {
        this.tableView = tableView;
    }

    private DisplayController displayController;

    public void setDisplayController(DisplayController displayController) {
        this.displayController = displayController;
    }

    //function to add a new task
    @FXML
    private void addTask() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String status = statusField.getText();

        if (!name.isEmpty() && !description.isEmpty() && !status.isEmpty()) {
            Task newTask = new Task(name, description, status);
            taskManager.addTask(newTask);

            nameField.clear();
            descriptionField.clear();
            statusField.clear();

            if (tableView != null) {
                tableView.getItems().add(newTask);
            }
        }
    }

    //function to delete an existing task, if its name is given

    @FXML
    private void deleteTask() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            ObservableList<Task> tasks = tableView.getItems();


            Task taskToRemove = null;
            for (Task task : tasks) {
                if (task.getTaskName().equals(name)) {
                    taskToRemove = task;
                    break; // Breaking the loop on finding the function to be deleted
                }
            }

            // deleting the task
            if (taskToRemove != null) {
                tasks.remove(taskToRemove);
            }

            // Clear the name field
            nameField.clear();

            // Update the tableView to reflect the changes
            tableView.setItems(tasks);
        }
    }

//function to edit a task, or create new one if no such is found
    @FXML
    private void editTask() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String status = statusField.getText();

        if (!name.isEmpty() && !description.isEmpty() && !status.isEmpty()) {
            ObservableList<Task> tasks = tableView.getItems();
            Task taskToEdit = null;

            // Find the task with the specified name in the TableView
            for (Task task : tasks) {
                if (task.getTaskName().equals(name)) {
                    taskToEdit = task;
                    break;
                }
            }

            if (taskToEdit != null) {
                // If a task with the specified name is found, update its details
                taskToEdit.setTaskDescription(description);
                taskToEdit.setTaskStatus(status);
            } else {
                // If no task with the specified name is found, create a new task
                Task newTask = new Task(name, description, status);
                tasks.add(newTask);
            }

            // Clear input fields
            nameField.clear();
            descriptionField.clear();
            statusField.clear();
            displayController.updateTasks(tasks);
        }
    }

    //function to go back to the task list
    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("display.fxml"));
            Parent root = loader.load();
            DisplayController displayController = loader.getController();

            // Pass the current tasks from EditController to DisplayController
            ObservableList<Task> currentTasks = tableView.getItems();
            displayController.updateTasks(currentTasks);

            Scene scene = new Scene(root);

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }