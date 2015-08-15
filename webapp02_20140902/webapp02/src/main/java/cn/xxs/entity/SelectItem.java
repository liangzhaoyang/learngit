package cn.xxs.entity;

import java.io.Serializable;

public class SelectItem implements Serializable {
	private static final long serialVersionUID = 4968418070661818093L;
	int code;
	String name;
	String type;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
