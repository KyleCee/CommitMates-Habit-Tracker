package com.commitmates.model;

import java.util.List;

public class DashboardModel {
	private String userNameString;
	private List<Habit> habits;
	private List<String> achievements;
	private List<String> moodStrings;
	
	public DashboardModel(String userName, List<Habit> habits, List<String> achievements, List<String> moods){
		this.userNameString = userName;
		this.habits = habits;
		this.achievements = achievements;
		this.moodStrings = moods;
		
	}

	public String getUserNameString() {
		return userNameString;
	}

	public void setUserNameString(String userNameString) {
		this.userNameString = userNameString;
	}

	public List<Habit> getHabits() {
		return habits;
	}

	public void setHabits(List<Habit> habits) {
		this.habits = habits;
	}

	public List<String> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<String> achievements) {
		this.achievements = achievements;
	}

	public List<String> getMoodStrings() {
		return moodStrings;
	}

	public void setMoodStrings(List<String> moodStrings) {
		this.moodStrings = moodStrings;
	}
	
	
	
}
