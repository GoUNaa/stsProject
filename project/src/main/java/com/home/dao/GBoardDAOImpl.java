package com.home.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.home.domain.GBoardBean;
import com.home.domain.PageBean;

@Repository
public class GBoardDAOImpl implements GBoardDAO {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.home.mapper.GBoardMapper";
	
	@Override
	public List<GBoardBean> getGBoardList(PageBean pbBean) {
		return sqlSession.selectList(namespace + ".getGBoardList",pbBean);
	}

	@Override
	public Integer getGBoardCount() {
		return sqlSession.selectOne(namespace + ".getGBoardCount");
	}
	
	
	
	
	
	
	
	
	
	
	
}
