package com.commitmates.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Study", "Exercise", "Mindfulness"

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Habit> habits;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Habit> getHabits() { return habits; }
    public void setHabits(List<Habit> habits) { this.habits = habits; }
}
