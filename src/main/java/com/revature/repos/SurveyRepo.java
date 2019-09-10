package com.revature.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Survey;

import java.util.Date;

public interface SurveyRepo extends JpaRepository<Survey, Integer> {
	
	List<Survey> findByTitleContainingIgnoreCaseOrderByDateCreatedDesc(String title);
	
	// finding by description
	List<Survey> findByDescriptionContainingIgnoreCaseOrderByDateCreatedDesc(String description);

	@Query("From Survey ORDER BY dateCreated DESC")
	List<Survey> findAllOrderByDateCreatedDes();
	
	@Query("FROM Survey s WHERE s.template = :isTemplate")
	Page<Survey> findByTemplate(boolean isTemplate, Pageable page);
	
	@Query("FROM Survey s WHERE s.creator = :creator")
	Page<Survey> findByCreator(String creator, Pageable page);

	@Query("FROM Survey s WHERE (s.closingDate > :date OR s.closingDate = null) AND s.template = false")
	Page<Survey> findByActive(Date date, Pageable page);

	@Query("FROM Survey s WHERE s.closingDate <= :date AND s.template = false")
	Page<Survey> findByClosed(Date date, Pageable page);
	
	Page<Survey> findByTemplateIsTrueOrderByDateCreatedDesc(Pageable page);
	
    Page<Survey> findByTemplateIsFalseOrderByDateCreatedDesc(Pageable page);
    
    Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsTrue(String title, Pageable page);
	
    Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsFalse(String title, Pageable page);
	
    Page<Survey> findByCreatorIgnoreCaseAndTemplateIsTrue(String email, Pageable page);
	
    Page<Survey> findByCreatorIgnoreCaseAndTemplateIsFalse(String email, Pageable page);

	Page<Survey> findByTemplateIsTrue(Pageable page);

	Page<Survey> findByTemplateIsFalse(Pageable page);
}