package com.example.questtionnaire.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.questtionnaire.entity.Question;
import com.example.questtionnaire.entity.Questionnaire;
import com.example.questtionnaire.vo.QnQuVo;

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

	/**
	 * 不寫 nativeQuery 等同於nativeQuery = false
	 * 語法鐘錶的名稱要變成entity的class名稱;欄位名稱要變成屬性名稱
	 * clearAutomatically = true: 清除持久化上下文，即清除站存資料
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "insert into questionnaire(title,description, is_published, start_date, end_date)"
			//對應到SQL裡分類↑　//對到@Param裡的值↓
			+ " values(:title, :desp, :isPub, :startDate, :endDate)", nativeQuery = true)
	public int insert(//
			@Param("title") String title,//
			@Param("desp") String description,//
			@Param("isPub")boolean isPublished,//
			@Param("startDate")LocalDate startDate,//
			@Param("endDate")LocalDate endDate);
	
	@Modifying
	@Transactional
	@Query(value = "insert into questionnaire(title,description, is_published, start_date, end_date)"
			//對12345個代數位置
			+ " values(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	public int insertData(//
			String title,//
			String description,//
			boolean isPublished,//
			LocalDate startDate,//
			LocalDate endDate);
	//=========================
	//update
	@Query(value = "update questionnaire set title = :title, description = :desp"
			+ " where id = :id", nativeQuery = true)
			//where一定要寫，不然整個資料庫會全改
	public int update(
			@Param("id")int id,//
			@Param("title")String title,//
			@Param("desp")String description);	
	//
//	@Query(value = "select * from Questionnaire"
//			+ " where start_date > :startDate", nativeQuery = true)
//	public List<Questionnaire> findByStartDate(@Param("startDate")LocalDate startDate);
	// 
//	@Query(value = "select new Questionnaire(id, title, is_published,startDate,endDate) from Questionnaire"
//			+ " where start_date > :startDate")
//	public List<Questionnaire> findByStartDate1(@Param("startDate")LocalDate startDate);
	//nativeQuery = false, select 的欄位要使用建構方法的方式，且Entity中也要有對應的建構方法
//	@Query(value = "select new Questionnaire(id, title, is_published) from Questionnaire"
//			+ " where start_date > :startDate")
//	public List<Questionnaire> findByStartDate2(@Param("startDate")LocalDate startDate);
	//使用別名，語法as 別名
//	@Query(value = "select qu from Questionnaire as qu"
//			+ " where startDate > :startDate or published = :ispublished")
//	public List<Questionnaire> findByStartDate3(@Param("startDate")LocalDate startDate,//
//			@Param("isPublished")boolean published);
	//order by & limit(limit放後面) 要先排序完後才限制數量
//	@Query(value = "select qu from Questionnaire as qu"
//			+ " where startDate > :startDate or published = :ispublished order by id desc")
//	public List<Questionnaire> findByStartDate4(
//			@Param("startDate")LocalDate startDate,//
//			@Param("isPublished")boolean published);
	//order by + limit
	//1. limit 語法只能使用在 nativeQuery = true
	//2. limit 要放在語法的最後
//	@Query(value = "select qu from Questionnaire as qu"
//			+ " where startDate > :startDate or published = :ispublished order by id desc limit :num"
//			, nativeQuery = true)
//	public List<Questionnaire> findByStartDate5(
//			@Param("startDate")LocalDate startDate,//
//			@Param("isPublished")boolean published,
//			@Param("num")int limitNum);
	
	@Query(value = "select * from questionnaire"
			+ " limit :startIndex, :limitNum", nativeQuery = true)
	public List<Questionnaire> findWithLimitAndStartPostion(
	@Param ("startIndex") int startIndex,
	@Param("limitNum")int limitNum);
	
	@Query (value = "select * from questionnaire"
			+ " where title like %:title%", nativeQuery = true)
	public List<Questionnaire> searchTitleLink(@Param("title")String title);
	
	//regexp
	//regexp 只能用在 nativeQuery = true的時候
	@Query (value = "select * from questionnaire "
			+ " where description regexp :keyword1|:keyword2", nativeQuery = true)
	public List<Questionnaire> searchDescriptionContaining(
			@Param("keyword1")String keyword1,//
			@Param("keyword2")String keyword2);
	
	@Query (value = "select * from questionnaire "
			+ " where description regexp concat (:keyword1,'|',:keyword2)", nativeQuery = true)
	public List<Questionnaire> searchDescriptionContaining2(
			@Param("keyword1")String keyword1,//
			@Param("keyword2")String keyword2);
	
	//===========================================================
	//join
//	@Query("select new com.example.questtionnaire.vo.QnQuVo("
//			+ " qn.id, qn.title, qn.description, qn.published, qn.startDate, qn.endDate,"
//			+ " q.quId, q.qTitle, q.optionType, q.necessary, q.option)"
//			+ " from Questionnaire as qn join Question as q on qn.id = q.qnId")
//	public List<QnQuVo> selectJoinQnQu();
//	
//	@Query("select new com.example.questtionnaire.vo.QnQuVo("
//			+ " qn.id, qn.title, qn.description, qn.published, qn.startDate, qn.endDate,"
//			+ " q.quId, q.qTitle, q.optionType, q.necessary, q.option)"
//			+ " from Questionnaire as qn join Question as q on qn.id = q.qnId"
//			+ " where qn.title link %:title% and qn.startDate >=: 1971-01-01")
//	public List<QnQuVo> selectFuzzy(@Param("title")String title,@Param("startDate")LocalDate startDate,
//			@Param("endDate")LocalDate endDate);
	
}
