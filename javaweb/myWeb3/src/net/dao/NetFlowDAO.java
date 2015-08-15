package net.dao;

import java.util.List;

import net.domain.DataFig;
import net.domain.NetFlow;
import net.web.page.CriteriaObj;
import net.web.page.Page;


public interface NetFlowDAO {


	/**
	 * 根据传入的 CriteriaBook 对象返回对应的 Page 对象
	 * @param cb
	 * @return
	 */
	public abstract Page<NetFlow> getPage(CriteriaObj cb);

	/**
	 * 根据传入的 CriteriaBook 对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public abstract long getTotalNetFlowNumber(CriteriaObj cb);

	/**
	 * 根据传入的 CriteriaBook 和 pageSize 返回当前页对应的 List 
	 * @param cb
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<NetFlow> getPageList(CriteriaObj cb,int pageSize);

	public abstract List<DataFig> getTotalNetFlowData(String start_time,String end_time);
	
	@SuppressWarnings("rawtypes")
	public abstract List getTotalNetFlowData2(String start_time,String end_time);
	
	//public abstract List getTotalNetFlowData3(String start_time,String end_time);
}