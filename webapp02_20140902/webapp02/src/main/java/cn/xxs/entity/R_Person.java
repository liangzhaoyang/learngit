package cn.xxs.entity;

import java.io.Serializable;

public class R_Person implements Serializable  {
	private static final long serialVersionUID = 8223795715482428549L;
	private int personid;
	private int orgId;
	public void setPersonid(int personid){
	this.personid=personid;
	}
	public int getPersonid(){
		return personid;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

}
