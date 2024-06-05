package org.big.service;

import org.big.dao.BoardDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardWriteService implements BoardService {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		dao.write(title, author, content);
	}
}
