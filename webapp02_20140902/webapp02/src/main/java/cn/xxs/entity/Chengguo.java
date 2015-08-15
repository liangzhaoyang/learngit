package cn.xxs.entity;

import java.io.Serializable;

public class Chengguo implements Serializable{

	private static final long serialVersionUID = 2170300103193945108L;
	private int personid;
	private int id;
	private String content;
	public void setPersonid(int personid){
	this.personid=personid;
	}
	public int getPersonid(){
		return personid;
	}
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setContent(String content){
	this.content=content;
	}
	public String getContent(){
		return content;
	}
	public String toString()
	{
		return ""+content+""+personid+""+id;
	}
}
