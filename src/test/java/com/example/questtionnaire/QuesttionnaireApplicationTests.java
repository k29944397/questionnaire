package com.example.questtionnaire;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questtionnaire.entity.Questionnaire;
import com.example.questtionnaire.repository.QuestionDao;
import com.example.questtionnaire.repository.QuestionnaireDao;

@SpringBootTest
class QuesttionnaireApplicationTests {

//	@Autowired
	private QuestionDao qnDao;

//	@Autowired
	private QuestionnaireDao quDao;

	@Autowired
    public QuesttionnaireApplicationTests(QuestionnaireDao quDao, QuestionDao qnDao) {
        this.quDao = quDao;
        this.qnDao = qnDao;
    }
	
//	@Test
//	public void test() {
//		LocalDate start = LocalDate.now();
//		LocalDate end = LocalDate.of(2023, 11, 31);
//		List<Questionnaire> res = quDao.findByTitleAndStartDateGreaterThanEqualAndEndDateLessThanEqual("AAA", start,
//				end);
//		
//	}

}
