package org.big;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deptInsert2")
public class DeptInsertServlet2 extends HttpServlet {

	public DeptInsertServlet2() {
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
		resp.setCharacterEncoding("UTF-8");
		
		int deptno = Integer.parseInt(req.getParameter("deptno"));
		String dname = req.getParameter("dname");
		String loc = req.getParameter("loc");
		
		resp.setContentType("text/html; UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:oracle";
		String userid = "scott";
		String passwd = "tiger";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			String query="INSERT INTO dept(deptno, dname, loc) VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			
			int n = pstmt.executeUpdate();
			
			if(n==1) {
				out.print("데이터 정상적으로 저장");
			} else {
				out.print("데이터 저장에 실패");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			} finally {
				
			}
		}
		
		out.print("</body></html>");
	}
}

