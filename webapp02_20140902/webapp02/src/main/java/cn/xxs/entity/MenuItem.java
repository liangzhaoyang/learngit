package cn.xxs.entity;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Serializable {
	private static final long serialVersionUID = 3602539689079895368L;
	private String name;
	private String entryPoint;
	private List<MenuItem> subItems;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntryPoint() {
		return entryPoint;
	}
	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}
	public List<MenuItem> getSubItems() {
		return subItems;
	}
	public void setSubItems(List<MenuItem> subItems) {
		this.subItems = subItems;
	}
}
