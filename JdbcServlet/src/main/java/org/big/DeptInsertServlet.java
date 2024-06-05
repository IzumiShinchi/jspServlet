package org.big;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deptInsert")
public class DeptInsertServlet extends HttpServlet {

	public DeptInsertServlet() {
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
		
		String deptno = req.getParameter("deptno");
		//입력이 어려워서 지금은 하지 않지만 숫자로 받을 거면, int deptno = Integer.parseInt(req.getParameter("deptno"));
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
		Statement stmt = null;
		//insert, update,delete는 작업 후 확인 안 되니까 안 쓰고, select만 사용 -> ResultSet rs = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();
			
			String query="INSERT INTO dept(deptno, dname, loc) VALUES " + "('" + deptno + "', '" + dname + "', '"+ loc + "')";
			
			int n = stmt.executeUpdate(query);
			
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
				if(stmt!=null) stmt.close();
				if(conn!=null) stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			} finally {
				
			}
		}
		
		out.print("</body></html>");
	}
}
