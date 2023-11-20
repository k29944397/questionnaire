package com.example.questtionnaire.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;

public class QuizVo {

	private Questionnaire questionnaire = new Questionnaire();
	
	private List<Question> questionList = new ArrayList<>();

	public QuizVo() {
		super();
	}

	public QuizVo(Questionnaire questionnaire2, List<Question> questionList) {
		super();
		this.questionnaire = questionnaire;
		this.questionList = questionList;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
		this.questionnaire = questionnaire;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
}
