package com.commitmates.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;
    private String description;
    private int hours;
    private int minutes;

    private boolean archived = false; // for archived habits
    private int totalTimeSpent = 0; // new field for timer
    private LocalDate lastActivityDate; // track last activity
    private String frequency; // daily, weekly, monthly
    private String target;    // goal description
    private String startDate; // yyyy-mm-dd
    private String endDate;   // yyyy-mm-dd

    public Habit() {}

    public Habit(String title, String description, int hours, int minutes) {
        this.title = title;
        this.description = description;
        this.hours = hours;
        this.minutes = minutes;
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
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

    public int getHours() { 
        return hours; 
    }
    
    public void setHours(int hours) { 
        this.hours = hours; 
    }

    public int getMinutes() { 
        return minutes; 
    }
    
    public void setMinutes(int minutes) { 
        this.minutes = minutes; 
    }

    public boolean getArchived() { 
        return archived; 
    }
    
    public void setArchived(boolean archived) { 
        this.archived = archived; 
    }

    public Category getCategory() { 
        return category; 
    }
    
    public void setCategory(Category category) {   
        this.category = category; 
    }

    //Getter and Setter for totalTimeSpent
    public int getTotalTimeSpent() { 
        return totalTimeSpent; 
    }
    
    public void setTotalTimeSpent(int totalTimeSpent) { 
        this.totalTimeSpent = totalTimeSpent; 
    }

    // Helper method to get formatted time spent (e.g., "2h 30m")
    public String getFormattedTimeSpent() { 
        int hrs = totalTimeSpent / 60;
        int mins = totalTimeSpent % 60;
        return hrs + "h " + mins + "m"; 
    }

    //Getter and Setter for lastActivityDate
    public LocalDate getLastActivityDate() { 
        return lastActivityDate; 
    }
    
    public void setLastActivityDate(LocalDate lastActivityDate) { 
        this.lastActivityDate = lastActivityDate; 
    }

    public String getFrequency() { 
        return frequency; 
    }
    
    public void setFrequency(String frequency) { 
        this.frequency = frequency; 
    }

    public String getTarget() { 
        return target; 
    }
    
    public void setTarget(String target) { 
        this.target = target; 
    }

    public String getStartDate() { 
        return startDate; 
    }
    
    public void setStartDate(String startDate) { 
        this.startDate = startDate; 
    }

    public String getEndDate() { 
        return endDate; 
    }
    
    public void setEndDate(String endDate) { 
        this.endDate = endDate; 
    }
}
