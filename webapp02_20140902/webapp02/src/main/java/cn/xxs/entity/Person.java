package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;

public class Person implements Serializable{
	private static final long serialVersionUID = 4160978708561067850L;
	private int id;
	private int orgid;
	private int zhiwu;
	private String name;
	private int xingbie;
	private int minzu;
	private Date chushengnianyue;
	private int xueli;
	private int zhengzhimianmao;
	private String zhuanye;
	private int xingzhengjibie;
	private int jishuzhicheng;
	private String renmingdanwei;
	private int shifouzhuanzhi;
	private String jianrenqitazhiwu;
	private Date kaishiriqi;
	private String suoshubumen;
	private String danweimingcheng;
	private String tel;
	private String mobilenum;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	private int bianzhirenshu;
	private String xianrenzhuiwu;
	private String zhuanyejishuzhiwu;
	private Date canjiagongzuoshijian;
	private int shemidengji;
	private int yaohaibumengrenyuan;
	private int renyuanleixing;
	//private String minzu;
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
	public void setMinzu(int minzu){
		this.minzu=minzu;
		}
		public int getMinzu(){
			return minzu;
		}
	public void setChushengnianyue(Date chushengnianyue){
	this.chushengnianyue=chushengnianyue;
	}
	public Date getChushengnianyue(){
		return chushengnianyue;
	}
	public void setXueli(int xueli){
	this.xueli=xueli;
	}
	public int getXueli(){
		return xueli;
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
	public void setXingzhengjibie(int xingzhengjibie){
	this.xingzhengjibie=xingzhengjibie;
	}
	public int getXingzhengjibie(){
		return xingzhengjibie;
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
		public void setBianzhirenshu(int bianzhirenshu){
		this.bianzhirenshu=bianzhirenshu;
		}
		public int getBianzhirenshu(){
			return bianzhirenshu;
		}
		public void setXianrenzhuiwu(String xianrenzhuiwu){
			this.xianrenzhuiwu=xianrenzhuiwu;
			}
		public String getXianrenzhuiwu(){
			return xianrenzhuiwu;
			}
		public void setZhuanyejishuzhiwu(String zhuanyejishuzhiwu){
			this.zhuanyejishuzhiwu=zhuanyejishuzhiwu;
			}
			public String getZhuanyejishuzhiwu(){
				return zhuanyejishuzhiwu;
			}
		public void setCanjiagongzuoshijian(Date canjiagongzuoshijian){
			this.canjiagongzuoshijian=canjiagongzuoshijian;
			}
			public Date getCanjiagongzuoshijian(){
				return canjiagongzuoshijian;
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

				public String toString()
				{
					return ""+id+","+orgid+","+ zhiwu+","+name +","+ xingbie+","+minzu+","+ chushengnianyue+","+xueli +","+zhengzhimianmao+","+ zhuanye+","+xingzhengjibie +","+jishuzhicheng 
							+","+ renmingdanwei+","+shifouzhuanzhi +","+jianrenqitazhiwu +","+ kaishiriqi+","+suoshubumen+","+ danweimingcheng+","+tel +","+mobilenum+","+shemidengji+","+yaohaibumengrenyuan+","+renyuanleixing;
				}
}

