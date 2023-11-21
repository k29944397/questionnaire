package com.example.questtionnaire.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;

@Repository
public interface QuestionnaireDao extends JpaRepository<Questionnaire,Question>{
	/**
	取得最新一筆資料:撈取全部資料後倒敘，最新的那筆資料會變成第一筆
	**/
	public boolean existsById(int id);

	public Optional<Questionnaire> findById(int id);
	
	public List<Questionnaire> findByIdIn(List<Integer> idList);
	
	public List<Questionnaire> findByIdInAndPublishedFalse(List<Integer> idList);
	
	public void deleteAllByIdIn(List<Integer> idList);
	
	public List<Questionnaire> findByTitleContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String title,LocalDate startDate,LocalDate endDate);

//	public List<Questionnaire> findByTitleAndStartDateGreaterThanEqualAndE(String title, LocalDate startDate,
//			LocalDate endDate); 
}
