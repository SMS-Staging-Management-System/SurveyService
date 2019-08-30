package com.revature.dtos;

import java.util.List;

import com.revature.models.Answers;
import com.revature.models.Question;
import com.revature.models.Survey;

public class SurveyQuestionAndAnswers {
	private Survey survey;
	
	private List<Question> question;
	
	private List<Answers> answers;

	public SurveyQuestionAndAnswers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SurveyQuestionAndAnswers(Survey survey, List<Question> question, List<Answers> answers) {
		super();
		this.survey = survey;
		this.question = question;
		this.answers = answers;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((survey == null) ? 0 : survey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyQuestionAndAnswers other = (SurveyQuestionAndAnswers) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (survey == null) {
			if (other.survey != null)
				return false;
		} else if (!survey.equals(other.survey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SurveyQuestionAndAnswers [survey=" + survey + ", question=" + question + ", answers=" + answers + "]";
	}
	
	
}
