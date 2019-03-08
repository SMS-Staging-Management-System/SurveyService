package com.revature.services;

import com.revature.models.*;
import java.util.List;

public interface ResponsesService {
	List<Responses> findAll();
	Responses findById(int id);
	List<Responses> findBySurveyIdSurveyId(int id);
	List<Responses> findByUserEmail(String userEmail);
	
}
