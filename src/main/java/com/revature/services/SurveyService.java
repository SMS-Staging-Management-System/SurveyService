package com.revature.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.revature.models.Survey;

public interface SurveyService {
	Survey save(Survey s);
	Survey update(Survey s);
	Survey delete(Survey s);
	
	List<Survey> findAllOrderByDateCreatedDesc();
	List<Survey> findAllTemplateOrderByDateCreatedDesc();
//	List<Survey> findAllPublishedOrderByDateCreatedDesc();
	Survey findById(int id);
//	List<Survey> findByTitle(String title);
	List<Survey> findByTitleContainingIgnoreCase(String title);
	List<Survey> findByDescriptionContainingIgnoreCase(String description);
	Page<Survey> findByTemplateIsTrueOrderByDateCreatedDesc(int pageNumber);
    Page<Survey> findByTemplateIsFalseOrderByDateCreatedDesc(int pageNumber);
    Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsTrue(String title, int pageNumber);
	Page<Survey> findByCreatorIgnoreCaseAndTemplateIsTrue(String creator, int page);
}
