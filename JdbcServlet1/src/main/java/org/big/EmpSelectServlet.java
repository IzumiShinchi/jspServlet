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

@WebServlet("/empSelect")
public class EmpSelectServlet extends HttpServlet {

	public EmpSelectServlet() {
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
		
		PrintWriter out=resp.getWriter();
		out.print("<html><body>");
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:oracle";
		String userid="scott";
		String passwd="tiger";
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, userid, passwd);
			stmt=conn.createStatement();
			
			String query="SELECT * FROM emp";
			rs=stmt.executeQuery(query);
			
			while(rs.next()) {
				int empno=rs.getInt("empno");                                     
				String ename=rs.getString("ename");                                             
				String job=rs.getString("job");                                                
				int mgr=rs.getInt("mgr");                                               
				String hiredate=rs.getString("hiredate");                                          
				int sal=rs.getInt("sal");                                                
				int comm=rs.getInt("comm");                                               
				int deptno=rs.getInt("deptno");
				out.print(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate+"\t"+sal+"\t"+comm+"\t"+deptno+"<br>");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		out.print("</body></html>");
	}
}












