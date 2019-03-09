package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Editor;
import com.revature.repos.EditorRepo;

@Service
public class EditorServiceImpl implements EditorService {

	@Autowired
	private EditorRepo eRepo;

	@Override
	public List<Editor> findAll() {
		return eRepo.findAll();
	}

	@Override
	public Editor findById(int id) {
		return eRepo.getOne(id);
	}

	@Override
	public Editor findByEmail(String email) {
		return eRepo.findByEmail(email);
	}

	@Override
	public Editor findBySurveyId(int id) {
		return eRepo.findBySurveyId(id);
	}

}
