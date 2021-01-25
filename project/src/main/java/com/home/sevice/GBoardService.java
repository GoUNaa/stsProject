package com.home.sevice;

import java.util.List;

import com.home.domain.GBoardBean;
import com.home.domain.PageBean;

public interface GBoardService {
	public List<GBoardBean> getGBoardList(PageBean pbBean);
	
	public Integer getGBoardCount();

}
