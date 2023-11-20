package com.example.questtionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="user")
public class User {
	
	@Column(name = "name")
	private String name;
	@Id
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private int age;

	@Column(name = "qn_id")
	private int qnId;

	@Column(name = "q_id")
	private int qId;

	@Column(name = "ans")
	private String ans;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
