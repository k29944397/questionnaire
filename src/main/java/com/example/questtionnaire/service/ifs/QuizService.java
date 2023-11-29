package com.example.questtionnaire.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.questtionnaire.vo.QuestionRes;
import com.example.questtionnaire.vo.QuestionnaireRes;
import com.example.questtionnaire.vo.QuizReq;
import com.example.questtionnaire.vo.QuizRes;
import com.example.questtionnaire.vo.QuizSearchReq;

public interface QuizService {
	
	public QuizRes checkParam(QuizReq req);

	public QuizRes create(QuizReq req);

	public QuizRes update(QuizReq req);

	public QuizRes deleteQuestionnaire(List<Integer> idList);

	public QuizRes deleteQuest(int qnId, List<Integer> quIdList);

	public QuizRes search(String title, LocalDate startDate, LocalDate endDate);
	
	public QuestionnaireRes searchQuestionnaireList(String title, LocalDate startDate, LocalDate endDate, boolean isPublished);

	public QuestionRes searchQuestionList(int qnId);

	public QuizRes searchFuzzy(String title, LocalDate startDate, LocalDate endDate);
}
