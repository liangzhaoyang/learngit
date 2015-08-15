package cn.xxs.entity;

import java.io.Serializable;

public class JigouLeibieCount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1251959971535537410L;
	private String jigouname;
	private int count;
	public void setJigouname(String jigouname)
	{
		this.jigouname = jigouname;
	}
	public String getJigouname()
	{
		return jigouname;
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
