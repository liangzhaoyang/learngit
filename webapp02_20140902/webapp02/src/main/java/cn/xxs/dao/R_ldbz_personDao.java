package cn.xxs.dao;

import org.apache.ibatis.annotations.Param;



public interface R_ldbz_personDao {
	public void insertr_ldbz_person(@Param(value = "orgid")int orgid,@Param(value = "personid")int personid);
	
	public void deleter_ldbz_person(@Param(value = "orgid")int orgid,@Param(value = "personid")int id);
}

