package com.commitmates.controller;

import com.commitmates.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public String showRewards(Model model) {
        model.addAttribute("rewards", rewardService.getAllRewards());
        model.addAttribute("totalRewards", rewardService.getTotalRewardsCount());
        return "rewards";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
        return "redirect:/rewards";
    }
}