package com.commitmates.model;

import java.time.LocalDate;

public class JournalEntry {
    private Long id;
    private Long userId;
    private String thoughts;
    private String goals;
    private LocalDate date;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getThoughts() { return thoughts; }
    public void setThoughts(String thoughts) { this.thoughts = thoughts; }

    public String getGoals() { return goals; }
    public void setGoals(String goals) { this.goals = goals; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
