package org.big;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cartBasket")
public class CartBasketServlet extends HttpServlet {
	
	public CartBasketServlet() {
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
		
		
		out.print("<html><body>");
		out.print("장바구니 리스트!!! <br>");
		
		HttpSession session = req.getSession(false);
		
		if(session!=null) {
			ArrayList<String> list = (ArrayList<String>)session.getAttribute("product");
			out.print("상품 : " + list + "<br>");
		} else { //세션 생성하지 않음
			out.print("세션 없음!<br>");
		}

		out.print("<a href='Product.html'>상품 선택 페이지</a><br>");
		out.print("<a href='cartDelete'>장바구니 비우기</a><br>");
		out.print("</body></html>");
	}
}