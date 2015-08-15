package net.web.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.dao.impl.AlarmDaoImpl;
import net.domain.Alarm;
import net.domain.DataFig;
import net.service.impl.AlarmService;
import net.web.page.CriteriaObj;
import net.web.page.Page;

@WebServlet("/AlarmServlet")
public class AlarmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AlarmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	private AlarmService alarmService = new AlarmService();
	protected void getAlarmEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNoStr = request.getParameter("pageNo");
		String start_timeStr = request.getParameter("start_time");
		String end_timeStr = request.getParameter("end_time");
	
		HttpSession session=request.getSession();
		int pageNo = 1;
		String start_time = (String) session.getAttribute("start_time");
		String end_time =(String) session.getAttribute("end_time");
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		try {
			if(start_timeStr == null || start_timeStr == ""){
				
			}
			else{
				
				start_time = start_timeStr;
				session.setAttribute("start_time", start_time);
			}
			
		} catch (NumberFormatException e) {}
		
		try {
			if(end_timeStr == null || end_timeStr == ""){
				
			}
			else{
				
				end_time = end_timeStr;
				session.setAttribute("end_time", end_time);
			}
		} catch (NumberFormatException e) {}
		
		CriteriaObj criteriaAlarm = new CriteriaObj(start_time, end_time, pageNo);
		Page<Alarm> page = alarmService.getPage(criteriaAlarm);
		request.setAttribute("alarmpage", page);
		request.setAttribute("start_time", start_time);
		request.setAttribute("end_time", end_time);
		request.getRequestDispatcher("/jsp/alarmQuery.jsp").forward(request, response);
	}
	
	@SuppressWarnings("rawtypes")
	protected void getSituationDataSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String start_time =(String) session.getAttribute("start_time");
		String end_time =(String) session.getAttribute("end_time");
		List<Double> weight=(List<Double>) session.getAttribute("weight");
		List list=new ArrayList();
		ObjectMapper mapper =new ObjectMapper();
		list=new AlarmDaoImpl().getSituationData(start_time, end_time,weight);
		String jsonfromList=mapper.writeValueAsString(list);
		
		response.setContentType("text/javascript");
		response.getWriter().print(jsonfromList);
	}
	
protected void getAlarmDataSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession session=request.getSession();
	String start_time =(String) session.getAttribute("start_time");
	String end_time =(String) session.getAttribute("end_time");

	List<DataFig> dataFigList=new ArrayList<DataFig>();
	
	ObjectMapper mapper =new ObjectMapper();
	dataFigList=alarmService.getDataSet(start_time, end_time);
	String jsonfromList=mapper.writeValueAsString(dataFigList);
	
	response.setContentType("text/javascript");
	response.getWriter().print(jsonfromList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
