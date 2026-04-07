// src/main/java/com/commitmates/controller/CheckInController.java
package com.commitmates.controller;

import com.commitmates.model.CheckIn;
import com.commitmates.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    // Show check-in form page
    @GetMapping
    public String showCheckInPage(Model model) {
        Long userId = 1L; // Replace with actual user
        CheckIn todayCheckIn = checkInService.getTodayCheckIn(userId);
        
        if (todayCheckIn != null) {
            model.addAttribute("checkIn", todayCheckIn);
            model.addAttribute("alreadyCheckedIn", true);
        } else {
            model.addAttribute("checkIn", new CheckIn());
            model.addAttribute("alreadyCheckedIn", false);
        }
        
        return "checkin";
    }

    // Save check-in
    @PostMapping("/save")
    public String saveCheckIn(@RequestParam int mood,
                             @RequestParam String reflection,
                             @RequestParam(required = false) String message) {
        Long userId = 1L; // Replace with actual user
        checkInService.save(userId, mood, reflection, message);
        return "redirect:/checkin/history";
    }

    // Update check-in
    @PostMapping("/update/{id}")
    public String updateCheckIn(@PathVariable Long id,
                               @RequestParam int mood,
                               @RequestParam String reflection,
                               @RequestParam(required = false) String message) {
        checkInService.update(id, mood, reflection, message);
        return "redirect:/checkin/history";
    }

    // NEW: View all check-ins history
    @GetMapping("/history")
    public String viewHistory(Model model) {
        Long userId = 1L; // Replace with actual user
        List<CheckIn> checkIns = checkInService.getAllCheckIns(userId);
        model.addAttribute("checkIns", checkIns);
        return "checkin-history";
    }
}