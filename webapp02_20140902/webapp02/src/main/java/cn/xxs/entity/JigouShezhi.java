package cn.xxs.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 作者 :wan
      创建时间：2014年8月24日 下午3:52:11
 * 类说明
 */
public class JigouShezhi implements Serializable{
	private static final long serialVersionUID = -5385022781250506040L;
	private Orgnization org;
	private List<JichuSheshi> jcss;
	private List<XingzhengBumenHelper> xzbm;//=new ArrayList<XingzhengBumenHelper>();
	private List<GuanliJigou> gljg;
	private List<ShiyeDanwei> sydw;
	
	public void setOrg(Orgnization org) {
		this.org = org;
	}

	public Orgnization getOrg() {
		return org;
	}

	public void setJcss(List<JichuSheshi> jcss) {
		this.jcss = jcss;
	}

	public List<JichuSheshi> getJcss() {
		return jcss;
	}
	
	public void setXzbm(List<XingzhengBumenHelper> xzbm) {
		this.xzbm = xzbm;
	}

	public List<XingzhengBumenHelper> getXzbm() {
		return xzbm;
	}
	
	public void setGljg(List<GuanliJigou> gljg) {
		this.gljg = gljg;
	}

	public List<GuanliJigou> getGljg() {
		return gljg;
	}
	
	public void setSydw(List<ShiyeDanwei> sydw) {
		this.sydw = sydw;
	}

	public List<ShiyeDanwei> getSydw() {
		return sydw;
	}
}
