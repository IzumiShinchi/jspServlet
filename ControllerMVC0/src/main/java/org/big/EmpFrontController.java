package org.big;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/*.do")
public class EmpFrontController extends HttpServlet {

	public EmpFrontController() {
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
		System.out.println("requestURI = " + requestURI);
		String contextPath = req.getContextPath();
		System.out.println("contextPath = " + contextPath);
		String com = requestURI.substring(contextPath.length());
		System.out.println("com = " + com);
		
		EmpCommand command = null;
		String nextPage = null;
		
		if(com.equals("/list.do")) {
			command = new EmpListCommand();
			command.execute(req, resp);
			nextPage = "list.jsp";
		}
	}
}
