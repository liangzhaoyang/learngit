package cn.xxs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.PersonDao;
import cn.xxs.dao.R_ganbu_personDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.entity.Person;
import cn.xxs.entity.PersonCondition;


@Service
public class GanbuService {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private R_ganbu_personDao r_ganbu_personDao;
	@Autowired
	private SequenceDao sequenceDao; 

		public List<Person> selectAllGanbu(int id)
		{
			return personDao.selectAllGanbuPage(id);
		}

	public  List<Map<String, Object>> selectGanbuPersonPage(PersonCondition personInfo)
	{
		return personDao.selectGanbuPersonPage(personInfo);
	}

	public void deleteGanbuPerson(int orgid,List<Integer> ids)
	{
	
		
		for(int id:ids)
		{
			
			
			r_ganbu_personDao.deleter_ganbu_person(orgid,id);
			
		}
		
	}

	public void insertr_ganbu_person(int orgid,int personid)
	{
		r_ganbu_personDao.insertr_ganbu_person(orgid, personid);
	}

	

}