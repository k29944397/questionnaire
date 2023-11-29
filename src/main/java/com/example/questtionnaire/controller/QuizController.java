package com.example.questtionnaire.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questtionnaire.entity.Questionnaire;
import com.example.questtionnaire.service.ifs.QuizService;
import com.example.questtionnaire.vo.QuizReq;
import com.example.questtionnaire.vo.QuizRes;
import com.example.questtionnaire.vo.QuizSearchReq;

@RestController
@CrossOrigin
public class QuizController {
	// @RestController要先在build.gradle裡載好"implementation
	// 'org.springframework.boot:spring-boot-starter-web'"
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
		String title = StringUtils.hasText(req.getTitle()) ? req.getTitle() : "";
		LocalDate startDate = req.getStartDate() != null ? req.getStartDate() : LocalDate.of(1971, 01, 01);
		LocalDate endDate = req.getEndDate() != null ? req.getEndDate() : LocalDate.of(2099, 12, 31);
		return service.search(title, startDate, endDate);
	}

	@GetMapping(value = "api/quiz/search2")
	public QuizRes search(@RequestParam Map<String, Object> params) {
		Object paramTitle = params.get("title");
		Object paramStartDate = params.get("startDate");
		Object paramEndDate = params.get("endDate");
		String title = paramTitle != null ? (String) paramTitle : "";
		LocalDate startDate = paramStartDate != null ? LocalDate.parse((String) paramStartDate)
				: LocalDate.of(1971, 01, 01);
		LocalDate endDate = paramEndDate != null ? LocalDate.parse((String) paramEndDate) : LocalDate.of(2099, 12, 31);
		return service.search(title, startDate, endDate);
	}
	
	@GetMapping(value = "api/quiz/delete")
	public QuizRes deleteQuestionnaire(@RequestBody List<Integer> qnIdList) {
		return service.deleteQuestionnaire(qnIdList);
	}
}
