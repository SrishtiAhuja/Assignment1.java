package com.example.assignment1;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private final StringProperty taskName = new SimpleStringProperty();
    private final StringProperty taskDescription = new SimpleStringProperty();
    private final StringProperty taskStatus = new SimpleStringProperty();

    public Task(String name, String description, String status) {
        setTaskName(name);
        setTaskDescription(description);
        setTaskStatus(status);
    }

    public String getTaskName() {
        return taskName.get();
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String name) {
        taskName.set(name);
    }

    public String getTaskDescription() {
        return taskDescription.get();
    }

    public StringProperty taskDescriptionProperty() {
        return taskDescription;
    }

    public void setTaskDescription(String description) {
        taskDescription.set(description);
    }

    public String getTaskStatus() {
        return taskStatus.get();
    }

    public StringProperty taskStatusProperty() {
        return taskStatus;
    }

    public void setTaskStatus(String status) {
        taskStatus.set(status);
    }
}
