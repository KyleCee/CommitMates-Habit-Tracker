package com.commitmates.service;

import com.commitmates.model.JournalEntry;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JournalService {

    private final Map<Long, JournalEntry> journalRepo = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public void save(Long userId, String thoughts, String goals, LocalDate date) {
        JournalEntry entry = new JournalEntry();
        entry.setId(idCounter.getAndIncrement());
        entry.setUserId(userId);
        entry.setThoughts(thoughts);
        entry.setGoals(goals);
        entry.setDate(date);
        journalRepo.put(entry.getId(), entry);
    }

    public List<JournalEntry> getAllJournals(Long userId) {
        List<JournalEntry> list = new ArrayList<>(journalRepo.values());
        list.sort(Comparator.comparing(JournalEntry::getDate).reversed());
        return list;
    }

    public JournalEntry findById(Long id) {
        return journalRepo.get(id);
    }

    public void update(Long id, Long userId, String thoughts, String goals, LocalDate date) {
        JournalEntry existing = journalRepo.get(id);
        if (existing != null) {
            existing.setThoughts(thoughts);
            existing.setGoals(goals);
            existing.setDate(date);
        }
    }

    public void deleteById(Long id) {
        journalRepo.remove(id);
    }

    public void clearAll() {
        journalRepo.clear();
    }
}
