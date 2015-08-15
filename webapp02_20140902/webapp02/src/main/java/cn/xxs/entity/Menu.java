package cn.xxs.entity;

import java.io.Serializable;

public class Menu implements Serializable {
	private static final long serialVersionUID = 7431956092770951321L;
	
	private int id;
	private String firstName;
	private String secondName;
	private String entryPoint;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	
	
}
