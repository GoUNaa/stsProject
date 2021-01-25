package com.home.sevice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.home.dao.GBoardDAO;
import com.home.domain.GBoardBean;
import com.home.domain.PageBean;

@Service
public class GBoardServiceImpl implements GBoardService {
	@Inject
	private GBoardDAO gBoardDAO;

	@Override
	public List<GBoardBean> getGBoardList(PageBean pbBean) {
		System.out.println("갤러리게시판 겟보드리스트");
		return gBoardDAO.getGBoardList(pbBean);
	}

	@Override
	public Integer getGBoardCount() {
		return gBoardDAO.getGBoardCount();
	}
	
	
	
}
