package com.home.domain;

public class PageBean {
	private int count;
	private int pageSize;
	private String pageNum;
	private int currentPage;
	private int startRow;
	
	private int pageBlock;
	private int startPage;
	private int endPage;
	private int pageCount;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		// 페이지 관련 계산 메서드 호출 init()
		init();
	}
	public void init() {
		pageBlock=10;
		startPage=(currentPage-1)/pageBlock*pageBlock+1;
		
		pageCount = count/pageSize +(count%pageSize==0?0:1);
		
		endPage=startPage+pageBlock-1;
		 if(endPage > pageCount){
			 endPage=pageCount;
		 }
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
}
