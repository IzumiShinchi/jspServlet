package org.big;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cartSave")
public class CarSaveServlet extends HttpServlet {

	public CarSaveServlet() {
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
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		String product = req.getParameter("product");
		
		HttpSession session = req.getSession();
		
		ArrayList<String> list = (ArrayList<String>)session.getAttribute("product");
		
		if(list==null) {//list가 null이면, (아무 정보도 없으면)
			list = new ArrayList<String>();
			list.add(product);
			session.setAttribute("product", list);
		} else {
			list.add(product);
		}
		out.print("<html><body>");
		out.print("Product 추가<br>");
		out.print("<a href='/testController/cartBasket'>장바구니 보기</a>");
		out.print("</body></html>");
	}
}
