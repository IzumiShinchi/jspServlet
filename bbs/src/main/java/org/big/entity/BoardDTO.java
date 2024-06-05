package org.big.entity;

public class BoardDTO {

	private int num;
	private String author;
	private String title;
	private String content;
	private String writeday;
	private int readcnt;	//조회수
	private int reproot;	//메인 글의 참조 수
	private int repstep;	//답글의 답글 관계(들어쓰기)
	private int repindent;	//답글 관계에 따른 들여쓰기 수
	
	public BoardDTO() {
		
	}
	
	public BoardDTO(int num, String author, String title, String content, String writeday, int readcnt, int reproot, int repstep, int repindent) {
		this.num = num;
		this.author = author;
		this.title = title;
		this.content = content;
		this.writeday = writeday;
		this.readcnt = readcnt;
		this.reproot = reproot;
		this.repstep = repstep;
		this.repindent = repindent;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteday() {
		return writeday;
	}

	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public int getReproot() {
		return reproot;
	}

	public void setReproot(int reproot) {
		this.reproot = reproot;
	}

	public int getRepstep() {
		return repstep;
	}

	public void setRepstep(int repstep) {
		this.repstep = repstep;
	}

	public int getRepindent() {
		return repindent;
	}

	public void setRepindent(int repindent) {
		this.repindent = repindent;
	}

	
}
