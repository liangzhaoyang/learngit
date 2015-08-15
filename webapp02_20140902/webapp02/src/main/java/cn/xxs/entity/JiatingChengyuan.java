package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;

public class JiatingChengyuan implements Serializable{

	private static final long serialVersionUID = -1147987310498265619L;
	private int personid;
	private int id;
	private String  chengwei;
	private String xingming;
	private Date chushengnianfen;
	private String zhengzhimianmao;
	private String gongzuodanweijizhiwu;
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
	public void setChengwei(String chengwei){
	this.chengwei=chengwei;
	}
	public String getChengwei(){
		return chengwei;
	}
	public void setXingming(String xingming){
	this.xingming=xingming;
	}
	public String getXingming(){
		return xingming;
	}
	public void setChushengnianfen(Date chushengnianfen){
	this.chushengnianfen=chushengnianfen;
	}
	public Date getChushengnianfen(){
		return chushengnianfen;
	}
	public void setZhengzhimianmao(String zhengzhimianmao){
	this.zhengzhimianmao=zhengzhimianmao;
	}
	public String getZhengzhimianmao(){
		return zhengzhimianmao;
	}
	public void setGongzuodanweijizhiwu(String gongzuodanweijizhiwu){
	this.gongzuodanweijizhiwu=gongzuodanweijizhiwu;
	}
	public String getGongzuodanweijizhiwu(){
		return gongzuodanweijizhiwu;
	}
	public String toString()
	{
		return ""+id+","+personid+","+chengwei+","+xingming+","+chushengnianfen+","+zhengzhimianmao+","+gongzuodanweijizhiwu;
	}
}
