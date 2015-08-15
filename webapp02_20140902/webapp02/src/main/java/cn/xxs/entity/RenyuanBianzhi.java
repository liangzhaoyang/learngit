package cn.xxs.entity;

import java.sql.Date;

   /**
    * renyuanbianzhi 实体类
    * Fri Aug 01 14:01:16 CST 2014 leo
    */ 


public class RenyuanBianzhi{
	private int orgid;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	public void setOrgid(int orgid){
	this.orgid=orgid;
	}
	public int getOrgid(){
		return orgid;
	}
	public void setShenheren(String shenheren){
	this.shenheren=shenheren;
	}
	public String getShenheren(){
		return shenheren;
	}
	public void setTianbiaoren(String tianbiaoren){
	this.tianbiaoren=tianbiaoren;
	}
	public String getTianbiaoren(){
		return tianbiaoren;
	}
	public void setTianbiaoriqi(Date tianbiaoriqi){
	this.tianbiaoriqi=tianbiaoriqi;
	}
	public Date getTianbiaoriqi(){
		return tianbiaoriqi;
	}
}

