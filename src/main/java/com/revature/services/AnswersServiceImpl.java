package com.revature.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Answers;
import com.revature.repos.AnswersRepo;


@Service
public class AnswersServiceImpl implements AnswersService{

	
	@Autowired
	
	private AnswersRepo answersRepo;

	@Override
	public Answers save(Answers A) {
		A.setAnswerId(0);
		return answersRepo.save(A);
	}

	@Override
	public Answers update(Answers A) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answers delete(Answers A) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answers> findAll() {
		return answersRepo.findAll();
		
	}

	@Override
	public Answers findById(int id) {
		return answersRepo.getOne(id);
	}

	@Override
	public List<Answers> findByQuestionQuestionId(int questionId) {
		return answersRepo.findByQuestionQuestionId(questionId);
	}

	@Override
	public List<Answers> saveMultiple(List<Answers> answers  ) {
		answers.forEach(answer -> {
			save(answer);
		});
		return answers;
	}
	
	
	
	
	
	
}
