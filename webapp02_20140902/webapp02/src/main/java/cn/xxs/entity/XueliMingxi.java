package cn.xxs.entity;

import java.io.Serializable;

public class XueliMingxi implements Serializable{
	private static final long serialVersionUID = 5024052055605558041L;
	private int id;
	private int personid;
	private int leibie;
	private String biyeyuanxiao;
	private String zhuanye;
	private int xueli;
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
	public void setLeibie(int leibie){
	this.leibie=leibie;
	}
	public int getLeibie(){
		return leibie;
	}
	public void setBiyeyuanxiao(String biyeyuanxiao){
	this.biyeyuanxiao=biyeyuanxiao;
	}
	public String getBiyeyuanxiao(){
		return biyeyuanxiao;
	}
	public void setZhuanye(String zhuanye){
	this.zhuanye=zhuanye;
	}
	public String getZhuanye(){
		return zhuanye;
	}
	
	public int getXueli() {
		return xueli;
	}
	public void setXueli(int xueli) {
		this.xueli = xueli;
	}
	public String toString()
	{
		return ""+id+","+personid+","+leibie+","+biyeyuanxiao+","+zhuanye;
	}
}
