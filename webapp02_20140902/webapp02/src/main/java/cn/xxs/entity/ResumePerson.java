package cn.xxs.entity;

import java.sql.Date;

public class ResumePerson {
	private int id;
	private String name;
	private int xingbie; 
	private Date chushengnianyue;
	private int zhengzhimianmao;
	private int minzu;
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
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
	public void setZhengzhimianmao(int zhengzhimianmao){
		this.zhengzhimianmao=zhengzhimianmao;
	}
	public int getZhengzhimianmao(){
		return zhengzhimianmao;
	}
	
	public void setChushengnianyue(Date chushengnianyue){
		this.chushengnianyue=chushengnianyue;
	}
	public Date gettChushengnianyue(){
		return chushengnianyue;
	}
	
	public void setMinzu(int minzu){
		this.minzu=minzu;
	}
	public int getMinzu(){
		return minzu;
	}
	public String toString()
	{
		return ""+id+","+name+","+xingbie+","+zhengzhimianmao+","+chushengnianyue+","+minzu;
	}

}
