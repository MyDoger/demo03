package com.woniu.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.woniu.pojo.User;

public class LoginFilter implements Filter {

	String pass;
	String[] passLists;
	boolean isUse;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	//ÅÐ¶ÏÊÇ·ñ¿ÉÒÔµÇÂ½
	public boolean isPass(String method) {
		
		for(String s: passLists) {
			if(s.equals(method)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(isUse) {
			HttpServletRequest req=(HttpServletRequest)request;
			String reqStr = req.getQueryString();
			reqStr =reqStr .substring(reqStr .lastIndexOf("=")+1);
			HttpSession session = req.getSession();
			HttpServletResponse resp=(HttpServletResponse)response;
			User loginUser=(User)session.getAttribute("loginUser");
			if(loginUser!=null || isPass(reqStr)) {
				chain.doFilter(request, response);
			}else {
				resp.sendRedirect("login.jsp");
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		pass=filterConfig.getInitParameter("pass");
		passLists=pass.split(",");
		isUse=Boolean.parseBoolean(filterConfig.getInitParameter("isUse"));
	}

}
