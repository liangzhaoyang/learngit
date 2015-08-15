package cn.xxs.dao;

import java.util.List;
import java.util.Map;

import cn.xxs.entity.SelectItem;

public interface ConstantsDao {
	public List<SelectItem> getSelectionListByType(String typeName);

	public List<SelectItem> getAllConstants();

	public String getSelectionConstantName(Map<String, Object> map);
}
