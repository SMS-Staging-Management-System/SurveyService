package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.Question;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

	Question findByTypeId(int typeId);

}