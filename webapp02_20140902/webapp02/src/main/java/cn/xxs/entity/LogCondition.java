package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 作者 :wan
      创建时间：2014年8月11日 下午1:31:36
 * 类说明
 */
public class LogCondition implements Serializable{
	private static final long serialVersionUID = 8583896752153851040L;
	private Timestamp logtimeFrom;
	private Timestamp logtimeTo;
	private short type;
	private String userid;
	private String desc;
	public void setLogtimeFrom(Timestamp logtimeFrom){
	this.logtimeFrom=logtimeFrom;
	}
	public Timestamp getLogtimeFrom(){
		return logtimeFrom;
	}
	//
	public void setLogtimeTo(Timestamp logtimeTo){
		this.logtimeTo=logtimeTo;
		}
		public Timestamp getLogtimeTo(){
			return logtimeTo;
		}
		//
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
	public void setDesc(String desc){
	this.desc=desc;
	}
	public String getDesc(){
		return desc;
	}
}
