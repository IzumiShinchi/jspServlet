package ch12.com.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class Authenfilter extends HttpFilter implements Filter {
    public Authenfilter() {
        super();
        
    }

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter01 초기화...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filter01.jsp 수행....");
		String name = request.getParameter("name");
		
		if(name==null || name.equals("")) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			String message = "입력된 name의 값은 null입니다.";
			writer.println(message);
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		System.out.println("Filter01 해제....");
	}

}
