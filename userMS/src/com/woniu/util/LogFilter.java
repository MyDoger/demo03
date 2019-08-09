package com.woniu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.woniu.pojo.User;

public class LogFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request;
		StringBuffer reqStr = req.getRequestURL();
	
		HttpSession session = req.getSession();
		HttpServletResponse resp=(HttpServletResponse)response;
		User loginUser=(User)session.getAttribute("loginUser");

		PrintWriter pw = new PrintWriter(new FileWriter(new File("d:/logs.txt")), true);
		
		String line="---------------------------------";
		String state= loginUser==null?"登陆前":"登陆后";
		String user= loginUser==null?"游客":loginUser.getUname();
		String now=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms").format(new Date());
		String url="访问："+req.getRequestURL()+(req.getQueryString()==null?"":"?"+req.getQueryString());
		
		pw.println(line);
		pw.println(state);
		pw.println(user);
		pw.println(now);
		pw.println(url);
		pw.println(line);
		pw.println();
		pw.flush();
		pw.close();
		 	 chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
