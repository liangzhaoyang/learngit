package cn.xxs.entity;

import java.io.Serializable;
import java.util.List;

public class ResumeForImport implements Serializable {
	private static final long serialVersionUID = 819244349932278980L;

	private Person personInfo;
	private Resume resumeInfo;
	private Jiangcheng jiangchengInfo;
	private Chengguo chengguoInfo;
	private List<JiatingChengyuan> jiatingchengyuanInfo;
	private XueliMingxi quanrizhiXueli;
	private XueliMingxi zaizhiXueli;
	public Person getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(Person personInfo) {
		this.personInfo = personInfo;
	}
	public Resume getResumeInfo() {
		return resumeInfo;
	}
	public void setResumeInfo(Resume resumeInfo) {
		this.resumeInfo = resumeInfo;
	}
	public Jiangcheng getJiangchengInfo() {
		return jiangchengInfo;
	}
	public void setJiangchengInfo(Jiangcheng jiangchengInfo) {
		this.jiangchengInfo = jiangchengInfo;
	}
	public Chengguo getChengguoInfo() {
		return chengguoInfo;
	}
	public void setChengguoInfo(Chengguo chengguoInfo) {
		this.chengguoInfo = chengguoInfo;
	}
	public List<JiatingChengyuan> getJiatingchengyuanInfo() {
		return jiatingchengyuanInfo;
	}
	public void setJiatingchengyuanInfo(List<JiatingChengyuan> jiatingchengyuanInfo) {
		this.jiatingchengyuanInfo = jiatingchengyuanInfo;
	}
	public XueliMingxi getQuanrizhiXueli() {
		return quanrizhiXueli;
	}
	public void setQuanrizhiXueli(XueliMingxi quanrizhiXueli) {
		this.quanrizhiXueli = quanrizhiXueli;
	}
	public XueliMingxi getZaizhiXueli() {
		return zaizhiXueli;
	}
	public void setZaizhiXueli(XueliMingxi zaizhiXueli) {
		this.zaizhiXueli = zaizhiXueli;
	}
}
