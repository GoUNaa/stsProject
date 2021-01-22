package com.home.sevice;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.home.dao.FBoardDAO;
import com.home.domain.FBoardBean;
import com.home.domain.PageBean;

@Service
public class FBoardServiceImpl implements FBoardService {
	
	@Inject
	private FBoardDAO fBoardDAO;

	@Override
	public List<FBoardBean> getFBoardList(PageBean pbBean) {
		System.out.println("FBoardServiceImpl - getFBoardList");
		return fBoardDAO.getFBoardList(pbBean);
	}

	@Override
	public Integer getFBoardCount() {
		System.out.println("FBoardServiceImpl - getBoardCount");
		return fBoardDAO.getFBoardCount();
	}

	@Override
	public FBoardBean getFBoard(int num) {
		System.out.println("FBoardServiceImpl - getFBoard");
		return fBoardDAO.getFBoard(num);
	}

	@Override
	public FBoardBean numCheck(FBoardBean fb) {
		System.out.println("FBoardServiceImpl - numCheck");
		return fBoardDAO.numCheck(fb);
	}

	@Override
	public void fupdateBoard(FBoardBean fb) {
		System.out.println("FBoardServiceImpl - fupdateBoard");
		fBoardDAO.fupdateBoard(fb);
	}

	@Override
	public void insertFBoard(FBoardBean fb) {
		System.out.println("FBoardServiceImpl - insertFBoard");
		if(fBoardDAO.getMaxNum() == null) {
			fb.setNum(1);
		} else {
			fb.setNum(fBoardDAO.getMaxNum()+1); // 일반글번호(기준글) (num최대값 +1)

		}
		fb.setDate(new Timestamp(System.currentTimeMillis())); // 현재 날짜

		fBoardDAO.insertFBoard(fb);

	}

	@Override
	public void updateReadcount(int num) {
		System.out.println("FBoardServiceImpl - updateReadcount");
		fBoardDAO.updateReadcount(num);
	}

	
	
	
}
