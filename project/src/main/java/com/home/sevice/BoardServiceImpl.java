package com.home.sevice;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.home.dao.BoardDAO;
import com.home.domain.BoardBean;
import com.home.domain.PageBean;


@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO boardDAO;

	@Override
	public List<BoardBean> getBoardList(PageBean pbBean) {
		System.out.println("BoardServiceImpl - getBoardList");
		return boardDAO.getBoardList(pbBean);
	}
	
	@Override
	public Integer getBoardCount() {
		System.out.println("BoardServiceImpl - getBoardCount");
		return boardDAO.getBoardCount();
	}

	@Override
	public void updateReadcount(int num) {
		System.out.println("BoardServiceImpl - updateReadcount");
		boardDAO.updateReadcount(num);
	}

	@Override
	public BoardBean getBoard(int num) {
		System.out.println("BoardServiceImpl - getBoard");
		return boardDAO.getBoard(num);
	}

	@Override
	public void insertBoard(BoardBean bb) {
		System.out.println("BoardServiceImpl - insertBoard");
		// name , pass , subject , content 폼에서 담아옴
//		num,readcount,date,file,re_ref,re_lev,re_seq
		if(boardDAO.getMaxNum() == null) {
			bb.setNum(1);
			bb.setRe_ref(1);
		} else {
			bb.setNum(boardDAO.getMaxNum()+1); // 일반글번호(기준글) (num최대값 +1)
			bb.setRe_ref(boardDAO.getMaxNum()+1); // 그룹번호 일반글번호(기준글) 일치
		}
		
		bb.setReadcount(0); // 조회수 0
		bb.setDate(new Timestamp(System.currentTimeMillis())); // 현재 날짜
		
		bb.setRe_lev(0); // 답글 들여쓰기 0
		bb.setRe_seq(0); // 답글순서 0
		boardDAO.insertBoard(bb);
	}

	@Override
	public void updateBoard(BoardBean bb) {
		System.out.println("BoardServiceImpl - updateBoard");
		boardDAO.updateBoard(bb);
	}

	@Override
	public BoardBean numCheck(BoardBean bb) {
		System.out.println("BoardServiceImpl - numCheck");
		return boardDAO.numCheck(bb);
	}

	@Override
	public void deleteBoard(BoardBean bb) {
		System.out.println("BoardServiceImpl - deleteBoard");
		boardDAO.deleteBoard(bb);
	}
	
	@Override
	public void reInsertBoard(BoardBean bb) {
		// num re_Ref re_lev re_seq name pass subject content
		
		// max(num)+1
		bb.setNum(boardDAO.getMaxNum()+1);
		// 답글 순서 재배치 re_seq + 1
		boardDAO.updateRe_seq(bb);
		
		bb.setDate(new Timestamp(System.currentTimeMillis())); // 현재 날짜
		bb.setReadcount(0);
		bb.setRe_lev(bb.getRe_lev() + 1);
		bb.setRe_seq(bb.getRe_seq() + 1);
		
		boardDAO.insertBoard(bb);
	
	}

	@Override
	public List<BoardBean> listSearch(PageBean pbBean) {
		return boardDAO.listSearch(pbBean);
	}

	@Override
	public Integer getSearchCount(PageBean pbBean) {
		return boardDAO.getSearchCount(pbBean);
	}
	
	
}
