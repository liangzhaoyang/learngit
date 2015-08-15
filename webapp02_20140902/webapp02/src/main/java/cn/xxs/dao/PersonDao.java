package cn.xxs.dao;



import java.util.List;
import java.util.Map;

import cn.xxs.entity.Person;
import cn.xxs.entity.PersonCondition;
import cn.xxs.entity.ResumePerson;

public interface PersonDao {
	public List<Person> getAllPerson();
	public Person getPersonById(int id );
	public void updatePerson(Person person);
	public void insertPerson(Person person);
	public List<Person> getPersonByConditionPage(PersonCondition personCon);
	//按条件查询ganbu 
	public List<Map<String, Object>> selectGanbuPersonPage(PersonCondition personInfo);
	//ganbu加载
	public List<Person> selectAllGanbuPage(int id);
	//领导班子按条件查询
	public List<Map<String, Object>> selectLeaderPersonPage(PersonCondition personInfo);
	//ldbz加载
	public List<Person> selectAllLeaderPage(int id);
	//涉密人员按条件查询
	public List<Map<String, Object>> selectShemiPersonPage(PersonCondition map);
	//涉密人员加载
	public List<Person> selectAllShemiRenyuanPage(int id);
	//简历查询selectAllShemiRenyuan
	public ResumePerson selectResumePersonByPersonid(int id);
	
	public List<Person> getPeopleByOrgnizationIdPage(int orgid);
	
	public List<Map<String, Object>> selectPersonByConditionPage(PersonCondition personInfo);
	
	//涉密人员加载
	public List<Person> selectAllShemiRenyuan(int id);
	//ldbz加载
	public List<Person> selectAllLeader(int id);
	//ganbu加载
	public List<Person> selectAllGanbu(int id);
}
