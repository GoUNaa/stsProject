package com.home.dao;

import java.util.List;

import com.home.domain.GBoardBean;
import com.home.domain.PageBean;

public interface GBoardDAO {
	public List<GBoardBean> getGBoardList(PageBean pbBean);
	
	public Integer getGBoardCount();
	
	public void insertGBoard(GBoardBean gb);
	
	public void updateReadCount(int num);
	
	public GBoardBean getGBoard(int num);
	
	public Integer getMaxNum();
	
	public GBoardBean numCheck(GBoardBean gb);
	
	public void updateGBoard(GBoardBean gb);
	
	public void deleteBoard(GBoardBean gb);
	
	public List<GBoardBean> listSearch(PageBean pbBean);
	
	public Integer SearchCount(PageBean pbBean);
}
