package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Log  implements Serializable{
	private static final long serialVersionUID = -5205126843582449026L;
	private Timestamp logtime;
	private short type;
	private String userid;
	private String descriptions;
	public void setLogtime(Timestamp logtime){
	this.logtime=logtime;
	}
	public Timestamp getLogtime(){
		return logtime;
	}
	public void setType(short type){
	this.type=type;
	}
	public short getType(){
		return type;
	}
	public void setUserid(String userid){
	this.userid=userid;
	}
	public String getUserid(){
		return userid;
	}
	
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	
	public String toString()
	{
		return ""+logtime+","+type+","+userid+","+descriptions;
	}
}

