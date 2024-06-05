package org.big.service;

import java.util.ArrayList;

import org.big.dao.BoardDAO;
import org.big.entity.BoardDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardSearchService implements BoardService {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String searchName = req.getParameter("searchName");
		String searchValue = req.getParameter("searchValue");
		
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.search(searchName, searchValue);
		
		req.setAttribute("list", list);
	}
}
