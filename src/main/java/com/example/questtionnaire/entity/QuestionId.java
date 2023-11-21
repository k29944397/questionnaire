package com.example.questtionnaire.entity;

import java.io.Serializable;

public class QuestionId implements Serializable{

	private int quId;
	
	private int qnId;

	public int getQuId() {
		return quId;
	}

	public QuestionId() {
		super();
	}

	public QuestionId(int quId, int qnId) {
		super();
		this.quId = quId;
		this.qnId = qnId;
	}

	public void setQuid(int quid) {
		this.quId = quId;
	}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

}
