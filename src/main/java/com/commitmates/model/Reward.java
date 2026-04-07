package com.commitmates.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;
    
    private String title;
    private String description;
    private LocalDateTime earnedDate;
    private String badgeIcon; // e.g., "trophy", "star", "medal"
    
    public Reward() {
        this.earnedDate = LocalDateTime.now();
    }
    
    public Reward(Habit habit, String title, String description, String badgeIcon) {
        this.habit = habit;
        this.title = title;
        this.description = description;
        this.badgeIcon = badgeIcon;
        this.earnedDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Habit getHabit() {
        return habit;
    }
    
    public void setHabit(Habit habit) {
        this.habit = habit;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getEarnedDate() {
        return earnedDate;
    }
    
    public void setEarnedDate(LocalDateTime earnedDate) {
        this.earnedDate = earnedDate;
    }
    
    public String getBadgeIcon() {
        return badgeIcon;
    }
    
    public void setBadgeIcon(String badgeIcon) {
        this.badgeIcon = badgeIcon;
    }
    
    // Helper method to format date for display
    public String getFormattedDate() {
        return earnedDate.toLocalDate().toString();
    }
}