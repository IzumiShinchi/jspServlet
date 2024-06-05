package org.big;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DeptDAO {

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url="jdbc:oracle:thin:@localhost:1521:oracle";
//	String userid="scott";
//	String passwd="tiger";
	
	DataSource dataFactory;
	
	public DeptDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle11");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public ArrayList<DeptDTO> select() {
		ArrayList<DeptDTO> list = new ArrayList<DeptDTO>();
		Connection conn =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//conn = DriverManager.getConnection(url, userid, passwd);
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM DEPT";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname =  rs.getString("dname");
				String loc = rs.getString("loc");
				
//				DeptDTO dto = new DeptDTO(deptno, dname, loc);
//				list.add(dto);
				
				DeptDTO dto = new DeptDTO();
				dto.setDeptno(deptno);
				dto.setDname(dname);
				dto.setLoc(loc);
				list.add(dto);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			} finally {
				
			}
		}
		
		return list;
	}
}
