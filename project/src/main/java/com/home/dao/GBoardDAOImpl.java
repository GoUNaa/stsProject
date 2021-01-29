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

	@Override
	public void insertGBoard(GBoardBean gb) {
		sqlSession.insert(namespace + ".insertGBoard",gb);
	}

	@Override
	public void updateReadCount(int num) {
		sqlSession.update(namespace + ".updateReadCount",num);
	}

	@Override
	public GBoardBean getGBoard(int num) {
		return sqlSession.selectOne(namespace + ".getGBoard",num);
	}

	@Override
	public Integer getMaxNum() {
		return sqlSession.selectOne(namespace + ".getMaxNum");
	}

	@Override
	public GBoardBean numCheck(GBoardBean gb) {
		return sqlSession.selectOne(namespace + ".numCheck",gb);
	}

	@Override
	public void updateGBoard(GBoardBean gb) {
		sqlSession.update(namespace + ".updateGBoard" , gb);
	}

	@Override
	public void deleteBoard(GBoardBean gb) {
		sqlSession.delete(namespace + ".deleteBoard",gb);
	}

	@Override
	public List<GBoardBean> listSearch(PageBean pbBean) {
		return sqlSession.selectList(namespace + ".listSearch",pbBean);
	}

	@Override
	public Integer SearchCount(PageBean pbBean) {
		return sqlSession.selectOne(namespace + ".SearchCount",pbBean);
	}
	
	
	
	
	
	
	
	
	
	
	
}
