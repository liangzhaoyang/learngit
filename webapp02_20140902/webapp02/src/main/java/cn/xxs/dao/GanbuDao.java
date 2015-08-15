package cn.xxs.dao;

import java.util.List;
import java.util.Map;

import cn.xxs.entity.Person;


public interface GanbuDao {
	    //显示所有ganbu
		//public List<GanbuPerson> getAllGanbu();
		//按条件查询
		
	/*public List<Person> selectGanbuPerson(@Param(value = "orgid") int orgid,@Param(value = "name") String name,@Param(value = "xingbie") int xingbie,@Param(value = "chushengnianyueFrom") Date chushengnianyueFrom,
			@Param(value = "chushengnianyueTo") Date chushengnianyueTo,@Param(value = "zhengzhimianmao") int zhengzhimianmao,@Param(value = "zhuanye") String zhuanye,
			@Param(value = "shifouzhuanzhi") int shifouzhuanzhi,@Param(value = "suoshubumen") int suoshubumen,
			@Param(value = "danweimingcheng") int danweimingcheng,@Param(value = "ordercon") String order);*/
	public List<Person> selectGanbuPersonPage(Map<String, Object> map);
	public void deleteGanbu(String id);

	

}

