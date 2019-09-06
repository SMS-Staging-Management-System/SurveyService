package com.revature.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.revature.models.Survey;

public interface SurveyService {
	Survey save(Survey s);
	Survey update(Survey s);
	Survey delete(Survey s);
	
	List<Survey> findAllOrderByDateCreatedDesc();
	Page<Survey> findByTemplateOrderByDateCreatedDesc(String isTemplate, int page);
	Survey findById(int id);
//	List<Survey> findByTitle(String title);
	List<Survey> findByTitleContainingIgnoreCase(String title);
	List<Survey> findByDescriptionContainingIgnoreCase(String description);
	
}
