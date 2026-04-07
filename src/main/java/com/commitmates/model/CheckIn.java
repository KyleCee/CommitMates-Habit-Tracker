package com.commitmates.model;

import java.time.LocalDate;

public class CheckIn {
    private Long id;
    private Long userId;
    private LocalDate date;
    private int mood; // 1-5 scale
    private String reflection;
    private String message;

    // Constructor
    public CheckIn() {}

    public CheckIn(Long userId, int mood, String reflection, String message) {
        this.userId = userId;
        this.date = LocalDate.now();
        this.mood = mood;
        this.reflection = reflection;
        this.message = message;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
