package com.example.questtionnaire.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizSearchReq {
	
	private String title;
	@JsonProperty("start_date")//postman¤ºªº¦WºÙ
	private LocalDate startDate;
	@JsonProperty("end_date")
	private LocalDate endDate;
	public QuizSearchReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuizSearchReq(String title, LocalDate startDate, LocalDate endDate) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
}
