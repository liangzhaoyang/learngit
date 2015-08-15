package cn.xxs.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 4922896569826555267L; 
	private String id;
	private String name;
	private String password;
	private Integer orgid;
	private int status;
	private boolean deleteflag;
	private boolean firsttime;
	private List<Integer> roles;
	private String orgName;
	private String suOrgName;
	private String oldPassword;
	private String passwordConfirm;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getOrgid() {
		return orgid;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}
	public boolean isFirsttime() {
		return firsttime;
	}
	public void setFirsttime(boolean firsttime) {
		this.firsttime = firsttime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSuOrgName() {
		return suOrgName;
	}
	public void setSuOrgName(String suOrgName) {
		this.suOrgName = suOrgName;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
