package cn.xxs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.xxs.dao.ZaibianRenyuanMingxiDao;
import cn.xxs.entity.ZaibianRenyuanMingxi;
@Service
public class ZaibianRenyuanMingxiService {
	@Autowired
	private ZaibianRenyuanMingxiDao zaibianRenyuanMingxiDao;

	public List<ZaibianRenyuanMingxi> getAllZaibian(int orgid)
	{
		return zaibianRenyuanMingxiDao.getAllZaibian(orgid);
		
	}
	public void updateZaibian(int orgid,int code,int num)
	{
		zaibianRenyuanMingxiDao.updateZaibian(orgid,code,num);
	}

	public void insertZaibian(int orgid,int code,int num)
	{
		zaibianRenyuanMingxiDao.insertZaibian(orgid,code,num);
	}
	
	public void saveRenyuanBianzhi(int orgid, List<ZaibianRenyuanMingxi> mingxiList) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			zaibianRenyuanMingxiDao.deleteZaibianRenyuanByOrgid(orgid);
			for(ZaibianRenyuanMingxi entity : mingxiList) {
				zaibianRenyuanMingxiDao.insertZaibianRenyuan(entity);
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


