package net.domain;

import java.util.Date;


public class DataFig1 {

	private int totalValue;
	private Date timestamp;
	
	public DataFig1() {
		super();
	}
	public DataFig1(int totalValue, Date timestamp) {
		super();
		this.totalValue = totalValue;
		this.timestamp = timestamp;
	}
	public int getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp2) {
		this.timestamp = timestamp2;
	}
	 
	
}
