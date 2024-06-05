package org.big;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

	public FrontController() {
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
		
		String requestURI = req.getRequestURI();	//전체 URI
		String contextPath = req.getContextPath();	//프로젝트 경로(이름)
		String command = requestURI.substring(contextPath.length());
		
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
			out.print("URI = " + requestURI + "<br>");
			out.print("Path = " + contextPath + "<br>");
			out.print("Command = " + command + "<br>");
			
			if(command.equals("/insert.do")) {
				out.print("Insert 요청입니다.");
			} else if (command.equals("/delete.do")) {
				out.print("Delete 요청입니다.");
			} else if (command.equals("update.do")) {
				out.print("Update 요청입니다.");
			} else {
				out.print("Select 요청입니다.");
			}
		out.print("</body></html>");
	}
}
