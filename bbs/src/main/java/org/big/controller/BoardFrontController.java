package org.big.controller;

import java.io.IOException;

import org.big.service.BoardDeleteService;
import org.big.service.BoardListService;
import org.big.service.BoardRetrieveService;
import org.big.service.BoardSearchService;
import org.big.service.BoardService;
import org.big.service.BoardUpdateService;
import org.big.service.BoardWriteService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	
	public BoardFrontController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String com = requestURI.substring(contextPath.length());
		
		BoardService service = null;
		String nextPage = null;
		
		if(com.equals("/list.do")) {
			service = new BoardListService();
			service.execute(req, resp);
			nextPage = "list.jsp";
		}
		if(com.equals("/writeui.do")) {
			nextPage = "write.jsp";
		}
		if(com.equals("/write.do")) {
			service = new BoardWriteService();
			service.execute(req, resp);
			nextPage = "list.do";
		}
		if(com.equals("/retrieve.do")) {
			service = new BoardRetrieveService();
			service.execute(req, resp);
			nextPage = "retrieve.jsp";
		}
		if(com.equals("/update.do")) {
			service = new BoardUpdateService();
			service.execute(req, resp);
			nextPage = "list.do";
		}
		if(com.equals("/delete.do")) {
			service = new BoardDeleteService();
			service.execute(req, resp);
			nextPage = "list.do";
		}
		if(com.equals("/search.do")) {
			service = new BoardSearchService();
			service.execute(req, resp);
			nextPage = "list.jsp";
		}
		RequestDispatcher dis = req.getRequestDispatcher(nextPage);
		dis.forward(req, resp);
	}
}