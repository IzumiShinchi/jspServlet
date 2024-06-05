package org.big.service;

import java.util.ArrayList;

import org.big.dao.BoardDAO;
import org.big.entity.BoardDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardListService implements BoardService {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO> list = dao.list();
		req.setAttribute("list", list);
	}
}
