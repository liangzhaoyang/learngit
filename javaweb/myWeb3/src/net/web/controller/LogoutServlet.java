package net.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//处理用户注销请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession(false);
		if(session!=null){
			session.removeAttribute("user");
		}
		request.setAttribute("message","注销成功！！<meta http-equiv='refresh' content='1;url="+request.getContextPath()+"/jsp/login.jsp'>");
		request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		
//		response.sendRedirect(request.getContextPath()+"/LoginServlet");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	

}
