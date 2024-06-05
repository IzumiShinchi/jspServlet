package org.big;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/empInsert")
public class EmpInsertServlet extends HttpServlet {

	public EmpInsertServlet() {
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
		req.setCharacterEncoding("UTF-8");
		
		String empno = req.getParameter("empno");
		String ename = req.getParameter("ename");
		String job = req.getParameter("job");
		String mgr = req.getParameter("mgr");
		String hiredate = req.getParameter("hiredate");
		String sal = req.getParameter("sal");
		String comm = req.getParameter("comm");
		String deptno = req.getParameter("deptno");
		
		resp.setContentType("text/html; UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:oracle";
		String userid = "scott";
		String passwd = "tiger";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			
			String query = "INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empno);
			pstmt.setString(2, ename);
			pstmt.setString(3, job);
			pstmt.setString(4, mgr);
			pstmt.setString(5,hiredate);
			pstmt.setString(6, sal);
			pstmt.setString(7, comm);
			pstmt.setString(8, deptno);
			
			int n = pstmt.executeUpdate();
			
			if(n==1) {
				out.print("데이터를 정상적으로 입력했습니다.");
			} else {
				out.print("데이터를 저장하지 못했습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			} finally {
				
			}
		}
		
		out.print("</body></html>");
	}
}
