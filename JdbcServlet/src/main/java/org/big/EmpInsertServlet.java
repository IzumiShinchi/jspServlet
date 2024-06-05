package org.big;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
		Statement stmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();
			
			String query = "INSERT INTO emp VALUES ('" + empno + "', '" + ename + "', '" + job +  "', '" + mgr + "' , '" + hiredate + "', '" + sal + "', '" + comm + "', '" + deptno + "')";
			
			int n = stmt.executeUpdate(query);
			
			if (n==1) {
				out.print("데이터 저장 정상적으로 완료");
			} else {
				out.print("데이터를 저장하지 못하였습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null) stmt.close();
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
