package com.home.dao;

import java.util.List;

import com.home.domain.BoardBean;
import com.home.domain.PageBean;


public interface BoardDAO {
	
	public List<BoardBean> getBoardList(PageBean pbBean);

	public Integer getBoardCount();

	public void updateReadcount(int num);

	public BoardBean getBoard(int num);

	public void insertBoard(BoardBean bb);
	
	public void updateBoard(BoardBean bb);

    public BoardBean numCheck(BoardBean bb);

	public Integer getMaxNum();
	
	public void deleteBoard(BoardBean bb);
	
	public void updateRe_seq(BoardBean bb);

	public List<BoardBean> listSearch(PageBean pbBean);

	public Integer getSearchCount(PageBean pbBean);

	
	
}
