package net.dao;

import java.util.List;

import net.domain.Alarm;
import net.domain.DataFig;
import net.web.page.CriteriaObj;
import net.web.page.Page;

public interface AlarmDao {

	/**
	 * 根据传入的 CriteriaBook 对象返回对应的 Page 对象
	 * @param cb
	 * @return
	 */
	public Page<Alarm> getPage(CriteriaObj cb);

	/**
	 * 根据传入的 CriteriaBook 对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public long getTotalAlarmNumber(CriteriaObj cb);

	/**
	 * 根据传入的 CriteriaBook 和 pageSize 返回当前页对应的 List 
	 * @param cb
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Alarm> getPageList(CriteriaObj cb,int pageSize);
	
	public List<DataFig> getDataSet(String start_time,String end_time);
}
