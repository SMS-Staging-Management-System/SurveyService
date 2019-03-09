package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Responses;
import com.revature.repos.ResponsesRepo;

@Service
public class ResponsesServiceImpl implements ResponsesService {
	
	@Autowired
	private ResponsesRepo responseRepo;

	@Override
	public List<Responses> findAll() {
		return responseRepo.findAll();
	}

	@Override
	public Responses findById(int id) {
		return responseRepo.getOne(id);
	}

	@Override
	public List<Responses> findBySurveyIdSurveyId(int id) {
		return responseRepo.findBySurveyIdSurveyId(id);
	}

	@Override
	public List<Responses> findByUserEmail(String userEmail) {
		return responseRepo.findByUserEmail(userEmail);
	}

}
