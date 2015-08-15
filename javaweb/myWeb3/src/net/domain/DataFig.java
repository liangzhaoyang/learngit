package net.domain;

import java.sql.Timestamp;

public class DataFig {

	private int totalValue;
	private Timestamp timestamp;
	
	public DataFig() {
		super();
	}
	public DataFig(int totalValue, Timestamp timestamp) {
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
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp2) {
		this.timestamp = timestamp2;
	}
	
}
