package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	public Survey update(Survey s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Survey delete(Survey s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Survey> findAllOrderByDateCreatedDesc() {
		return surveyRepo.findAllOrderByDateCreatedDes();
	}
	
	@Override
	public List<Survey> findAllTemplateOrderByDateCreatedDesc() {
		return surveyRepo.findAllTemplateOrderByDateCreatedDes();
	}
	
//	@Override
//	public List<Survey> findAllPublishedOrderByDateCreatedDesc() {
//		return surveyRepo.findAllPublishedOrderByDateCreatedDes();
//	}
	
    @Override
    public Page<Survey> findByTitleContainingIgnoreCaseAndTemplateIsTrue(String title, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 3, Sort.by(Direction.DESC, "dateCreated"));
        return surveyRepo.findByTitleContainingIgnoreCaseAndTemplateIsTrue(title, page);
    }

	@Override
	public Survey findById(int id) {
		return surveyRepo.getOne(id);
	}

//	@Override
//	public List<Survey> findByTitle(String title) {
//		return surveyRepo.findByTitle(title);
//	}
//	
	@Override
	public List<Survey> findByTitleContainingIgnoreCase(String title) {
		return surveyRepo.findByTitleContainingIgnoreCaseOrderByDateCreatedDesc(title);
	}

	@Override
	public List<Survey> findByDescriptionContainingIgnoreCase(String description) {
		return surveyRepo.findByDescriptionContainingIgnoreCaseOrderByDateCreatedDesc(description);
	}

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

		Survey survey = surveyRepo.getOne(newSurvey.getSurveyId());

		return survey;
	}
    @Override
    public Page<Survey> findByTemplateIsTrueOrderByDateCreatedDesc(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 10, Sort.by(Direction.DESC, "dateCreated"));
    
        return surveyRepo.findByTemplateIsTrueOrderByDateCreatedDesc(page);
    }
    
    @Override
    public Page<Survey> findByTemplateIsFalseOrderByDateCreatedDesc(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, 10, Sort.by(Direction.DESC, "dateCreated"));
    
        return surveyRepo.findByTemplateIsFalseOrderByDateCreatedDesc(page);
    }

	@Override
	public Page<Survey> findByCreatorIgnoreCaseAndTemplate(String creator, int pageNumber) {

		 Pageable page = PageRequest.of(pageNumber, 10, Sort.by(Direction.DESC, "dateCreated"));
		return surveyRepo.findByCreatorIgnoreCaseAndTemplate(creator,page);
	}

//	@Override
//	public Survey saveVersionTwo(SurveyQuestionAndAnswers sqa) {
//		Survey survey = sqa.getSurvey();
//		List<Question> question = sqa.getQuestion();
//		List<Answers> answers = sqa.getAnswers(); 
//		
//		
//	}

}