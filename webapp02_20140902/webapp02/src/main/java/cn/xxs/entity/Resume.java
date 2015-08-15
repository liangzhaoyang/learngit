package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;



   /**
    * resume 实体类
    * Fri Aug 01 13:46:20 CST 2014 leo
    */ 


public class Resume implements Serializable{
	private static final long serialVersionUID = 4530679527229240474L;
	private int orgid;
	private int id;
	private int personid;
	private String zhaopian;
	private Date canjiagongzuoshijian;
	private String zhuanyejishuzhiwu;
	private String zhuanchang;
	private String xianrenzhuiwu;
	private String jianli;
	private String beizhu;
	public void setOrgid(int orgid){
		this.orgid=orgid;
		}
	public int getOrgid(){
		return orgid;
	}
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setPersonid(int personid){
	this.personid=personid;
	}
	public int getPersonid(){
		return personid;
	}
	public void setZhaopian(String zhaopian){
	this.zhaopian=zhaopian;
	}
	public String getZhaopian(){
		return zhaopian;
	}
	public void setCanjiagongzuoshijian(Date date){
	this.canjiagongzuoshijian=date;
	}
	public Date getCanjiagongzuoshijian(){
		return canjiagongzuoshijian;
	}
	public void setZhuanyejishuzhiwu(String zhuanyejishuzhiwu){
	this.zhuanyejishuzhiwu=zhuanyejishuzhiwu;
	}
	public String getZhuanyejishuzhiwu(){
		return zhuanyejishuzhiwu;
	}
	public void setZhuanchang(String zhuanchang){
	this.zhuanchang=zhuanchang;
	}
	public String getZhuanchang(){
		return zhuanchang;
	}
	public void setXianrenzhuiwu(String xianrenzhuiwu){
	this.xianrenzhuiwu=xianrenzhuiwu;
	}
	public String getXianrenzhuiwu(){
		return xianrenzhuiwu;
	}
	public void setJianli(String jianli){
	this.jianli=jianli;
	}
	public String getJianli(){
		return jianli;
	}
	public void setBeizhu(String beizhu){
	this.beizhu=beizhu;
	}
	public String getBeizhu(){
		return beizhu;
	}
	public String toString()
	{
		return ""+id+","+personid+","+orgid+","+zhaopian+","+canjiagongzuoshijian+","+zhuanyejishuzhiwu+","+zhuanchang
				+","+xianrenzhuiwu+","+jianli+","+beizhu;
	}
}

