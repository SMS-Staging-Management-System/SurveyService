package com.revature.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import com.revature.models.Answers;
import com.revature.models.Question;
import com.revature.models.Survey;
import com.revature.models.SurveyQuestionsJunction;
import com.revature.repos.QuestionRepo;
import com.revature.repos.SurveyRepo;

@Service
public class SurveyServiceImpl implements SurveyService {
	@Autowired
	private SurveyRepo surveyRepo;

	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public List<Survey> findAllOrderByDateCreatedDesc() {
		return surveyRepo.findAllOrderByDateCreatedDes();
	}
	
	@Override
	public Page<Survey> findByTemplateOrderByDateCreatedDesc(String isTemplate, int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 5, Sort.by(Order.desc("dateCreated")));
		return surveyRepo.findByTemplate(Boolean.valueOf(isTemplate), page);
	}
	
	@Override
	public Page<Survey> findByActiveOrderByDateCreatedDesc(String isActive, int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 5, Sort.by(Order.desc("dateCreated")));
		if(isActive.equalsIgnoreCase("true")) {
			return surveyRepo.findByActive(new Date() ,page);
		} else {
			return surveyRepo.findByClosed(new Date() ,page);
		}
		
	}
	

	@Override
	public Page<Survey> findAllByCreator(String creator, int pageNumber) {
		Pageable page = PageRequest.of(pageNumber, 5, Sort.by(Order.desc("dateCreated")));
		return surveyRepo.findByCreator(String.valueOf(creator), page);
	}
	
	@Override
	public Survey findById(int id) {
		return surveyRepo.getOne(id);
	}

	@Override
	public List<Survey> findByDescriptionContainingIgnoreCase(String description) {
		return surveyRepo.findByDescriptionContainingIgnoreCaseOrderByDateCreatedDesc(description);
	}
	
	
	private Sort dateDesc = Sort.by(Direction.DESC, "dateCreated");

	@Override
	@Transactional
	public Survey save(Survey s) {

		Survey newSurvey = surveyRepo.save(s);

		List<SurveyQuestionsJunction> sqj = s.getQuestionJunctions();

		for (int i = 0; i < sqj.size(); i++) {

			Question newQuestion = questionRepo.save(sqj.get(i).getQuestion());
			
			List<Answers> answers = sqj.get(i).getQuestion().getAnswers();

			if (newQuestion.getTypeId() != 5) {
				
				for (int j = 0; j < answers.size(); j++) {
					Answers ans = answers.get(j);
					ans.setQuestion(newQuestion);
				}
			}
			SurveyQuestionsJunction newSqj = sqj.get(i);
			newSqj.setQuestion(newQuestion);
			newSqj.setSurvey(newSurvey);
			newSqj.setQuestionOrder(i + 1);
		}

		return newSurvey;
	}

	

	@Override
	public Survey update(Survey s) {
		return null;
	}

	@Override
	public Survey delete(Survey s) {
		return null;
	}
	
	@Override
	public List<Survey> findByTitleContainingIgnoreCase(String title) {
		return surveyRepo.findByTitleContainingIgnoreCaseOrderByDateCreatedDesc(title);
	}

	@Override
	public Page<Survey> findByTemplateIsTrue(int pageNumber) {
		 Pageable page = PageRequest.of(pageNumber, 10, dateDesc);
		return surveyRepo.findByTemplateIsTrue(page);
	}

	@Override
	public Page<Survey> findByTemplateIsFalse(int pageNumber) {
		 Pageable page = PageRequest.of(pageNumber, 10, dateDesc);
		return surveyRepo.findByTemplateIsFalse(page);
	}

    @Override
    public Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsTrue(String title, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 10, dateDesc);
        return surveyRepo.findByTitleContainingIgnoreCaseAndTemplateIsTrue(title, page);
    }
    
    @Override
    public Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsFalse(String title, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 10, dateDesc);
        return surveyRepo.findByTitleContainingIgnoreCaseAndTemplateIsFalse(title, page);
    }

	@Override
	public Page<Survey> findByCreatorIgnoreCaseAndTemplateIsTrue(String email, int pageNumber) {
		 Pageable page = PageRequest.of(pageNumber, 10, dateDesc);
		return surveyRepo.findByCreatorIgnoreCaseAndTemplateIsTrue(email,page);
	}

	@Override
	public Page<Survey> findByCreatorIgnoreCaseAndTemplateIsFalse(String email, int pageNumber) {
		 Pageable page = PageRequest.of(pageNumber, 10, dateDesc);
		return surveyRepo.findByCreatorIgnoreCaseAndTemplateIsFalse(email,page);
	}

}