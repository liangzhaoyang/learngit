package cn.xxs.entity;

import java.io.Serializable;

/**
 * @author 作者 :wan
      创建时间：2014年8月24日 下午4:05:04
 * 类说明
 */
public class XingzhengBumenHelper implements Serializable{
	private static final long serialVersionUID = -5114002783879443745L;
	private int xzbmid;
	private int orgid;
	private String xzbmname;
	private int xzbmbianzhirenshu;
	private int xzbmshijirenshu;
	private String xzbmxingzhengjibie;

	public void setXzbmid(int xzbmid) {
		this.xzbmid = xzbmid;
	}

	public int getXzbmid() {
		return xzbmid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setXzbmname(String xzbmname) {
		this.xzbmname = xzbmname;
	}

	public String getXzbmname() {
		return xzbmname;
	}

	public void setXzbmbianzhirenshu(int xzbmbianzhirenshu) {
		this.xzbmbianzhirenshu = xzbmbianzhirenshu;
	}

	public int getXzbmbianzhirenshu() {
		return xzbmbianzhirenshu;
	}

	public void setXzbmshijirenshu(int xzbmshijirenshu) {
		this.xzbmshijirenshu = xzbmshijirenshu;
	}

	public int getXzbmshijirenshu() {
		return xzbmshijirenshu;
	}

	public void setXzbmxingzhengjibie(String xzbmxingzhengjibie) {
		this.xzbmxingzhengjibie = xzbmxingzhengjibie;
	}

	public String getXzbmxingzhengjibie() {
		return xzbmxingzhengjibie;
	}

	public String toString() {
		return "" + xzbmid + "," + orgid + "," + xzbmname + "," + xzbmbianzhirenshu + "," + xzbmshijirenshu + "," + xzbmxingzhengjibie;
	}
}
