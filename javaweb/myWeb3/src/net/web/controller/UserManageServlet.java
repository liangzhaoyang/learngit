package net.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.domain.User;
import net.factory.DaoFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserManageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		/*如果是进行信息的添加操作*/
		if(action.equals("add")) {	
			AddAdmin(request, response);
		} else if(action.equals("update")) {
			UpdateAdminInfo(request, response);
		} else if(action.equals("del")) {
			DeleteUserInfo(request,response);
		}else if(action.equals("check")){
			CheckByUsername(request,response);
		}else if(action.equals("check2")){
			CheckByEmail(request,response);
		}else if(action.equals("check_u")){
			CheckByName_Id(request,response);
		}else if(action.equals("check_u2")){
			CheckByEmail_Id(request,response);
		}else if(action.equals("ch_psw")){
			ChangePSW(request,response);
		}
	}

	private void ChangePSW(HttpServletRequest request,
			HttpServletResponse response) {
		String userid=request.getParameter("id");
		String password=request.getParameter("password");
		int row=DaoFactory.getUserDaoInstance().UpdatePSW(Integer.parseInt(userid),password);
		
		ObjectMapper mapper =new ObjectMapper();
		String jsonfromList;
		try {
			jsonfromList = mapper.writeValueAsString(row);
			response.setContentType("text/javascript");
			response.getWriter().print(jsonfromList);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void CheckByEmail_Id(HttpServletRequest request,
			HttpServletResponse response) {
		String userid=request.getParameter("userid");
		String email=request.getParameter("email");
		String str=DaoFactory.getUserDaoInstance().CheckByEmail_Id(Integer.parseInt(userid),email);
		ObjectMapper mapper =new ObjectMapper();
		String jsonfromList;
		try {
			jsonfromList = mapper.writeValueAsString(str);
			response.setContentType("text/javascript");
			response.getWriter().print(jsonfromList);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void CheckByName_Id(HttpServletRequest request,
			HttpServletResponse response) {
		String userid=request.getParameter("userid");
		String username=request.getParameter("admin_name");
		String str=DaoFactory.getUserDaoInstance().CheckByName_Id(Integer.parseInt(userid),username);
		ObjectMapper mapper =new ObjectMapper();
		String jsonfromList;
		try {
			jsonfromList = mapper.writeValueAsString(str);
			response.setContentType("text/javascript");
			response.getWriter().print(jsonfromList);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void CheckByEmail(HttpServletRequest request,
			HttpServletResponse response) {
		String email=request.getParameter("email");
		String str=DaoFactory.getUserDaoInstance().CheckByEmail(email);
		ObjectMapper mapper =new ObjectMapper();
		String jsonfromList;
		try {
			jsonfromList = mapper.writeValueAsString(str);
			response.setContentType("text/javascript");
			response.getWriter().print(jsonfromList);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private void CheckByUsername(HttpServletRequest request,
			HttpServletResponse response) {
		String name=request.getParameter("admin_name");
		//System.out.println(name);
		String str=DaoFactory.getUserDaoInstance().CheckUserByName(name);
		ObjectMapper mapper =new ObjectMapper();
		String jsonfromList;
		try {
			jsonfromList = mapper.writeValueAsString(str);
			response.setContentType("text/javascript");
			response.getWriter().print(jsonfromList);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doGet(request,response);
	}

	private void DeleteUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User admin=(User) request.getSession().getAttribute("user");

		String id = request.getParameter("id");

		if(admin.getPermission().equals("高级"))
		{
			if(DaoFactory.getUserDaoInstance().DeleteUserById(id) > 0){
				request.setAttribute("result", "管理员信息删除成功!");
				if(String.valueOf(admin.getUserid()).equals(id)){
					request.getSession().setAttribute("flag", "true");
					
				}
			} else {
				request.setAttribute("result", "管理员信息删除失败!");
			}
			
		}else{
			request.setAttribute("result", "您没有删除管理员信息的权限!");
			//request.setAttribute("flag", "false");
		}

		request.getRequestDispatcher("/jsp/tab.jsp").forward(request, response);
	}
	
	private void UpdateAdminInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User user=new User(Integer.parseInt(request.getParameter("id")),request.getParameter("name"),request.getParameter("password"),request.getParameter("permission"),request.getParameter("email"),request.getParameter("phone"));

		if(DaoFactory.getUserDaoInstance().UpdateUser(user)==1){
			request.setAttribute("result", "管理员信息更新成功!");
			
			request.getRequestDispatcher("/jsp/tab.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("message","管理员信息更新失败！！<meta http-equiv='refresh' content='1;url="+request.getContextPath()+"/jsp/updateAdmin.jsp'>");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}

	private void AddAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		user.setUsername(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setIphone(request.getParameter("phone"));
		user.setEmail(request.getParameter("email"));
		user.setPermission(request.getParameter("permission"));
		if(DaoFactory.getUserDaoInstance().AddUser(user)==1){
			
			request.setAttribute("result", "管理员信息添加成功!");
			request.getRequestDispatcher("/jsp/tab.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("message","管理员信息添加失败！！<meta http-equiv='refresh' content='2;url="+request.getContextPath()+"/jsp/addAdmin.jsp'>");
			request.getRequestDispatcher("/jsp/message.jsp").forward(request, response);
		}
	}
}
