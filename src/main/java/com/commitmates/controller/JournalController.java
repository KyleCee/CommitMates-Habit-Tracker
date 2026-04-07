package com.commitmates.controller;

import com.commitmates.model.JournalEntry;
import com.commitmates.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public String showJournalPage(Model model) {
        model.addAttribute("journal", new JournalEntry());
        return "journal";
    }

    @PostMapping("/save")
    public String saveJournal(@RequestParam String thoughts,
                              @RequestParam(required = false) String goals,
                              @RequestParam LocalDate date) {
        Long userId = 1L;
        journalService.save(userId, thoughts, goals, date);
        return "redirect:/journal/history";
    }

    @GetMapping("/history")
    public String viewHistory(Model model) {
        Long userId = 1L;
        List<JournalEntry> journals = journalService.getAllJournals(userId);
        model.addAttribute("journals", journals);
        return "journalHistory";
    }

    // NEW: Edit form
    @GetMapping("/edit/{id}")
    public String editJournal(@PathVariable Long id, Model model) {
        JournalEntry entry = journalService.findById(id);
        model.addAttribute("journal", entry);
        return "journalEdit"; // new page you’ll make
    }

    // NEW: Update existing entry
    @PostMapping("/update/{id}")
    public String updateJournal(@PathVariable Long id,
                                @RequestParam String thoughts,
                                @RequestParam(required = false) String goals,
                                @RequestParam LocalDate date) {
        Long userId = 1L;
        journalService.update(id, userId, thoughts, goals, date);
        return "redirect:/journal/history";
    }

    // NEW: Delete
    @GetMapping("/delete/{id}")
    public String deleteJournal(@PathVariable Long id) {
        journalService.deleteById(id);
        return "redirect:/journal/history";
    }
}
