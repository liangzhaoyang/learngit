package cn.xxs.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author 作者 :wan
      创建时间：2014年8月12日 下午5:20:18
 * 类说明
 */
public class ShemiRenyuanQingkuang implements Serializable{
	private static final long serialVersionUID = -3877215813841143486L;
	private String shenheren;
	private String tianbiaoren;
	private Date tianbiaoriqi;
	private List<Person> persons;
	
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
    	return shenheren+","+tianbiaoren+","+tianbiaoriqi;
    }
}
