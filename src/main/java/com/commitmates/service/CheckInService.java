package com.commitmates.service;

import com.commitmates.model.CheckIn;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CheckInService {
    private Map<Long, CheckIn> checkIns = new HashMap<>();
    private Long idCounter = 1L;

    public CheckIn save(Long userId, int mood, String reflection, String message) {
        CheckIn checkIn = new CheckIn();
        checkIn.setId(idCounter++);
        checkIn.setUserId(userId);
        checkIn.setDate(LocalDate.now());
        checkIn.setMood(mood);
        checkIn.setReflection(reflection);
        checkIn.setMessage(message);
        
        checkIns.put(checkIn.getId(), checkIn);
        return checkIn;
    }

    public CheckIn getTodayCheckIn(Long userId) {
        LocalDate today = LocalDate.now();
        for (CheckIn checkIn : checkIns.values()) {
            if (checkIn.getUserId().equals(userId) && checkIn.getDate().equals(today)) {
                return checkIn;
            }
        }
        return null;
    }

    public CheckIn update(Long id, int mood, String reflection, String message) {
        CheckIn checkIn = checkIns.get(id);
        if (checkIn != null) {
            checkIn.setMood(mood);
            checkIn.setReflection(reflection);
            checkIn.setMessage(message);
        }
        return checkIn;
    }

    // NEW: Get all check-ins for user, sorted by date (newest first)
    public List<CheckIn> getAllCheckIns(Long userId) {
        return checkIns.values().stream()
            .filter(c -> c.getUserId().equals(userId))
            .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
            .collect(Collectors.toList());
    }
}
