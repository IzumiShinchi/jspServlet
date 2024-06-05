package org.big;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface EmpCommand {

	public void execute(HttpServletRequest req, HttpServletResponse resp);
}
