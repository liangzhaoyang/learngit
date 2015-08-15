package cn.xxs.entity;

import java.io.Serializable;

   /**
    * guanlijigou 实体类
    * Tue Aug 05 09:36:28 CST 2014 wan
    */ 


public class GuanliJigou  implements Serializable{
	private static final long serialVersionUID = 8704233365246562677L;
	private int id;
	private int orgid;
	private String name;
	private int bianzhirenshu;
	private int shijirenshu;
	private int xingzhengjibie;
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
	public void setBianzhirenshu(int bianzhirenshu){
	this.bianzhirenshu=bianzhirenshu;
	}
	public int getBianzhirenshu(){
		return bianzhirenshu;
	}
	public void setShijirenshu(int shijirenshu){
	this.shijirenshu=shijirenshu;
	}
	public int getShijirenshu(){
		return shijirenshu;
	}
	public void setXingzhengjibie(int xingzhengjibie){
	this.xingzhengjibie=xingzhengjibie;
	}
	public int getXingzhengjibie(){
		return xingzhengjibie;
	}
	
	public String toString()
	{
		return ""+id+","+orgid+","+name+","+bianzhirenshu+","+shijirenshu+","+xingzhengjibie;
	}
}

