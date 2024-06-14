package org.big.entity;

import java.util.ArrayList;

public class PageTO {
	
	ArrayList<BoardDTO> list;
	private int curPage;//현재 페이지
	private int perPage=10;//페이지 당 노출 게시물 수
	private int totalCount;//전체 게시물 수
	
	public ArrayList<BoardDTO> getList() {
		return this.list;
	}
	
	public void setList(ArrayList<BoardDTO> list) {
		this.list = list;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
