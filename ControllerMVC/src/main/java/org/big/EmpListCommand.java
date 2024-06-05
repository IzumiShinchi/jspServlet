package org.big;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmpListCommand implements EmpCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		EmpDAO dao=new EmpDAO();
		ArrayList<EmpDTO> list=dao.select();
		req.setAttribute("list", list);
	}
}
