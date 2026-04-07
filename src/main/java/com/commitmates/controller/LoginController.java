package com.commitmates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping({"/index"})
    public String showLogin() {
        return "index"; // use index.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        if(email.equals("user@rumi.com") && password.equals("mindful123")) {
            model.addAttribute("message", "Welcome back, " + name + "!");
            model.addAttribute("error", ""); // clear error
        } else {
            model.addAttribute("error", "Invalid email or password");
            model.addAttribute("message", ""); // clear success
        }

        return "dashboard"; // go to dashboard
    }
}