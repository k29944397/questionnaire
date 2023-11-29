package com.example.questtionnaire.vo;

import java.util.List;

import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;

public class QuizReq extends QuizVo{

	public QuizReq() {
		super();
	}

	public QuizReq(Questionnaire questionnaire, List<Question> questionList) {
		super(questionnaire, questionList);
	}

}
