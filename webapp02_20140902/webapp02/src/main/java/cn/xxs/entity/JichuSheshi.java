package cn.xxs.entity;

import java.io.Serializable;

   /**
    * jichusheshi 实体类
    * Fri Aug 01 14:00:40 CST 2014 leo
    */ 


public class JichuSheshi implements Serializable{
	private static final long serialVersionUID = 1091059633952349967L;
	private int id;
	private int orgid;
	private String name;
	private int leibie;
	private double mianji;
	private double touruzijin;
	private String jianshedanwei;
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setOrgid(int orgid){
	this.orgid=orgid;
	}
	public int getOrgid(){
		return orgid;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setLeibie(int leibie){
	this.leibie=leibie;
	}
	public int getLeibie(){
		return leibie;
	}
	public void setMianji(double mianji){
	this.mianji=mianji;
	}
	public double getMianji(){
		return mianji;
	}
	public void setTouruzijin(double touruzijin){
	this.touruzijin=touruzijin;
	}
	public double getTouruzijin(){
		return touruzijin;
	}
	public void setJianshedanwei(String jianshedanwei){
	this.jianshedanwei=jianshedanwei;
	}
	public String getJianshedanwei(){
		return jianshedanwei;
	}
	
	public String toString()
	{
		return ""+id+","+orgid+","+name+","+leibie+","+mianji+","+touruzijin+","+jianshedanwei;
	}
}

