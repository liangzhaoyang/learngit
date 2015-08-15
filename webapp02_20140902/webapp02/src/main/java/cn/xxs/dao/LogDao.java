package cn.xxs.dao;

import java.util.List;

import cn.xxs.entity.Log;
import cn.xxs.entity.LogCondition;

/**
 * @author 作者 :wan
      创建时间：2014年8月11日 下午1:50:12
 * 类说明
 */
public interface LogDao {
	public List<Log> queryLogByConditionPage(LogCondition log);
	public void insertLog(Log log);
}
