package cn.xxs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.PersonDao;
import cn.xxs.dao.R_shemi_personDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.entity.Person;
import cn.xxs.entity.PersonCondition;


@Service
public class ShemiService {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private R_shemi_personDao r_shemi_personDao;
	@Autowired
	private SequenceDao sequenceDao; 

	public List<Person> selectAllShemiRenyuan(int id)
	{
		return personDao.selectAllShemiRenyuanPage(id);
	}

	public  List<Map<String, Object>> selectShemiPersonPage(PersonCondition map)
	{
		return personDao.selectShemiPersonPage(map);
	}
	public void deleteShemiPerson(int orgid,List<Integer> ids)
	{
		for(int id:ids)
		{
			r_shemi_personDao.deleter_shemi_person(orgid,id);
			
		}	
	}

		public void insertr_shemi_person(int orgid,int personid)
		{
			r_shemi_personDao.insertr_shemi_person(orgid, personid);
		}

}