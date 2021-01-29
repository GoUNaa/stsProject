package com.home.sevice;

import java.sql.Timestamp;
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

	@Override
	public void insertGBoard(GBoardBean gb) {
		if(gBoardDAO.getMaxNum() == null) {
			gb.setNum(1);
		} else {
			gb.setNum(gBoardDAO.getMaxNum()+1); // 일반글번호(기준글) (num최대값 +1)

		}
		gb.setDate(new Timestamp(System.currentTimeMillis())); // 현재 날짜
		gBoardDAO.insertGBoard(gb);
	}

	@Override
	public void updateReadCount(int num) {
		 gBoardDAO.updateReadCount(num);

	}

	@Override
	public GBoardBean getGBoard(int num) {
		return gBoardDAO.getGBoard(num);
	}

	@Override
	public GBoardBean numCheck(GBoardBean gb) {
		return gBoardDAO.numCheck(gb);
	}

	@Override
	public void updateGBoard(GBoardBean gb) {
		gBoardDAO.updateGBoard(gb);
	}

	@Override
	public void deleteBoard(GBoardBean gb) {
		gBoardDAO.deleteBoard(gb);
	}

	@Override
	public List<GBoardBean> listSearch(PageBean pbBean) {
		return gBoardDAO.listSearch(pbBean);
	}

	@Override
	public Integer SearchCount(PageBean pbBean) {
		return gBoardDAO.SearchCount(pbBean);
	}
	
	
	
}
