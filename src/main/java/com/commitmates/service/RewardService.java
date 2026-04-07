package com.commitmates.service;

import com.commitmates.model.Habit;
import com.commitmates.model.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepo rewardRepository;
    
    // Check if habit goal is met and award reward if needed
    public Reward checkAndAwardReward(Habit habit) {
        int goalMinutes = (habit.getHours() * 60) + habit.getMinutes();
        int actualMinutes = habit.getTotalTimeSpent();
        
        // Check if goal is reached
        if (actualMinutes >= goalMinutes) {
            // Check if reward already exists for this milestone
            String rewardTitle = "Goal Achieved: " + habit.getTitle();
            
            if (!rewardRepository.existsByHabitAndTitle(habit, rewardTitle)) {
                // Create new reward
                Reward reward = new Reward(
                    habit,
                    rewardTitle,
                    "Congratulations! You completed your " + habit.getHours() + "h " + 
                    habit.getMinutes() + "m goal for " + habit.getTitle(),
                    "trophy"
                );
                
                return rewardRepository.save(reward);
            }
        }
        
        return null; // No reward earned
    }
    
    // Check for milestone rewards (50%, 150%, 200%, etc.)
    public Reward checkMilestoneReward(Habit habit) {
        int goalMinutes = (habit.getHours() * 60) + habit.getMinutes();
        int actualMinutes = habit.getTotalTimeSpent();
        
        if (goalMinutes == 0) return null;
        
        double percentage = (double) actualMinutes / goalMinutes * 100;
        
        // Check various milestones
        if (percentage >= 200 && !rewardRepository.existsByHabitAndTitle(habit, "Double Master")) {
            return saveReward(habit, "Double Master", "You've done 2x your goal!", "medal");
        }
        else if (percentage >= 150 && !rewardRepository.existsByHabitAndTitle(habit, "Overachiever")) {
            return saveReward(habit, "Overachiever", "You've exceeded your goal by 50%!", "star");
        }
        else if (percentage >= 50 && percentage < 100 && !rewardRepository.existsByHabitAndTitle(habit, "Halfway There")) {
            return saveReward(habit, "Halfway There", "You're 50% towards your goal!", "fire");
        }
        
        return null;
    }
    
    private Reward saveReward(Habit habit, String title, String description, String icon) {
        Reward reward = new Reward(habit, title, description, icon);
        return rewardRepository.save(reward);
    }
    
    // Get all rewards
    public List<Reward> getAllRewards() {
        return rewardRepository.findAllByOrderByEarnedDateDesc();
    }
    
    // Get rewards for a specific habit
    public List<Reward> getRewardsByHabit(Habit habit) {
        return rewardRepository.findByHabit(habit);
    }
    
    // Get total number of rewards
    public int getTotalRewardsCount() {
        return (int) rewardRepository.count();
    }
    
    // Delete a reward
    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }
}