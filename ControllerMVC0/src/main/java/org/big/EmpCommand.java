package org.big;

import java.net.http.HttpResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface EmpCommand {

	public void execute(HttpServletRequest req, HttpServletResponse resp);
}
