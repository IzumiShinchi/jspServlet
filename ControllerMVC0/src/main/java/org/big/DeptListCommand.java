package org.big;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeptListCommand implements EmpCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		EmpDAO dao = new EmpDAO();
		ArrayList<DeptDTO> list1 = dao.list();
		
		req.setAttribute("list1", list1);
		
	}
	
	
}
