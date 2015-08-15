package cn.xxs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.SequenceDao;

@Service
public class SequenceService {
	public int getSequence(String tblName) {
		return sequenceDao.getSequence(tblName);
	}
	
	@Autowired
	private SequenceDao sequenceDao;
}
