package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class ResumeInfo implements Serializable
{
	private static final long serialVersionUID = -2959497908477370964L;
	private String name;
	private String orgid;
	private Date canjiagongzuoshijianFrom;
	private Date canjiagongzuoshijianTo;
	private String zhuanyejishuzhiwu;
	private String xianrenzhuiwu;
	private String orderinfo;
	private String id;
	private List<String> ids;
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public void setId(String id){
		this.id=id;
		}
		public String getId(){
			return id;
		}
	public void setName(String name){
		this.name=name;
		}
		public String getName(){
			return name;
		}
	public void setOrgid(String orgid){
	this.orgid=orgid;
	}
	public String getOrgid(){
		return orgid;
	}
	public void setCanjiagongzuoshijianFrom(Date canjiagongzuoshijianFrom){
	this.canjiagongzuoshijianFrom=canjiagongzuoshijianFrom;
	}
	public Date getCanjiagongzuoshijianFrom(){
		return canjiagongzuoshijianFrom;
	}
	public void setCanjiagongzuoshijianTo(Date canjiagongzuoshijianTo){
		this.canjiagongzuoshijianTo=canjiagongzuoshijianTo;
		}
		public Date getCanjiagongzuoshijianTo(){
			return canjiagongzuoshijianTo;
		}
	public void setZhuanyejishuzhiwu(String zhuanyejishuzhiwu){
	this.zhuanyejishuzhiwu=zhuanyejishuzhiwu;
	}
	public String getZhuanyejishuzhiwu(){
		return zhuanyejishuzhiwu;
	}
	public void setXianrenzhuiwu(String xianrenzhuiwu){
	this.xianrenzhuiwu=xianrenzhuiwu;
	}
	public String getXianrenzhuiwu(){
		return xianrenzhuiwu;
	}
	public void setOrderinfo(String orderinfo){
		this.orderinfo=orderinfo;
		}
	public String getOrderinfo(){
		return orderinfo;
	}

}
