package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;

public class OrgnizationCondition implements Serializable{
	private static final long serialVersionUID = 5656711107975165585L;
	private int id;
	private String orgname;
	private int suorgid;
	private String suOrgName;
	private Date chenglishijianFrom;
	private int xingzhengjibie;
	private String jingfeilaiyuan;
	private short zhengfuxulie;
	private short jigouleibie;
	private int status;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	private short deleteflag;
	private int bianzhishu;
	private int renshu;
	private int zhuanzhiganbushu;
	private int xueshengshu;
	private byte[] jiuyeqingkuang;
	//
	private Date chenglishijianTo;
	
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setOrgname(String orgname){
	this.orgname=orgname;
	}
	public String getOrgname(){
		return orgname;
	}
	public void setSuorgid(int suorgid){
	this.suorgid=suorgid;
	}
	public int getSuorgid(){
		return suorgid;
	}
	
	public String getSuOrgName() {
		return suOrgName;
	}
	public void setSuOrgName(String suOrgName) {
		this.suOrgName = suOrgName;
	}
	public void setChenglishijianFrom(Date chenglishijianFrom){
	this.chenglishijianFrom=chenglishijianFrom;
	}
	public Date getChenglishijianFrom(){
		return chenglishijianFrom;
	}
	public void setXingzhengjibie(int xingzhengjibie){
	this.xingzhengjibie=xingzhengjibie;
	}
	public int getXingzhengjibie(){
		return xingzhengjibie;
	}
	public void setJingfeilaiyuan(String jingfeilaiyuan){
	this.jingfeilaiyuan=jingfeilaiyuan;
	}
	public String getJingfeilaiyuan(){
		return jingfeilaiyuan;
	}
	public void setZhengfuxulie(short zhengfuxulie){
	this.zhengfuxulie=zhengfuxulie;
	}
	public short getZhengfuxulie(){
		return zhengfuxulie;
	}
	public void setJigouleibie(short jigouleibie){
	this.jigouleibie=jigouleibie;
	}
	public short getJigouleibie(){
		return jigouleibie;
	}
	public void setStatus(int status){
	this.status=status;
	}
	public int getStatus(){
		return status;
	}
	public void setShenheren(String shenheren){
	this.shenheren=shenheren;
	}
	public String getShenheren(){
		return shenheren;
	}
	public void setTianbiaoren(String tianbiaoren){
	this.tianbiaoren=tianbiaoren;
	}
	public String getTianbiaoren(){
		return tianbiaoren;
	}
	public void setTianbiaoriqi(Date tianbiaoriqi){
	this.tianbiaoriqi=tianbiaoriqi;
	}
	public Date getTianbiaoriqi(){
		return tianbiaoriqi;
	}
	public void setDeleteflag(short deleteflag){
	this.deleteflag=deleteflag;
	}
	public short getDeleteflag(){
		return deleteflag;
	}
	public void setBianzhishu(int bianzhishu){
	this.bianzhishu=bianzhishu;
	}
	public int getBianzhishu(){
		return bianzhishu;
	}
	public void setRenshu(int renshu){
	this.renshu=renshu;
	}
	public int getRenshu(){
		return renshu;
	}
	public void setZhuanzhiganbushu(int zhuanzhiganbushu){
	this.zhuanzhiganbushu=zhuanzhiganbushu;
	}
	public int getZhuanzhiganbushu(){
		return zhuanzhiganbushu;
	}
	public void setXueshengshu(int xueshengshu){
	this.xueshengshu=xueshengshu;
	}
	public int getXueshengshu(){
		return xueshengshu;
	}
	public void setJiuyeqingkuang(byte[] jiuyeqingkuang){
	this.jiuyeqingkuang=jiuyeqingkuang;
	}
	public byte[] getJiuyeqingkuang(){
		return jiuyeqingkuang;
	}
	//
	public void setChenglishijianTo(Date chenglishijianTo){
		this.chenglishijianTo=chenglishijianTo;
	}
	public Date getChenglishijianTo(){
		return chenglishijianTo;
	}
}
