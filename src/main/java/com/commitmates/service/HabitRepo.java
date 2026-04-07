package com.commitmates.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.commitmates.model.Habit;
import java.util.List;

public interface HabitRepo extends JpaRepository<Habit, Long> {
    List<Habit> findByArchivedFalse();
    List<Habit> findByArchivedTrue();
}
