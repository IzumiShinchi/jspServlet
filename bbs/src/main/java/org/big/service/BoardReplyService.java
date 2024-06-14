package org.big.service;

import org.big.dao.BoardDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardReplyService implements BoardService {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		String num = req.getParameter("num");
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		String reproot = req.getParameter("reproot");
		String repstep = req.getParameter("repstep");
		String repindent = req.getParameter("repindent");
		
		BoardDAO dao = new BoardDAO();
		dao.reply(num, title, author, content, reproot, repstep, repindent);
		
	}
}
