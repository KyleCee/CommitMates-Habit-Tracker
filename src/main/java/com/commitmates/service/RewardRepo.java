package com.commitmates.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.commitmates.model.Reward;
import com.commitmates.model.Habit;
import java.util.List;

public interface RewardRepo extends JpaRepository<Reward, Long> {
    // Find all rewards for a specific habit
    List<Reward> findByHabit(Habit habit);
    
    // Find all rewards, ordered by date (newest first)
    List<Reward> findAllByOrderByEarnedDateDesc();
    
    // Check if a specific reward already exists for a habit
    boolean existsByHabitAndTitle(Habit habit, String title);
}