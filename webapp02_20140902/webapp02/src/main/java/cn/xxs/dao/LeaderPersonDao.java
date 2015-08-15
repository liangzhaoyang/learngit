package cn.xxs.dao;

import java.util.List;
import java.util.Map;

import cn.xxs.entity.Person;

public interface LeaderPersonDao {


			//按条件查询
		
			/*public List<Person> selectLeaderPerson(@Param(value = "orgid") int orgid,@Param(value = "name") String name,@Param(value = "xingbie") int xingbie,@Param(value = "chushengnianyueFrom") Date chushengnianyueFrom,
			@Param(value = "chushengnianyueTo") Date chushengnianyueTo,@Param(value = "zhengzhimianmao") int zhengzhimianmao,@Param(value = "zhuanye") String zhuanye,
			@Param(value = "shifouzhuanzhi") int shifouzhuanzhi,@Param(value = "suoshubumen") int suoshubumen,
			@Param(value = "danweimingcheng") int danweimingcheng,@Param(value = "ordercon") String order);*/
			public List<Person> selectLeaderPersonPage(Map<String, Object> map);
			//public void deleteLeader(String id);
			public void deleteLeader(String id);
			
}
