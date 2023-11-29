package com.example.questtionnaire.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Id;

public class QnQuVo {
	
	private int id;

	private String title;
	
	private String description;

	private boolean published;

	private LocalDate startDate;

	private LocalDate endDate;

	private int quId;

	private String qTitle;

	private String optionType;

	private boolean necessary;

	private String option;
}
