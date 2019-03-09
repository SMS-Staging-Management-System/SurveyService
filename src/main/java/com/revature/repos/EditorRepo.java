package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Editor;

public interface EditorRepo extends JpaRepository<Editor, Integer> {
	
	Editor findBySurveyId(int id);
	
	Editor findByEmail(String email);
	
	

}
