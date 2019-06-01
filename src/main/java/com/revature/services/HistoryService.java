package com.revature.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.History;

public interface HistoryService {

	List<History> findAll();

	History findById(int id);
	
	List<History> findBySurveyId(int surveyId);

	List<History> findByEmail(String email);
	
	List<History> findCompleted();
	
	List<History> findIncomplete();

	History save(History h);
	
	History update(History h);

	Page<History> findAllBySurveyId(int surveyId, Pageable pageable);

}
