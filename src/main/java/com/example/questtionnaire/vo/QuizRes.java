package com.example.questtionnaire.vo;

import java.util.List;

import com.example.questtionnaire.constants.RtnCode;
import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;

public class QuizRes {
	
	private List<QuizVo> quizVoList;
	
	private RtnCode retCode;

	public QuizRes(List<QuizVo> quizVoList2, RtnCode successful) {
		super();
	}

	public QuizRes(RtnCode retCode) {
		super();
		this.retCode = retCode;
	}

	public RtnCode getRetCode() {
		return retCode;
	}

	public void setRetCode(RtnCode retCode) {
		this.retCode = retCode;
	}

}
