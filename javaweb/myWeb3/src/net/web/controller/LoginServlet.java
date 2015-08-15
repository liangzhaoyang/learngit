package net.web.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.domain.User;
import net.factory.DaoFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username=request.getParameter("admin_name");
		String password=request.getParameter("password");
		User user=DaoFactory.getUserDaoInstance().GetUserByName_psw(username, password);
		//System.out.println(user.getUserid()+"--"+user.getUsername());
		int y=0;
		HttpSession session=request.getSession();
		if(user.getUsername()!=null){
			session.setAttribute("user", user);
			session.setAttribute("flag", "false");
			session.setAttribute("start_time", "2000-03-07 22:24:39");
			session.setAttribute("end_time", "2000-03-08 01:33:25");
			List<Double> list=Arrays.asList(0.05,0.7,0.1,0.05,0.05,0.05);
			session.setAttribute("weight", list);
			y=1;
		}else{
			y=0;
		}
		ObjectMapper mapper =new ObjectMapper();
		String jsonfromList;
		try {
			jsonfromList = mapper.writeValueAsString(y);
			//System.out.println(jsonfromList);
			response.setContentType("text/javascript");
			response.getWriter().print(jsonfromList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}


}
