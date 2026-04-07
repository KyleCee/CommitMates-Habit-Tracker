package com.commitmates.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.commitmates.model.CheckIn;
import com.commitmates.model.Habit;
import com.commitmates.service.CategoryService;
import com.commitmates.service.CheckInService;
import com.commitmates.service.HabitService;

@Controller
public class DashboardController {

    @Autowired
    private HabitService habitService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CheckInService checkInService;
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Storing habits in a list first so we can use them for the reminder logic below
        List<Habit> allHabits = habitService.getAllHabits();
        model.addAttribute("habits", allHabits);
        model.addAttribute("categories", categoryService.getAllCategories());

        // Check for daily check-in
        Long userId = 1L;
        CheckIn todayCheckIn = checkInService.getTodayCheckIn(userId);
        
        if (todayCheckIn == null) {
            model.addAttribute("reminder", "Gentle reminder: Take a moment to check in with yourself today. 🌱");
        } else {
            // If check-in is done, check if any habits are "missed" today
            LocalDate today = LocalDate.now();
            Habit missedHabit = null;

            for (Habit h : allHabits) {
                // If date is null (never done) or date is not today
                if (h.getLastActivityDate() == null || !h.getLastActivityDate().isEqual(today)) {
                    missedHabit = h;
                    break; // Found one, that's enough for a reminder
                }
            }

            if (missedHabit != null) {
                model.addAttribute("reminder", "You haven't worked on '" + missedHabit.getTitle() + "' yet today. A few minutes is all it takes! 🕰️");
            }
        }

        String[] dailyAffirmations = {
                "There is peace available to you that is stronger than stress.",
                "Even small beginnings can grow into something beautiful. Keep Going!",
                "You are wonderfully made, with a purpose uniquely your own.",
                "You have the power to overcome anything, because strength flows through you daily.",
                "Today is a fresh page, Write something small on it.",
                "Your efforts matters more than your speed.",
                "You deserve a slow, steady day. Take one step at a time."

        };

        int index = (int)(Math.random() * dailyAffirmations.length);
        model.addAttribute("dailyAffirmationsMessage", dailyAffirmations[index]);
        return "dashboard";
    }
}