package org.big.service;

import org.big.dao.BoardDAO;
import org.big.entity.BoardDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardReplyuiService implements BoardService {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String num = req.getParameter("num");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.replyui(num);
		
		req.setAttribute("replyui", data);
		//"replyui"라는 이름으로 data를 저장!
	}
}
