package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author 作者 :wan
      创建时间：2014年8月12日 下午4:53:51
 * 类说明
 */
public class BaomiGanbuQingkuang implements Serializable{
	private static final long serialVersionUID = -4443222844702707560L;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	private short jigouleibie;
	private int xingzhengjibie;
	private int baomiganbubianzhirenshu;
	private List<Person> persons;
	
	public void setJigouleibie(short jigouleibie){
		this.jigouleibie=jigouleibie;
		}
	public int getJigouleibie(){
		return jigouleibie;
	}
	
	public void setXingzhengjibie(int xingzhengjibie){
		this.xingzhengjibie=xingzhengjibie;
		}
	public int getXingzhengjibie(){
		return xingzhengjibie;
	}
	
	public void setBaomiganbubianzhirenshu(int baomiganbubianzhirenshu){
		this.baomiganbubianzhirenshu=baomiganbubianzhirenshu;
		}
	public int getBaomiganbubianzhirenshu(){
		return baomiganbubianzhirenshu;
	}
	//
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
	
	public List<Person> getPersons() {  
        return persons;  
    }  
  
    public void setPersons(List<Person> persons) {  
        this.persons = persons;  
    }  
    
    public String toString()
    {
    	String str="";
    	for(Person temp:persons)
    	{
    		str=str+temp.toString();
    	}
    	return shenheren+","+tianbiaoren+","+tianbiaoriqi+","+jigouleibie+","+xingzhengjibie+","+baomiganbubianzhirenshu;
    }
}
