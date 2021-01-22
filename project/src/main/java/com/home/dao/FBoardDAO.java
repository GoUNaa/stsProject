package com.home.dao;

import java.util.List;

import com.home.domain.FBoardBean;
import com.home.domain.PageBean;

public interface FBoardDAO {
	
	public List<FBoardBean> getFBoardList(PageBean pbBean);

	public Integer getFBoardCount();
	
	public FBoardBean getFBoard(int num);
	
    public FBoardBean numCheck(FBoardBean fb);
	  
	public void fupdateBoard(FBoardBean fb);
	
	public void insertFBoard(FBoardBean fb);
	
	public void updateReadcount(int num);

	public Integer getMaxNum();

	
}
