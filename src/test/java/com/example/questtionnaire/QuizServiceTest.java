package com.example.questtionnaire;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questtionnaire.entity.Questionnaire;
import com.example.questtionnaire.repository.QuestionnaireDao;
import com.example.questtionnaire.service.ifs.QuizService;
import com.example.questtionnaire.vo.QuizRes;

@SpringBootTest
public class QuizServiceTest {

	@Autowired
	private QuizService service;
	

	@Autowired
	private QuestionnaireDao qnDao;
	
//	@Test
//	public void createTest() {
//		System.out.println("================");
//		Questionnaire questionnaire = new Questionnaire(0,"t3", "t", false, LocalDate.of(2023, 11, 24),
//				LocalDate.of(2023, 12, 01));
//		
//		List<Question> questionList = new ArrayList<>();
//		Question q1 = new Question(1,"test_question_1","single",false,"AAA;BBB;CCC");
//		Question q2 = new Question(2,"test_question_2","multi",false,"10;20;30;40");
//		Question q3 = new Question(3,"test_question_3","text",false,"AAAAAA");
//		questionList.addAll(Arrays.asList(q1,q2,q3));
//		
//		QuizReq req = new QuizReq(questionnaire,questionList);
//		QuizRes res = service.create(req);
//		Assert.isTrue(res.getRetCode().getCode()==400,"create error");
//	}
	
	@Test
	public void insertTest() {
		int res = qnDao.insert("qa_01", "qa_01 test", false, LocalDate.of(2023, 11, 30),LocalDate.of(2024, 01, 02));
		System.out.println(res);
	}
	@Test
	public void insertTest2() {
		int res = qnDao.insertData("qa_01", "qa_01 test", false, LocalDate.of(2023, 11, 30),LocalDate.of(2024, 01, 02));
		System.out.println(res);
	}
	
	@Test
	public void updateTest() {
		//要對到正確的id才會update成功，不然Error:0 資料也不會update上去
		int res = qnDao.update(5, "qn_007", "qn_007 test");
		System.out.println(res);
	}
	
	@Test
	public void selectTest1() {
//		List<Questionnaire> res = qnDao.findByStartDate(LocalDate.of(2023, 11, 30));
//		List<Questionnaire> res = qnDao.findByStartDate1(LocalDate.of(2023, 11, 30));
//		List<Questionnaire> res = qnDao.findByStartDate2(LocalDate.of(2023, 11, 30));
//		List<Questionnaire> res = qnDao.findByStartDate3(LocalDate.of(2023, 11, 30),true);
//		List<Questionnaire> res = qnDao.findByStartDate4(LocalDate.of(2023, 11, 30),true);
//		List<Questionnaire> res = qnDao.findByStartDate5(LocalDate.of(2023, 11, 30),true,3);
//		System.out.println(res.size());
	}
	
	@Test
	public void limitTest() {
		List<Questionnaire> res = qnDao.findWithLimitAndStartPostion(1, 3);
		for(Questionnaire item : res) {
			System.out.println(item.getId());
		}
		res.forEach(item ->{
			System.out.println(item.getId());
		});
	}
	
	@Test
	public void regexpTest() {
		List<Questionnaire> res = qnDao.searchDescriptionContaining2("qu", "qn");
		for(Questionnaire item : res) {
			System.out.println(item.getDescription());
		}
	}
	
//	@Test
//	public void selectFuzzyTest() {
//		QuizRes res = qnDao.selectFuzzy(null, null, null);
//	}
}
