package cn.xxs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.ConstantsDao;
import cn.xxs.entity.SelectItem;

@Service
public class ConstantsService {
	public List<SelectItem> getSelectListByType(String typeName) {
		return constantsDao.getSelectionListByType(typeName);
	}
	public List<SelectItem> getAllConstants() {
		return constantsDao.getAllConstants();
	}

	public String getSelectionConstantName(String typeName,int code)
	{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("typeName", typeName);
		map.put("code", code);
		
		return constantsDao.getSelectionConstantName(map);
	}
	
	@Autowired
	private ConstantsDao constantsDao;
}
