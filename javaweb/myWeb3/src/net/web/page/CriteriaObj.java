package net.web.page;

import java.sql.Timestamp;

public class CriteriaObj {

	
	private String start_time = "2000-03-07 22:24:39";
	private String end_time = new Timestamp(System.currentTimeMillis()).toString();
	
	private int pageNo;

	

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public CriteriaObj(String start_time, String end_time, int pageNo) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.pageNo = pageNo;
	}
	
	
}
