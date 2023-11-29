package com.example.questtionnaire.vo;

import java.util.List;

import com.example.questtionnaire.constants.RtnCode;
import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;

public class QuizRes {
	
	private List<QuizVo> quizVoList;
	
	private RtnCode retCode;

	private List<QnQuVo> quQnVoList;
	
	public List<QnQuVo> getQuQnVoList() {
		return quQnVoList;
	}
	public void setQuQnVoList(List<QnQuVo> quQnVoList) {
		this.quQnVoList = quQnVoList;
	}
	public QuizRes(List<QuizVo> quizVoList, List<QnQuVo> quQnVoList, RtnCode retCode) {
		super();
		this.quizVoList = quizVoList;
		this.retCode = retCode;
		this.quQnVoList = quQnVoList;
	}
	public QuizRes() {
		super();
	}
	public QuizRes(List<QuizVo> quizVoList, RtnCode retCode) {
		super();
		this.quizVoList = quizVoList;
		this.retCode = retCode;
	}
	public List<QuizVo> getQuizVoList() {
		return quizVoList;
	}

	public void setQuizVoList(List<QuizVo> quizVoList) {
		this.quizVoList = quizVoList;
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
