package it.polimi.tiw.filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tinylog.Logger;


/**
 * Servlet Filter implementation class AdminChecker
 */

public class UserChecker implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String loginpath = req.getServletContext().getContextPath() + "/index.html";
		// check if the client is an admin
		HttpSession s = req.getSession();
		Logger.debug("User filter executing..");
	
		if (s.isNew() || s.getAttribute("user") == null) {
			res.setStatus(403);
			res.setHeader("Location", loginpath);
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
