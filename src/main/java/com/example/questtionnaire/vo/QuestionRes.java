package com.example.questtionnaire.vo;

import java.util.List;

import com.example.questtionnaire.constants.RtnCode;
import com.example.questtionnaire.entity.Questionnaire;

public class QuestionRes {
	private List<Questionnaire> questionnaireList;
	private RtnCode renCode;
	public QuestionRes() {
		super();
	}
	public QuestionRes(List<Questionnaire> questionnaireList, RtnCode renCode) {
		super();
		this.questionnaireList = questionnaireList;
		this.renCode = renCode;
	}
	public List<Questionnaire> getQuestionnaireList() {
		return questionnaireList;
	}
	public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
	public RtnCode getRenCode() {
		return renCode;
	}
	public void setRenCode(RtnCode renCode) {
		this.renCode = renCode;
	}
}
