package net.web.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dao.impl.NetFlowDAOImpl;
import net.domain.DataFig;
import net.domain.NetFlow;
import net.service.impl.NetFlowService;
import net.web.page.CriteriaObj;
import net.web.page.Page;

import com.fasterxml.jackson.databind.ObjectMapper;


public class NetFlowServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private NetFlowService netFlowService = new NetFlowService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String methodName = request.getParameter("method");
		
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	
	protected void getNetFlows(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNoStr = request.getParameter("pageNo");
		String start_timeStr = request.getParameter("start_time");
		String end_timeStr = request.getParameter("end_time");
	
		int pageNo = 1;
		String start_time = "2000-03-07 22:21:36";
		String end_time = new Timestamp(System.currentTimeMillis()).toString();
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		try {
			if(start_timeStr == null || start_timeStr == ""){
				
			}
			else{
				
				start_time = start_timeStr;
			}
			
		} catch (NumberFormatException e) {}
		
		try {
			//end_time = end_timeStr;
			if(end_timeStr == null || end_timeStr == ""){
				
			}
			else{
				
				end_time = end_timeStr;
			}
		} catch (NumberFormatException e) {}
		
		CriteriaObj criteriaNetFlow = new CriteriaObj(start_time, end_time, pageNo);
		Page<NetFlow> page = netFlowService.getPage(criteriaNetFlow);

		request.setAttribute("netFlowpage", page);
		request.setAttribute("start_time", start_time);
		request.setAttribute("end_time", end_time);
		request.getRequestDispatcher("/jsp/netFlows.jsp").forward(request, response);
	}
	
protected void getDataSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String start_timeStr = request.getParameter("start_time");
		String end_timeStr = request.getParameter("end_time");

		String start_time = "2000-03-07 22:21:36";
		String end_time = new Timestamp(System.currentTimeMillis()).toString();
		try {
			if(start_timeStr == null || start_timeStr == ""){
				
			}
			else{
				
				start_time = start_timeStr;
			}
			
		} catch (NumberFormatException e) {}
		
		try {
			if(end_timeStr == null || end_timeStr == ""){
				
			}
			else{
				
				end_time = end_timeStr;
			}
		} catch (NumberFormatException e) {}
		
		List<DataFig> dataFigList=new ArrayList<DataFig>();
		ObjectMapper mapper =new ObjectMapper();
		dataFigList=new NetFlowDAOImpl().getTotalNetFlowData(start_time, end_time);
		String jsonfromList=mapper.writeValueAsString(dataFigList);
		response.setContentType("text/javascript");
		response.getWriter().print(jsonfromList);
	}

@SuppressWarnings("rawtypes")
protected void getDataSet2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String start_timeStr =request.getParameter("start_time");
	String end_timeStr =request.getParameter("end_time");

	List list=new ArrayList();
	ObjectMapper mapper =new ObjectMapper();
	list=new NetFlowDAOImpl().getTotalNetFlowData2(start_timeStr, end_timeStr);

	String jsonfromList=mapper.writeValueAsString(list);
	
	response.setContentType("text/javascript");
	response.getWriter().print(jsonfromList);
}

protected void getDataSet3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String start_timeStr = request.getParameter("start_time");
	String end_timeStr = request.getParameter("end_time");

	@SuppressWarnings("rawtypes")
	List list=new ArrayList();
	list=new NetFlowDAOImpl().getTotalNetFlowData3(start_timeStr, end_timeStr);
	
	ObjectMapper mapper =new ObjectMapper();
	String jsonfromList=mapper.writeValueAsString(list);

	response.setContentType("text/javascript");
	response.getWriter().print(jsonfromList);
}

@SuppressWarnings("rawtypes")
protected void getDataSet4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String start_timeStr = request.getParameter("start_time");
	String end_timeStr = request.getParameter("end_time");

	//System.out.println(start_timeStr+"--"+end_timeStr);
	List list=new ArrayList();
	list=new NetFlowDAOImpl().getTotalNetFlowData4(start_timeStr, end_timeStr);
	
	ObjectMapper mapper =new ObjectMapper();
	String jsonfromList=mapper.writeValueAsString(list);
	//System.out.println(jsonfromList);
	response.setContentType("text/javascript");
	response.getWriter().print(jsonfromList);
}

}
