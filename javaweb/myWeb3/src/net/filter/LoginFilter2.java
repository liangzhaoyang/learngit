package net.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.domain.User;

public class LoginFilter2 extends HttpFilter {

	//private String sessionKey;
	private String redirectUrl;
	//private String uncheckedUrls;
	
	@Override
	protected void init() {
		ServletContext servletContext=getFilterConfig().getServletContext();
		//sessionKey=servletContext.getInitParameter("userSessionKey");
		redirectUrl=servletContext.getInitParameter("redirectPage");
		//uncheckedUrls=servletContext.getInitParameter("uncheckedUrls");
	}
	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Object user=request.getSession().getAttribute("user");
		if(user==null){
			//response.sendRedirect(request.getContextPath()+redirectUrl);
			PrintWriter out = response.getWriter();
			String url=request.getContextPath()+redirectUrl;
			out.write("<script>window.parent.location.href='"+url+"';</script>");
			return;
		}else{
			User user2=(User)user;
			if(user2.getUsername()==null){
				//response.sendRedirect(request.getContextPath()+redirectUrl);
				PrintWriter out = response.getWriter();
				String url=request.getContextPath()+redirectUrl;
				out.write("<script>window.parent.location.href='"+url+"';</script>");
				return;
			}
		}
		chain.doFilter(request, response);
	}

}
