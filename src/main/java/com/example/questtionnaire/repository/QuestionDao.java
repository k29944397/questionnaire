package com.example.questtionnaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.QuestionId;

public interface QuestionDao extends JpaRepository<Question,QuestionId>{
	
	public void deleteAllByQnIdIn(List<Integer> qnIdList);
	
	public void deleteAllByQnIdInAndQuIdIn(List<Integer> qnIdList,List<Integer> quIdList);
	
	public List<Question> findByQnIdInAndQnId(List<Integer> idList, int qnId);

	public List<Question> findAllByQnIdIn(List<Integer> qnIds);
}
