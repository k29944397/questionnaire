package com.example.questtionnaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;
import com.example.questtionnaire.service.ifs.QuizService;
import com.example.questtionnaire.vo.QuizReq;
import com.example.questtionnaire.vo.QuizRes;

@SpringBootTest
public class QuizServiceTest {

	@Autowired
	private QuizService service;

	@Test
	public void createTest() {
		Questionnaire questionnaire = new Questionnaire("t1", "t", false, LocalDate.of(2023, 11, 30),
				LocalDate.of(2023, 11, 31));
		
		List<Question> questionList = new ArrayList<>();
		Question q1 = new Question(1,"test_question_1","single",false,"AAA;BBB;CCC");
		Question q2 = new Question(2,"test_question_2","multi",false,"10;20;30;40");
		Question q3 = new Question(3,"test_question_3","text",false,"A");
		questionList.addAll(Arrays.asList(q1,q2,q3));
		
		QuizReq req = new QuizReq(questionnaire,questionList);
		QuizRes res = service.create(req);
		
		Assert.isTrue(res.getRetCode().getCode()==200,"create error");
		
	}
}
