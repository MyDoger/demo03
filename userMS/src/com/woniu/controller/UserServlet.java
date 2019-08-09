package com.woniu.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.Executor;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.woniu.dao.UserDAO;
import com.woniu.pojo.User;

public class UserServlet extends HttpServlet {
	UserDAO ud=new UserDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method=req.getParameter("method");
		if(method.equals("login")) {
			login(req,resp);
		}else if(method.equals("findAll")) {
			findAll(req,resp);
		}else if(method.equals("userDel")) {
			userDel(req,resp);
		}else if(method.equals("userGet")) {
			userGet(req,resp);
		}else if(method.equals("UserEdit")) {
			UserEdit(req,resp);
		}else if(method.equals("reg"));
			reg(req,resp);
	}

	private void reg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uname=req.getParameter("uname");
		String upwd=req.getParameter("upwd");
		User user=new User(null,uname,upwd);
		ud.save(user);
		resp.sendRedirect("user.do?method=findAll");
		
	}

	private void UserEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int uid = Integer.parseInt(req.getParameter("uid"));
		String uname=req.getParameter("uname");
		String upwd=req.getParameter("upwd");
		User user=new User(uid,uname,upwd);
		ud.update(user);
		resp.sendRedirect("user.do?method=findAll");
		
	}

	private void userGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int uid = Integer.parseInt(req.getParameter("uid"));
		User u = ud.findOne(uid);
		req.setAttribute("u", u);
		req.getRequestDispatcher("userEdit.jsp").forward(req, resp);
	}

	private void userDel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		int uid = Integer.parseInt(req.getParameter("uid"));
		
		ud.delete(uid);
		
		resp.sendRedirect("user.do?method=findAll");
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<User> userList = ud.findAll();
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("userList.jsp").forward(req, resp);
		
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String uname=req.getParameter("uname");
		String upwd=req.getParameter("upwd");
		User user=new User(null,uname,upwd);
		User loginUser=ud.login(user);
		if(loginUser!=null) {
			
			req.getSession().setAttribute("loginUser", loginUser);
			//获取客户端的cookie信息
			Cookie[] cookies=req.getCookies();
			for(Cookie c:cookies) {
				//判断该session是否存有sessionID
				if(c.getName().equals("JSESSIONID")) {
					
					//设置session失效时间，转成文件存储
					c.setMaxAge(60*60*24);
					//发给客户端
					resp.addCookie(c);
				}
			}
			
			
			resp.sendRedirect("user.do?method=findAll");
		}else {
			resp.sendRedirect("login.jsp?message=unameIsError!");
		}
		
	}


}
