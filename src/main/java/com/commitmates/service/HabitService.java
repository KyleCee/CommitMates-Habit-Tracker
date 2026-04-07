package com.commitmates.service;

import com.commitmates.model.Habit;
//import com.commitmates.service.HabitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HabitService {

    @Autowired
    private HabitRepo habitRepository;

    public List<Habit> getAllHabits() {
        return habitRepository.findByArchivedFalse();
    }

    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }

    public Habit getHabitById(Long id) {
        return habitRepository.findById(id).orElse(null);
    }
    
    public void archiveHabit(Long id) {
        Habit habit = getHabitById(id);
        if (habit != null) {
            habit.setArchived(true);
            habitRepository.save(habit);
        }
    }

    public List<Habit> getArchivedHabits() {
        return habitRepository.findByArchivedTrue();
    }

    public void restoreHabit(Long id) {
        Habit habit = getHabitById(id);
        if (habit != null) {
            habit.setArchived(false);
            habitRepository.save(habit);
        }
    }

    public void deleteArchivedHabit(Long id) {
        habitRepository.deleteById(id);
    }
}
