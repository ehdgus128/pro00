package com.spring5.mypro00.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring5.mypro00.domain.MyBoardVO;

@Repository
public class MyBoardDAO {
	
	private SqlSession sqlSession ;
	
	//생성자를 통한 주입: 필드에 해당하는 매개변수를 가진 초기화 생성자를 정의 
	//@Autowired
	public MyBoardDAO(SqlSession sqlSession) {
		System.out.println("DAO 생성자를 통한 SqlSession 필드에 SqlSessionTemplate 빈 주입(의존성 주입)");
		this.sqlSession = sqlSession ;
	}
	
//	public MyBoardDAO() {
//		System.out.println("Setter를 통한 SqlSession 필드에 SqlSessionTemplate 빈 주입(의존성 주입)");
//	}
//	
//	@Autowired
//	public void setSqlSession(SqlSession sqlSession) {
//		System.out.println("Setter를 이용한 의존성 주입");
//		this.sqlSession = sqlSession ;
//	}
	
	//게시물 목록 조회
	public List<MyBoardVO> selectDAOMyBoardList() {
		
		return this.sqlSession.selectList("MyBoardDAOMapper.selectDAOMyBoardList") ;	
	
	}
	
	//특정 게시물 조회(by bno)
	public MyBoardVO selectDAOMyBoard(long bno) {
		return this.sqlSession.selectOne("MyBoardDAOMapper.selectDAOMyBoard", bno) ; 
	}
	
	//특정 게시물 등록
	public boolean insertDAOMyBoard(MyBoardVO board) {
		
		return this.sqlSession.insert("MyBoardDAOMapper.insertDAOMyBoard", board) == 1 ;
		
	}
	

}
