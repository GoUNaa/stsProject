package com.home.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.home.domain.BoardBean;
import com.home.domain.PageBean;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.home.mapper.BoardMapper";

	@Override
	public List<BoardBean> getBoardList(PageBean pbBean) {
		System.out.println("BoardDAOImpl - getBoardList");
		return sqlSession.selectList(namespace + ".getBoardList",pbBean);
	} 

	@Override
	public Integer getBoardCount() {
		System.out.println("BoardDAOImpl - getBoardCount");
		return sqlSession.selectOne(namespace + ".getBoardCount");
	}

	@Override
	public void updateReadcount(int num) {
		System.out.println("BoardDAOImpl - updateReadcount");
		sqlSession.update(namespace + ".updateReadcount",num);
	}

	@Override
	public BoardBean getBoard(int num) {
		System.out.println("BoardDAOImpl - getBoard");
		return 	sqlSession.selectOne(namespace+".getBoard",num);
	}

	@Override
	public void insertBoard(BoardBean bb) {
		System.out.println("BoardDAOImpl - insertBoard");

		sqlSession.insert(namespace + ".insertBoard",bb);
	}

	@Override
	public void updateBoard(BoardBean bb) {
		System.out.println("BoardDAOImpl - updateBoard");

		sqlSession.update(namespace + ".updateBoard",bb);
	}

	@Override
	public BoardBean numCheck(BoardBean bb) {
		System.out.println("BoardDAOImpl - numCheck");
		return sqlSession.selectOne(namespace + ".numCheck",bb);
	}

	@Override
	public Integer getMaxNum() {
		System.out.println("BoardDAOImpl - getMaxNum");
		return sqlSession.selectOne(namespace + ".getMaxNum");
	}

	@Override
	public void deleteBoard(BoardBean bb) {
		System.out.println("BoardDAOImpl - deleteBoard");
		sqlSession.delete(namespace + ".deleteBoard",bb);
	}

	@Override
	public void updateRe_seq(BoardBean bb) {
		System.out.println("BoardDAOImpl - updateRe_seq");
		sqlSession.update(namespace + ".updateRe_seq" , bb);		
	}
	
	
}
