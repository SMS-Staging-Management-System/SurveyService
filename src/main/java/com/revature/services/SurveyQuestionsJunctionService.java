package com.revature.services;

import java.util.List;

import com.revature.models.SurveyQuestionsJunction;

public interface SurveyQuestionsJunctionService {
	SurveyQuestionsJunction save(SurveyQuestionsJunction sqj);
	SurveyQuestionsJunction update(SurveyQuestionsJunction sqj);
	SurveyQuestionsJunction delete(SurveyQuestionsJunction sqj);
	
	List<SurveyQuestionsJunction> findAll();
	SurveyQuestionsJunction findById(int id);
	List<SurveyQuestionsJunction> findBySurveyIdSurveyId(int surveyId);
	List<SurveyQuestionsJunction> findByQuestionIdQuestionId(int questionId);
}