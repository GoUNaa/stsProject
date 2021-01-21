package com.home.sevice;

import java.util.List;

import com.home.domain.BoardBean;
import com.home.domain.PageBean;

public interface BoardService {
	
	// 글 리스트
	public List<BoardBean> getBoardList(PageBean pbBean);
	
	//글 갯수
	public Integer getBoardCount();
	
	// 조회수
	public void updateReadcount(int num);

	public BoardBean getBoard(int num);

	public void insertBoard(BoardBean bb);
	
	public void updateBoard(BoardBean bb);

    public BoardBean numCheck(BoardBean bb);

	public void deleteBoard(BoardBean bb);
	
	public void reInsertBoard(BoardBean bb);

	
	
	
	
}
