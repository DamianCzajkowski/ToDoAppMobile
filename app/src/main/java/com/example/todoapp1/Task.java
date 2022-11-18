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
        if(isComplete){
            return name.toUpperCase() + " \u2611";
        }
        return name.toUpperCase() + " \u2610";

    }



}
