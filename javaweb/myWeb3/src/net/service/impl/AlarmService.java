package net.service.impl;

import java.util.List;

import net.dao.AlarmDao;
import net.dao.impl.AlarmDaoImpl;
import net.domain.Alarm;
import net.domain.DataFig;
import net.web.page.CriteriaObj;
import net.web.page.Page;

public class AlarmService {

	AlarmDao alarmDao=new AlarmDaoImpl();
	public Page<Alarm> getPage(CriteriaObj criteriaAlarm){
		return alarmDao.getPage(criteriaAlarm);
	}
	
	public List<DataFig> getDataSet(String start_time,String end_time){
		return alarmDao.getDataSet(start_time, end_time);
	}
	
	
}
