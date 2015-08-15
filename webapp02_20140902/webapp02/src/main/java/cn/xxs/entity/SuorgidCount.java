package cn.xxs.entity;

import java.io.Serializable;

public class SuorgidCount implements Serializable{
	private static final long serialVersionUID = -6430785250233958591L;
	private String orgname;
	private int count;
	public void setOrgname(String orgname)
	{
		this.orgname = orgname;
	}
	public String getOrgname()
	{
		return orgname;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public int getCount()
	{
		return count;
	}


}
