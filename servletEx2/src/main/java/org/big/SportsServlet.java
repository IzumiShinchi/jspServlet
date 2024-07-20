package org.big;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sports")
public class SportsServlet extends HttpServlet {

	public SportsServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] sports = req.getParameterValues("sports");
		String gender = req.getParameter("gender");
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		out.print("좋아하는 운동 : ");
        if (sports != null && sports.length > 0) {
            for (int i = 0; i < sports.length; i++) {
                out.print(sports[i]);
                if (i < sports.length - 1) {
                    out.print(", ");
                }
            }
        } else {
            out.print("선택된 운동 없음");
        }
        out.print("<br>");
		out.print("응답하신 성별 : " + gender);
		out.print("</body></html>");
	}
}
