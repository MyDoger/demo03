package com.woniu.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo implements Filter {
	
	String encode;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("in destory..");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("in doFilter..");
		//给请求、响应对应设置编码格式
		arg0.setCharacterEncoding(encode);
		arg1.setCharacterEncoding(encode);
		System.out.println("编码格式已设置");
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		encode = arg0.getInitParameter("encode");
		System.out.println("in init..");
	}

}
