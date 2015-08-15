package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author 作者 :wan
      创建时间：2014年8月12日 下午3:55:02
 * 类说明
 */
public class ZaibianRenyuanQingkuang implements Serializable{
	private static final long serialVersionUID = 250796796674714990L;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	private List<ZaibianRenyuanMingxi> zbrymx;
	
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
	
	public List<ZaibianRenyuanMingxi> getZaibianRenyuanMingxi() {  
        return zbrymx;  
    }  
  
    public void setZaibianRenyuanMingxi(List<ZaibianRenyuanMingxi> zbrymx) {  
        this.zbrymx = zbrymx;  
    }  
    
    public String toString()
    {
    	return shenheren+","+tianbiaoren+","+tianbiaoriqi;
    }
}
