package com.revature.services;

import java.util.List;
import com.revature.models.Question;

public interface QuestionService {
	List<Question> findAll();

	Question findById(int id);

	Question findByType(int typeId);

	Question save(Question q);

}
