package com.example.todoapp1;

public class Task {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    private Boolean isComplete = false;
    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task: " + name.toUpperCase() + " is completed: " + isComplete;

    }



}
