package com.example.questtionnaire.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questtionnaire.constants.RtnCode;
import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;
import com.example.questtionnaire.repository.QuestionDao;
import com.example.questtionnaire.repository.QuestionnaireDao;
import com.example.questtionnaire.service.ifs.QuizService;
import com.example.questtionnaire.vo.QuestionRes;
import com.example.questtionnaire.vo.QuestionnaireRes;
import com.example.questtionnaire.vo.QuizReq;
import com.example.questtionnaire.vo.QuizRes;
import com.example.questtionnaire.vo.QuizVo;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuestionnaireDao qnDao;

	@Autowired
	private QuestionDao quDao;

	@Transactional
	@Override
	public QuizRes create(QuizReq req) {
		QuizRes checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}
		int quId = qnDao.save(req.getQuestionnaire()).getId();
		List<Question> quList = req.getQuestionList();
		if (quList.isEmpty()) {
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
//		int quId = qnDao.findTopByOrderByIdDesc().getId();
		for (Question qu : quList) {
			qu.setQnId(quId);
		}
		quDao.saveAll(quList);
		return new QuizRes(RtnCode.SUCCESSFUL);
	}

	@Override
	public QuizRes checkParam(QuizReq req) {
		Questionnaire qn = req.getQuestionnaire();
//		if(qn == null) {
//			return new QuizRes(RtnCode.QUESTIONNAIRE_PARAM_ERROR);
//		}
		if (!StringUtils.hasText(qn.getTitle()) || !StringUtils.hasText(qn.getDescription())
				|| qn.getStartDate() == null || qn.getEndDate() == null
				|| (!qn.getStartDate().isEqual(qn.getEndDate())) && qn.getStartDate().isAfter(qn.getEndDate())) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for (Question qu : quList) {
			if (qu.getQuId() <= 0 || !StringUtils.hasText(qu.getqTitle()) || !StringUtils.hasText(qu.getOptionType())
					|| !StringUtils.hasText(qu.getOption())) {
				return new QuizRes(RtnCode.QUESTION_PARAM_ERROR);
			}
		}
		return null;
	}

	@Override
	public QuizRes update(QuizReq req) {
		QuizRes checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}
		checkResult = checkQuestionnaireId(req);
		if (checkResult != null) {
			return checkResult;
		}
		Optional<Questionnaire> qnOp = qnDao.findById(req.getQuestionnaire().getId());
		if (qnOp.isEmpty()) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_NOT_FOUND);
		}
		Questionnaire qn = qnOp.get();
		// 可修改的條件:
		// 1. 尚未發布: is_published == false
		// 2. 已發布但尚未開始進行: is_published == true + 當時必須小於 start_date
		if (qn.isPublished() == false || (qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate()))) {
			qnDao.save(req.getQuestionnaire());// 用save,id不存在的話會新增
			quDao.saveAll(req.getQuestionList());
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		return new QuizRes(RtnCode.UPDATE_ERROR);
	}

	private QuizRes checkQuestionnaireId(QuizReq req) {
		if (req.getQuestionnaire().getId() <= 0) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
		List<Question> quList = req.getQuestionList();
		for (Question qu : quList) {
			if (qu.getQnId() != req.getQuestionnaire().getId()) {
				return new QuizRes(RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
			}
		}
		if (!qnDao.existsById(req.getQuestionnaire().getId())) {
			return new QuizRes(RtnCode.QUESTIONNAIRE_ID_NOT_FOUND);
		}
		return null;
	}

	@Override
	public QuizRes deleteQuestionnaire(List<Integer> qnIdList) {
		List<Questionnaire> qnList = qnDao.findByIdIn(qnIdList);
		List<Integer> idList = new ArrayList<>();
		for (Questionnaire qn : qnList) {
			if (!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
//				qnDao.deleteById(qn.getId());
				idList.add(qn.getId());
			}
		}
		if (!idList.isEmpty()) {
			qnDao.deleteAllByIdIn(idList);
			quDao.deleteAllByQnIdIn(idList);
		}
		return new QuizRes(RtnCode.SUCCESSFUL);
	}

	@Override
	public QuizRes deleteQuest(int qnId, List<Integer> quIdList) {
		Optional<Questionnaire> qnOp = qnDao.findById(qnId);
		if (qnOp.isEmpty()) {
			return new QuizRes(RtnCode.SUCCESSFUL);
		}
		Questionnaire qn = qnOp.get();
		if (!qn.isPublished() || qn.isPublished() && LocalDate.now().isBefore(qn.getStartDate())) {
			quDao.deleteAllByQnIdIn(quIdList);
		}
		return new QuizRes(RtnCode.SUCCESSFUL);
	}

	@Override
	public QuizRes search(String title, LocalDate startDate, LocalDate endDate) {
		title = StringUtils.hasText(title) ? title : "";
		startDate = startDate != null ? startDate : LocalDate.of(1971, 1, 1);
		endDate = endDate != null ? endDate : LocalDate.of(2099, 12, 31);
//		if (!StringUtils.hasText(title)) {
//			title = "";
//		}
//		if (startDate == null) {
//			startDate = LocalDate.of(1971, 1, 1);
//		}
//		if (endDate == null) {
//			endDate = LocalDate.of(2099, 12, 31);
//		}縮減後如上
		List<Questionnaire> qnList = qnDao.findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(title,
				startDate, endDate);
		List<Integer> qnIds = new ArrayList<>();
		for (Questionnaire qu : qnList) {
			qnIds.add(qu.getId());
		}
		List<Question> quList = quDao.findAllByQnIdIn(qnIds);
		List<QuizVo> quizVoList = new ArrayList<>();
		for (Questionnaire qn : qnList) {
			QuizVo vo = new QuizVo();
			vo.setQuestionnaire(qn);
			List<Question> questionList = new ArrayList<>();
			for (Question qu : quList) {
				if (qu.getQnId() == qn.getId()) {
					questionList.add(qu);
				}
			}
			vo.setQuestionList(questionList);
			quizVoList.add(vo);
		}
		return new QuizRes(quizVoList, RtnCode.SUCCESSFUL);
	}

	@Override
	public QuestionnaireRes searchQuestionnaireList(String title, LocalDate startDate, LocalDate endDate,boolean isPublished) {
		title = StringUtils.hasText(title) ? title : "";
		startDate = startDate != null ? startDate : LocalDate.of(1971, 1, 1);
		endDate = endDate != null ? endDate : LocalDate.of(2099, 12, 31);
		List<Questionnaire> qnList =new ArrayList<>();
//		if(!isAll) {
//			qnList = qnDao.findByTitleAndStartDateGreaterThanEqualAndEndDateLessThanEqual(title,
//					startDate, endDate);
//		}else {
//			qnList = qnDao.findByTitleAndStartDateGreaterThanEqual(title,
//					startDate, endDate);
//		}
		return new QuestionnaireRes(qnList, RtnCode.SUCCESSFUL);
	}

	@Override
	public QuestionRes searchQuestionList(int qnId) {
		if(qnId <= 0) {
			return new QuestionRes(null , RtnCode.QUESTIONNAIRE_ID_PARAM_ERROR);
		}
		List<Question> quList = quDao.findAllByQnIdIn(Arrays.asList(qnId));
//		return new QuestionRes(quList, RtnCode.SUCCESSFUL);
		return null;
	}
}
