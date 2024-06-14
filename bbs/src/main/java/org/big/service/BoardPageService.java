package org.big.service;

import org.big.dao.BoardDAO;
import org.big.entity.PageTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardPageService implements BoardService {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	
		int curPage = 1;
		
		if(req.getParameter("curPage")!=null) {
			curPage = Integer.parseInt(req.getParameter("curPage"));
		}
		
		BoardDAO dao = new BoardDAO();
		PageTO to = dao.page(curPage);
		
		req.setAttribute("list", to.getList());	//"list"는 리스트가 필요해서 리스트로
		req.setAttribute("page", to);			//"page"는 페이지 정보만 있으면 되니까 리스트 필요 없음
	}
}
