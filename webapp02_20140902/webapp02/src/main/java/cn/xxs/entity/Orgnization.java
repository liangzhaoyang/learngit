package cn.xxs.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Orgnization implements Serializable {
	private static final long serialVersionUID = 2372317505913896677L;
	private int id;
	private String orgname;
	private Integer suorgid;
	private String suOrgName;
	private Date chenglishijian;
	private int xingzhengjibie;
	private String jingfeilaiyuan;
	private int zhengfuxulie;
	private int jigouleibie;
	private int status;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	private short deleteflag;
	private int bianzhishu;
	private int renshu;
	private int zhuanzhiganbushu;
	private int xueshengshu;
	private String jiuyeqingkuang;
	//
	private int shengfen;
	private int jigoufenlei2;
	private int baomiganbubianzhirenshu;
	private int jigoufenlei1;
	private String chenglishijianString;
	private String tianbiaoriqiString;

	public void setShengfen(int shengfen) {
		this.shengfen = shengfen;
	}

	public int getShengfen() {
		return shengfen;
	}

	public void setJigoufenlei1(int jigoufenlei1) {
		this.jigoufenlei1 = jigoufenlei1;
	}

	public int getJigoufenlei1() {
		return jigoufenlei1;
	}

	public void setJigoufenlei2(int jigoufenlei2) {
		this.jigoufenlei2 = jigoufenlei2;
	}

	public int getJigoufenlei2() {
		return jigoufenlei2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setSuorgid(Integer suorgid) {
		this.suorgid = suorgid;
	}

	public Integer getSuorgid() {
		return suorgid;
	}

	public String getSuOrgName() {
		return suOrgName;
	}

	public void setSuOrgName(String suOrgName) {
		this.suOrgName = suOrgName;
	}

	public void setChenglishijian(Date chenglishijian) {
		this.chenglishijian = chenglishijian;
		chenglishijianString = new SimpleDateFormat("YYYY-MM-dd").format(chenglishijian);
	}

	public Date getChenglishijian() {
		return chenglishijian;
	}

	public void setXingzhengjibie(int xingzhengjibie) {
		this.xingzhengjibie = xingzhengjibie;
	}

	public int getXingzhengjibie() {
		return xingzhengjibie;
	}

	public void setJingfeilaiyuan(String jingfeilaiyuan) {
		this.jingfeilaiyuan = jingfeilaiyuan;
	}

	public String getJingfeilaiyuan() {
		return jingfeilaiyuan;
	}

	public void setZhengfuxulie(int zhengfuxulie) {
		this.zhengfuxulie = zhengfuxulie;
	}

	public int getZhengfuxulie() {
		return zhengfuxulie;
	}

	public void setJigouleibie(int jigouleibie) {
		this.jigouleibie = jigouleibie;
	}

	public int getJigouleibie() {
		return jigouleibie;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setShenheren(String shenheren) {
		this.shenheren = shenheren;
	}

	public String getShenheren() {
		return shenheren;
	}

	public void setTianbiaoren(String tianbiaoren) {
		this.tianbiaoren = tianbiaoren;
	}

	public String getTianbiaoren() {
		return tianbiaoren;
	}

	public void setTianbiaoriqi(Date tianbiaoriqi) {
		this.tianbiaoriqi = tianbiaoriqi;
		tianbiaoriqiString = new SimpleDateFormat("YYYY-MM-dd").format(tianbiaoriqi);
	}

	public Date getTianbiaoriqi() {
		return tianbiaoriqi;
	}

	public void setDeleteflag(short deleteflag) {
		this.deleteflag = deleteflag;
	}

	public short getDeleteflag() {
		return deleteflag;
	}

	public void setBianzhishu(int bianzhishu) {
		this.bianzhishu = bianzhishu;
	}

	public int getBianzhishu() {
		return bianzhishu;
	}

	public void setRenshu(int renshu) {
		this.renshu = renshu;
	}

	public int getRenshu() {
		return renshu;
	}

	public void setZhuanzhiganbushu(int zhuanzhiganbushu) {
		this.zhuanzhiganbushu = zhuanzhiganbushu;
	}

	public int getZhuanzhiganbushu() {
		return zhuanzhiganbushu;
	}

	public void setXueshengshu(int xueshengshu) {
		this.xueshengshu = xueshengshu;
	}

	public int getXueshengshu() {
		return xueshengshu;
	}

	public void setJiuyeqingkuang(String jiuyeqingkuang) {
		this.jiuyeqingkuang = jiuyeqingkuang;
	}

	public String getJiuyeqingkuang() {
		return jiuyeqingkuang;
	}

	// public Integer getJgfl1() {
	// return jgfl1;
	// }
	// public void setJgfl1(Integer jgfl1) {
	// this.jgfl1 = jgfl1;
	// }
	// public Integer getJgfl2() {
	// return jgfl2;
	// }
	// public void setJgfl2(Integer jgfl2) {
	// this.jgfl2 = jgfl2;
	// }

	private List<XingzhengBumen> xingzhengBumenLst;
	private List<GuanliJigou> guanliJigouLst;
	private List<ShiyeDanwei> shiyeDanweiLst;
	List<JichuSheshi> jichuSheshiLst;

	public List<XingzhengBumen> getXingzhengBumenLst() {
		return xingzhengBumenLst;
	}

	public void setXingzhengBumenLst(List<XingzhengBumen> xingzhengBumenLst) {
		this.xingzhengBumenLst = xingzhengBumenLst;
	}

	public List<GuanliJigou> getGuanliJigouLst() {
		return guanliJigouLst;
	}

	public void setGuanliJigouLst(List<GuanliJigou> guanliJigouLst) {
		this.guanliJigouLst = guanliJigouLst;
	}

	public List<ShiyeDanwei> getShiyeDanweiLst() {
		return shiyeDanweiLst;
	}

	public void setShiyeDanweiLst(List<ShiyeDanwei> shiyeDanweiLst) {
		this.shiyeDanweiLst = shiyeDanweiLst;
	}

	public List<JichuSheshi> getJichuSheshiLst() {
		return jichuSheshiLst;
	}

	public void setJichuSheshiLst(List<JichuSheshi> jichuSheshiLst) {
		this.jichuSheshiLst = jichuSheshiLst;
	}

	public int getBaomiganbubianzhirenshu() {
		return baomiganbubianzhirenshu;
	}

	public void setBaomiganbubianzhirenshu(int baomiganbubianzhirenshu) {
		this.baomiganbubianzhirenshu = baomiganbubianzhirenshu;
	}

	public String getChenglishijianString() {
		return chenglishijianString;
	}

	public void setChenglishijianString(String chenglishijianString) {
		this.chenglishijianString = chenglishijianString;
	}

	public String getTianbiaoriqiString() {
		return tianbiaoriqiString;
	}

	public void setTianbiaoriqiString(String tianbiaoriqiString) {
		this.tianbiaoriqiString = tianbiaoriqiString;
	}

	public String toString() {
		return "" + id + "," + orgname + "," + suorgid + "," + chenglishijian + "," + xingzhengjibie + "," + jingfeilaiyuan + "," + zhengfuxulie + ","
				+ jigouleibie + "," + status + "," + shenheren + "," + tianbiaoren + "," + tianbiaoriqi + "," + deleteflag + "," + bianzhishu + "," + renshu
				+ "," + zhuanzhiganbushu + "," + xueshengshu + "," + jiuyeqingkuang;
	}
}
