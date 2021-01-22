package com.home.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.home.domain.FBoardBean;
import com.home.domain.PageBean;

@Repository
public class FBoardDAOImpl implements FBoardDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.home.mapper.FBoardMapper";

	@Override
	public List<FBoardBean> getFBoardList(PageBean pbBean) {
		System.out.println("FBoardDAOImpl - getFBoardList");
		return sqlSession.selectList(namespace + ".getFBoardList", pbBean);
	}

	@Override
	public Integer getFBoardCount() {
		System.out.println("FBoardDAOImpl - getFBoardCount");
		return sqlSession.selectOne(namespace + ".getFBoardCount");
	}

	@Override
	public FBoardBean getFBoard(int num) {
		System.out.println("FBoardDAOImpl - getFBoard");
		return sqlSession.selectOne(namespace +".getFBoard",num );
	}

	@Override
	public FBoardBean numCheck(FBoardBean fb) {
		System.out.println("FBoardDAOImpl - numCheck");
		return sqlSession.selectOne(namespace + ".numCheck",fb);
	}

	@Override
	public void fupdateBoard(FBoardBean fb) {
		System.out.println("FBoardDAOImpl - fupdateBoard");
		sqlSession.update(namespace + ".fupateBoard",fb);
	}

	@Override
	public void insertFBoard(FBoardBean fb) {
		System.out.println("FBoardDAOImpl - insertFBoard");
		sqlSession.insert(namespace + ".insertFBoard",fb);
	}

	@Override
	public void updateReadcount(int num) {
		System.out.println("FBoardDAOImpl - updateReadcount");
		sqlSession.update(namespace + ".updateReadcount",num);

	}

	@Override
	public Integer getMaxNum() {
		System.out.println("FBoardDAOImpl - getMaxNum");
		return sqlSession.selectOne(namespace + ".getMaxNum");
	}
	
	
	
	
	
	
	
}
