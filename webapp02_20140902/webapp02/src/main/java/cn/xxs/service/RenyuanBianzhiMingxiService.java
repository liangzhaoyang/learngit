package cn.xxs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.xxs.dao.RenyuanBianzhiMingxiDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.entity.RenyuanBianzhiMingxi;

@Service
public class RenyuanBianzhiMingxiService 
{
	@Autowired
	private RenyuanBianzhiMingxiDao bianzhiDao;
	@Autowired
	private SequenceDao sequenceDao; 
//	public String  getAllBianzhi(int orgid,int code )
//	{
//		return bianzhiDao.getAllBianzhi(orgid,code);
//		
//	}
	public List<RenyuanBianzhiMingxi> getAllBianzhi(int orgid)
	{
		return bianzhiDao.getAllBianzhi(orgid);
		
	}
	public void updateBianzhi(int orgid,int code,int num)
	{
		bianzhiDao.updateBianzhi(orgid,code,num);
	}
//	public void insertBianzhi(RenyuanBianzhiMingxi bianzhi)
//	{
//		bianzhiDao.insertBianzhi(bianzhi);
//	}
	public void insertBianzhi(int orgid,int code,int num)
	{
		bianzhiDao.insertBianzhi(orgid,code,num);
	}

	public void saveRenyuanBianzhi(int orgid, List<RenyuanBianzhiMingxi> mingxiList) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			bianzhiDao.deleteRenyuanBianzhiByOrgid(orgid);
			for(RenyuanBianzhiMingxi entity : mingxiList) {
				bianzhiDao.insertRenyuanBianzhi(entity);
			}
		} catch(Exception e) {
			transactionManager.rollback(status);
			throw e;
		}
		transactionManager.commit(status);
	}
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
}
