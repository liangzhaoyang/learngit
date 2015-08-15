package cn.xxs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.PersonDao;
import cn.xxs.dao.R_ldbz_personDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.entity.Person;
import cn.xxs.entity.PersonCondition;
@Service
public class LeaderService {
	
	@Autowired
	private PersonDao personDao;
	@Autowired
	private R_ldbz_personDao r_ldbz_personDao;
	@Autowired
	private SequenceDao sequenceDao; 

	public List<Person> selectAllLeader(int id)
	{
		return personDao.selectAllLeaderPage(id);
	}
	public  List<Map<String, Object>> selectLeaderPersonPage(PersonCondition personInfo)
	{
		return personDao.selectLeaderPersonPage(personInfo);
	}
	
	public void deleteLeaderPerson(int orgid,List<Integer> ids)
	{
		
		for(int id:ids)
		{
			
			r_ldbz_personDao.deleter_ldbz_person(orgid,id);
			
		}
		
	}
	
	public void insertr_ldbz_person(int orgid,int personid)
	{
		r_ldbz_personDao.insertr_ldbz_person(orgid, personid);
	}
}
