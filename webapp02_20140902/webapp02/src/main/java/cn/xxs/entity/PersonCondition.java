package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class PersonCondition implements Serializable{
	private static final long serialVersionUID = -897677222376397306L;
	private int id;
	private int orgid;
	private int zhiwu;
	private String name;
	private int xingbie;
	private Date chushengnianyueFrom;
	private int xueliFrom;
	private int zhengzhimianmao;
	private String zhuanye;
	private int xingzhengjibieFrom;
	private int jishuzhicheng;
	private String renmingdanwei;
	private int shifouzhuanzhi;
	private String jianrenqitazhiwu;
	private Date kaishiriqi;
	private String suoshubumen;
	private String danweimingcheng;
	private String tel;
	private String mobilenum;
	private List<Integer> ids;
	//
	private Date chushengnianyueTo;
	private int xueliTo;
	private int xingzhengjibieTo; 
	private String orderinfo;
	//
	private int minzu;
	private int shemidengji;
	private int yaohaibumengrenyuan;
	private int renyuanleixing;
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public void setShemidengji(int shemidengji){
		this.shemidengji=shemidengji;
		}
		public int getShemidengji(){
			return shemidengji;
		}
	public void setYaohaibumengrenyuan(int yaohaibumengrenyuan){
		this.yaohaibumengrenyuan=yaohaibumengrenyuan;
		}
		public int getYaohaibumengrenyuan(){
			return yaohaibumengrenyuan;
		}
	public void setRenyuanleixing(int renyuanleixing){
		this.renyuanleixing=renyuanleixing;
		}
		public int getRenyuanleixing(){
			return renyuanleixing;
		}
		
	public void setMinzu(int minzu){
		this.minzu=minzu;
		}
		public int getMinzu(){
			return minzu;
		}
		
	public void setChushengnianyueTo(Date chushengnianyueTo){
		this.chushengnianyueTo=chushengnianyueTo;
		}
	public Date getChushengnianyueTo(){
		return chushengnianyueTo;
	}
		
	public void setXueliTo(int xueliTo){
		this.xueliTo=xueliTo;
		}
	public int getXueliTo(){
		return xueliTo;
	}
			
	public void setXingzhengjibieTo(int xingzhengjibieTo){
		this.xingzhengjibieTo=xingzhengjibieTo;
		}
	public int getXingzhengjibieTo(){
		return xingzhengjibieTo;
	}
	
	public void setOrderinfo(String orderinfo){
		this.orderinfo=orderinfo;
		}
	public String getOrderinfo(){
		return orderinfo;
	}
		//
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setOrgid(int orgid){
	this.orgid=orgid;
	}
	public int getOrgid(){
		return orgid;
	}
	public void setZhiwu(int zhiwu){
	this.zhiwu=zhiwu;
	}
	public int getZhiwu(){
		return zhiwu;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setXingbie(int xingbie){
	this.xingbie=xingbie;
	}
	public int getXingbie(){
		return xingbie;
	}
	public void setChushengnianyueFrom(Date chushengnianyueFrom){
	this.chushengnianyueFrom=chushengnianyueFrom;
	}
	public Date getChushengnianyueFrom(){
		return chushengnianyueFrom;
	}
	public void setXueliFrom(int xueliFrom){
	this.xueliFrom=xueliFrom;
	}
	public int getXueliFrom(){
		return xueliFrom;
	}
	public void setZhengzhimianmao(int zhengzhimianmao){
	this.zhengzhimianmao=zhengzhimianmao;
	}
	public int getZhengzhimianmao(){
		return zhengzhimianmao;
	}
	public void setZhuanye(String zhuanye){
	this.zhuanye=zhuanye;
	}
	public String getZhuanye(){
		return zhuanye;
	}
	public void setXingzhengjibieFrom(int xingzhengjibieFrom){
	this.xingzhengjibieFrom=xingzhengjibieFrom;
	}
	public int getXingzhengjibieFrom(){
		return xingzhengjibieFrom;
	}
	public void setJishuzhicheng(int jishuzhicheng){
	this.jishuzhicheng=jishuzhicheng;
	}
	public int getJishuzhicheng(){
		return jishuzhicheng;
	}
	public void setRenmingdanwei(String renmingdanwei){
	this.renmingdanwei=renmingdanwei;
	}
	public String getRenmingdanwei(){
		return renmingdanwei;
	}
	public void setShifouzhuanzhi(int shifouzhuanzhi){
	this.shifouzhuanzhi=shifouzhuanzhi;
	}
	public int getShifouzhuanzhi(){
		return shifouzhuanzhi;
	}
	public void setJianrenqitazhiwu(String jianrenqitazhiwu){
	this.jianrenqitazhiwu=jianrenqitazhiwu;
	}
	public String getJianrenqitazhiwu(){
		return jianrenqitazhiwu;
	}
	public void setKaishiriqi(Date kaishiriqi){
	this.kaishiriqi=kaishiriqi;
	}
	public Date getKaishiriqi(){
		return kaishiriqi;
	}
	public void setSuoshubumen(String suoshubumen){
	this.suoshubumen=suoshubumen;
	}
	public String getSuoshubumen(){
		return suoshubumen;
	}
	public void setDanweimingcheng(String danweimingcheng){
	this.danweimingcheng=danweimingcheng;
	}
	public String getDanweimingcheng(){
		return danweimingcheng;
	}
	public void setTel(String tel){
	this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setMobilenum(String mobilenum){
	this.mobilenum=mobilenum;
	}
	public String getMobilenum(){
		return mobilenum;
	}
}
