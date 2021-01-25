package com.home.dao;

import java.util.List;

import com.home.domain.GBoardBean;
import com.home.domain.PageBean;

public interface GBoardDAO {
	public List<GBoardBean> getGBoardList(PageBean pbBean);
	
	public Integer getGBoardCount();

}
