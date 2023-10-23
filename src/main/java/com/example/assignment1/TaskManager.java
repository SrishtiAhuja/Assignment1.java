package com.example.assignment1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskManager {
    private ObservableList<Task> tasks;

    public TaskManager() {
        tasks = FXCollections.observableArrayList();
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
    private static TaskManager instance;


    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    // Method to add initial tasks

    public void deleteTask(String name) {
        for (Task task : tasks) {
            if (task.getTaskName().equals(name)) {
                System.out.println("Deleting Task: " + task.getTaskName());
                tasks.remove(task);
                break;  // Exit the loop after the task is deleted
            }
        }
    }

    public void editTask(String taskName, String newDescription, String newStatus) {
        // Find the task with the given name and update its description and status
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                task.setTaskDescription(newDescription);
                task.setTaskStatus(newStatus);
                break; // Stop after finding and updating the task
            }
        }
    }
    public void addInitialTasks() {
        tasks.add(new Task("Task 1", "Do Assignment", "Pending"));
        tasks.add(new Task("Task 2", "Make Bed", "In Progress"));
        tasks.add(new Task("Task 3", "Go to Work", "Completed"));
    }
}

