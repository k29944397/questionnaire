package com.example.questtionnaire.entity;

public class QuestionId {

	private int quid;
	
	private int qnId;

	public int getQuid() {
		return quid;
	}

	public QuestionId() {
		super();
	}

	public QuestionId(int quid, int qnId) {
		super();
		this.quid = quid;
		this.qnId = qnId;
	}

	public void setQuid(int quid) {
		this.quid = quid;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

}
