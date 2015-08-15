package cn.xxs.dao;

import org.apache.ibatis.annotations.Param;

public interface R_ganbu_personDao {
	
	public void deleter_ganbu_person(@Param(value = "orgid")int orgid,@Param(value = "personid")int id);
	public void insertr_ganbu_person(@Param(value = "orgid")int orgid,@Param(value = "personid")int personid);
}
