package org.big;

import java.io.IOException;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/postPre")
public class PostPreServlet extends HttpServlet {

	public PostPreServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet 실행!!");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy 실행!!!");
	}
	
	@PostConstruct
	public void initMethod() {
		System.out.println("initMethod 실행!");
	}
	
	@PreDestroy
	public void clean() {
		System.out.println("clean 실행!!!!");
	}
	
}
