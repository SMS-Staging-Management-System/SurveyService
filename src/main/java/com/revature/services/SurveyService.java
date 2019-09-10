package com.revature.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.revature.models.Survey;

public interface SurveyService {
	Survey save(Survey s);
	Survey update(Survey s);
	Survey delete(Survey s);
	
	Survey findById(int id);
	List<Survey> findAllOrderByDateCreatedDesc();
	List<Survey> findByTitleContainingIgnoreCase(String title);
	List<Survey> findByDescriptionContainingIgnoreCase(String description);
	Page<Survey> findByTemplateIsTrue(int pageNumber);	
	Page<Survey> findByTemplateIsFalse(int pageNumber);
    Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsTrue(String title, int pageNumber);
    Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsFalse(String title, int pageNumber);
	Page<Survey> findByCreatorIgnoreCaseAndTemplateIsTrue(String email, int pageNumber);
	Page<Survey> findByCreatorIgnoreCaseAndTemplateIsFalse(String email, int pageNumber);

}
