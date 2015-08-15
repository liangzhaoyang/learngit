package cn.xxs.entity;

import java.io.Serializable;

public class Jiangcheng implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4935711233222509435L;
	private int id;
	private int personid;
	private String content;
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
