package cn.xxs.entity;

import java.io.Serializable;

public class ZaibianRenyuanMingxi implements Serializable{
	private static final long serialVersionUID = 3735381609935531774L;
	private int orgid;
	private int code;
	private int num;
	public void setOrgid(int orgid){
	this.orgid=orgid;
	}
	public int getOrgid(){
		return orgid;
	}
	public void setCode(int code){
	this.code=code;
	}
	public int getCode(){
		return code;
	}
	public void setNum(int num){
	this.num=num;
	}
	public int getNum(){
		return num;
	}
	
	public String toString()
	{
		return ""+code+","+num;
	}
}
