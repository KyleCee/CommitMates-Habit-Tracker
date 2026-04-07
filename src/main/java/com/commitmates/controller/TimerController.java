package com.commitmates.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commitmates.model.Habit;
import com.commitmates.model.Reward;
import com.commitmates.service.HabitService;
import com.commitmates.service.RewardService;

@Controller
@RequestMapping("/timer")
public class TimerController {

    @Autowired
    private HabitService habitService;
    
    @Autowired
    private RewardService rewardService;

    @GetMapping
    public String showTimer(Model model) {
        model.addAttribute("habits", habitService.getAllHabits());
        return "timer";
    }

    @PostMapping("/add-time/{habitId}")
    @ResponseBody
    public String addTimeToHabit(@PathVariable Long habitId, @RequestParam int minutes) {
        Habit habit = habitService.getHabitById(habitId);
        if (habit != null) {
            // Add time to habit
            habit.setTotalTimeSpent(habit.getTotalTimeSpent() + minutes);

            // Update the last activity date to today
            habit.setLastActivityDate(LocalDate.now());

            habitService.saveHabit(habit);
            
            // Check for rewards
            Reward goalReward = rewardService.checkAndAwardReward(habit);
            Reward milestoneReward = rewardService.checkMilestoneReward(habit);
            
            // Return success message with reward info
            if (goalReward != null) {
                return "reward:" + goalReward.getTitle();
            } else if (milestoneReward != null) {
                return "reward:" + milestoneReward.getTitle();
            }
            
            return "success";
        }
        return "error";
    }
}