package cn.xxs.entity;

import java.io.Serializable;

public class XingzhengJibieCount implements Serializable{
	private static final long serialVersionUID = -3236129093737497308L;
	private String xingzhengjibie;
	private int count;
	public void setXingzhengjibie(String xingzhengjibie)
	{
		this.xingzhengjibie = xingzhengjibie;
	}
	public String getXingzhengjibie()
	{
		return xingzhengjibie;
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
