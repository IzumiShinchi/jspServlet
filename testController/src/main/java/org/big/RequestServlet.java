package org.big;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

	public RequestServlet() {
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
		// forward는 직접 운반(객체 같음) / redirect는 재하청(명령 받는 객체, 전달 하는 객체 다름)
		req.setAttribute("username", "Hong Gil Dong");
		req.setAttribute("useraddress", "Seoul");
		
		RequestDispatcher dis = req.getRequestDispatcher("response");
		dis.forward(req, resp);	//direct 연결-forward 방식
	}
}
