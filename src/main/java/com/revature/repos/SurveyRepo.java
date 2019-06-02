package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Survey;
import java.util.List;

public interface SurveyRepo extends JpaRepository<Survey, Integer> {
//	@Query("From survey WHERE title LIKE CONCAT('%',:title,'%')")
//	 List<Survey> findByTitle(String title);
	
	List<Survey> findByTitleContainingIgnoreCase(String title);
	
	// finding by description
	List<Survey> findByDescriptionContainingIgnoreCase(String description);

}