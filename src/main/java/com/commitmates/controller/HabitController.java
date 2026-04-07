package com.commitmates.controller;

import com.commitmates.model.Category;
import com.commitmates.model.Habit;
import com.commitmates.service.CategoryService;
import com.commitmates.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showHabits(Model model) {
        model.addAttribute("habits", habitService.getAllHabits());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "habits";
    }

    @PostMapping("/save")
    public String saveHabit(@ModelAttribute Habit habit, @RequestParam Long categoryId) {
        habit.setCategory(categoryService.getCategoryById(categoryId));
        habitService.saveHabit(habit);
        return "redirect:/habits";
    }


    @PostMapping("/new")
    public String createHabit(@RequestParam String title,
                            @RequestParam String description,
                            @RequestParam int hours,
                            @RequestParam int minutes,
                            @RequestParam(required = false) Long categoryId) {

        Habit habit = new Habit(title, description, hours, minutes);

        // Only assign category if user selected one (avoid null/empty crash)
        if (categoryId != null) {
            habit.setCategory(categoryService.getCategoryById(categoryId));
        }

        habitService.saveHabit(habit);
        return "redirect:/habits";
    }


    @GetMapping("/delete/{id}")
    public String deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return "redirect:/habits";
    }

    @GetMapping("/edit/{id}")
    public String editHabit(@PathVariable Long id, Model model) {
        Habit habit = habitService.getHabitById(id);
        if (habit != null) {
            model.addAttribute("habit", habit);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "edit-habit";
        } else {
            return "redirect:/dashboard";
        }
    }

    @GetMapping("/duplicate/{id}")
    public String duplicateHabit(@PathVariable Long id) {
        Habit original = habitService.getHabitById(id);
        if (original != null) {
            Habit duplicate = new Habit();
            duplicate.setTitle(original.getTitle());
            duplicate.setDescription(original.getDescription());
            duplicate.setHours(original.getHours());
            duplicate.setMinutes(original.getMinutes());
            duplicate.setCategory(original.getCategory());
            habitService.saveHabit(duplicate);
        }
        return "redirect:/habits";
    }

    @GetMapping("/archive/{id}")
    public String archiveHabit(@PathVariable Long id) {
        habitService.archiveHabit(id);
        return "redirect:/habits";
    }

    @GetMapping("/archived")
    public String showArchivedHabits(Model model) {
        model.addAttribute("archivedHabits", habitService.getArchivedHabits());
        return "archived";
    }

    @GetMapping("/restore/{id}")
    public String restoreHabit(@PathVariable Long id) {
        habitService.restoreHabit(id);
        return "redirect:/habits/archived";
    }

    @GetMapping("/deleteArchived/{id}")
    public String deleteArchivedHabit(@PathVariable Long id) {
        habitService.deleteArchivedHabit(id);
        return "redirect:/habits/archived";
    }

    @PostMapping("/category/new")
    public String addCategoryFromForm(@RequestParam String name) {
        Category newCategory = new Category(name);
        categoryService.saveCategory(newCategory);
        return "redirect:/habits";
    }


    @PostMapping("/category/new/raw")
    @ResponseBody
    public String addCategoryRaw(@RequestParam String name) {
        Category newCategory = new Category(name);
        categoryService.saveCategory(newCategory);
        return String.valueOf(newCategory.getId());
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/habits";
    }

    @PostMapping("/goal/save")
    public String saveGoal(@RequestParam Long habitId,
                           @RequestParam String frequency,
                           @RequestParam String target,
                           @RequestParam String startDate,
                           @RequestParam String endDate) {
        Habit habit = habitService.getHabitById(habitId);
        if (habit != null) {
            habit.setFrequency(frequency);
            habit.setTarget(target);
            habit.setStartDate(startDate);
            habit.setEndDate(endDate);
            habitService.saveHabit(habit);
        }
        return "redirect:/habits";
    }
}
