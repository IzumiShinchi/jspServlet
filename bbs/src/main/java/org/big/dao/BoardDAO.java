package org.big.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.big.entity.BoardDTO;

import jakarta.servlet.jsp.tagext.TryCatchFinally;

public class BoardDAO {

	DataSource dataFactory;
	
	public BoardDAO() {
		// TODO Auto-generated constructor stub
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}
	
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') writeday, readcnt, reproot, repstep, repindent FROM BOARD ORDER BY reproot desc, repstep asc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");	//조회수
				int reproot = rs.getInt("reproot");	//메인 글의 참조 수
				int repstep = rs.getInt("repstep");	//답글의 답글 관계(들어쓰기)
				int repindent = rs.getInt("repindent");	//답글 관계에 따른 들여쓰기 수
				
				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				data.setReproot(reproot);
				data.setRepstep(repstep);
				data.setRepindent(repindent);
				
				list.add(data);
				//System.out.println(num + author + title + content + writeday + readcnt + reproot + repstep + repindent);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				
			}
		}
		return list;
	}
	
	public void write(String _title, String _author, String _content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "INSERT INTO board(num, title, author, content, reproot, repstep, repindent) VALUES (board_seq.nextval, ?, ?, ?, board_seq.currval, 0, 0)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _title);
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			
			int n = pstmt.executeUpdate(); //True, False
			System.out.println("저장 상태 확인(1:true, 0:false) : " + n);
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
	}
	
	public void readCount(String _num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE board SET readcnt=readcnt+1 WHERE num=" + _num;
			pstmt = conn.prepareStatement(query);
			
			int n = pstmt.executeUpdate();
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
	}
	
	public BoardDTO retrieve(String _num) {
		readCount(_num);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardDTO data = new BoardDTO();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM board WHERE num= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");	//조회수
				
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
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
		
		return data;
	}
	
	public void update(String _num, String _title, String _author, String _content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE board SET title=?, author=?, content=? WHERE num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _title);	//DB에 저장
			pstmt.setString(2, _author);
			pstmt.setString(3, _content);
			pstmt.setInt(4, Integer.parseInt(_num));	//DB에 저장하니까 String으로 받은 변수를 Int로 저장 
			
			int n = pstmt.executeUpdate();
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
	}
	
	public void delete(String _num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM board WHERE num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _num);
			
			int n = pstmt.executeUpdate();
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
	}
	
	public ArrayList<BoardDTO> search(String _searchName, String _searchValue) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') as writeday, readcnt FROM board";
			if(_searchName.equals("title")) {
				query+=" WHERE title LIKE ?";
			} else {
				query+=" WHERE author LIKE ?";
			}
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+_searchValue+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String author = rs.getString("author");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");	//조회수
				
				BoardDTO data = new BoardDTO();
				data.setNum(num);
				data.setAuthor(author);
				data.setTitle(title);
				data.setContent(content);
				data.setWriteday(writeday);
				data.setReadcnt(readcnt);
				
				list.add(data);
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
			}
		}
		
		return list;
	}
	
	public BoardDTO replyui(String _num) {
		BoardDTO data = new BoardDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM board WHERE num=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(_num));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setWriteday(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setReproot(rs.getInt("reproot"));
				data.setRepstep(rs.getInt("repstep"));
				data.setRepindent(rs.getInt("repindent"));
				
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
		return data;
	}
}
