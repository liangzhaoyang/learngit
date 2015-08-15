package cn.xxs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.PersonDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.entity.Person;
import cn.xxs.entity.PersonCondition;

@Service
public class PersonService {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private SequenceDao sequenceDao;
	
	public int  insertPerson(Person person)
	{
		Integer seq = sequenceDao.getSequence("person");
		person.setId(seq);
		personDao.insertPerson(person);
		
		return seq;
	}
	
	public void updatePerson(Person person)
	{
		personDao.updatePerson(person);
	}
	//
	public List<Person> getPeopleByOrgnizationId(int orgid)
	{
		return personDao.getPeopleByOrgnizationIdPage(orgid);
	}
	
	public List<Person> getPersonByCondition(PersonCondition personCon)
	{
		return personDao.getPersonByConditionPage(personCon);
	}
	//
	public Person getPersonById(int id)
	{
		return personDao.getPersonById(id);
	}
	public List<Map<String, Object>> selectPersonByCondition(PersonCondition personInfo) {
		return personDao.selectPersonByConditionPage(personInfo);
	}
	
}
