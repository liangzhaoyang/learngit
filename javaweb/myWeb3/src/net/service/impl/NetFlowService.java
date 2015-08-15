package net.service.impl;

import net.dao.NetFlowDAO;
import net.dao.impl.NetFlowDAOImpl;
import net.domain.NetFlow;
import net.web.page.CriteriaObj;
import net.web.page.Page;


public class NetFlowService {
	
	private NetFlowDAO netFlowDAO = new NetFlowDAOImpl();
	
	public Page<NetFlow> getPage(CriteriaObj criteriaNetFlow){
		return netFlowDAO.getPage(criteriaNetFlow);
	}
}
