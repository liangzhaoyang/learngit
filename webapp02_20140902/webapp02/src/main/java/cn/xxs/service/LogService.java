package cn.xxs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.LogDao;
import cn.xxs.entity.Log;
import cn.xxs.entity.LogCondition;


@Service
public class LogService {
	@Autowired
	private LogDao logDao;
	
	public List<Log> getLogByCondition(LogCondition logCon)
	{
		return logDao.queryLogByConditionPage(logCon);
	}
	
	public void insertLog(Log log) {
		logDao.insertLog(log);
	}
}
