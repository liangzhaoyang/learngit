package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author 作者 :wan
      创建时间：2014年8月12日 下午3:09:10
 * 类说明
 */
public class RenyuanBianzhiQingkuang implements Serializable{
	private static final long serialVersionUID = -4752564207074293383L;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	private List<RenyuanBianzhiMingxi> rybzmx;
	
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
	
	public List<RenyuanBianzhiMingxi> getRenyuanBianzhiMingxis() {  
        return rybzmx;  
    }  
  
    public void setRenyuanBianzhiMingxis(List<RenyuanBianzhiMingxi> rybzmx) {  
        this.rybzmx = rybzmx;  
    }  
    
    public String toString()
    {
    	return shenheren+","+tianbiaoren+","+tianbiaoriqi;
    }
}
