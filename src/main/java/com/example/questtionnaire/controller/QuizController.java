package com.example.questtionnaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.questtionnaire.service.ifs.QuizService;
import com.example.questtionnaire.vo.QuizReq;
import com.example.questtionnaire.vo.QuizRes;
import com.example.questtionnaire.vo.QuizSearchReq;

@RestController
@CrossOrigin
public class QuizController {
	//@RestController要先在build.gradle裡載好"implementation 'org.springframework.boot:spring-boot-starter-web'"
	@Autowired
	private QuizService service;
	//
	@PostMapping(value = "api/quiz/create")
	public QuizRes create(@RequestBody QuizReq req) {
		return service.create(req);
	}
	//
	@GetMapping(value = "api/quiz/search")
	public QuizRes search(@RequestBody QuizSearchReq req) {
		return service.search(req.getTitle(), req.getStartDate(), req.getEndDate());
	}
}
