package org.big;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deptDAOSelect")
public class DeptDAOServlet extends HttpServlet {
	
	public DeptDAOServlet() {
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
		resp.setContentType("text/html; charset=UTf-8");	//이미 DB에 있는 걸 가지고 오는거니까 Response
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptDTO> list = dao.select();
		
		for(DeptDTO dto : list) {
			int deptno = dto.getDeptno();
			String dname =  dto.getDname();
			String loc = dto.getLoc();
			
			out.print(deptno + "\t" + dname + "\t" + loc + "\t" + "<br");
		}
		
		out.print("</body></html>");
	}
}
