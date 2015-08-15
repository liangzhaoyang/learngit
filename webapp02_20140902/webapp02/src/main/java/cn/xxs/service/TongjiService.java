package cn.xxs.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.ConstantsDao;
import cn.xxs.dao.TongjiDao;
import cn.xxs.entity.SelectItem;

@Service
public class TongjiService {
	public List<Map<String, Object>> rybztj() {
		List<SelectItem> shengfenList = constantsDao.getSelectionListByType("省份");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> sum = new HashMap<String, Object>();
		sum.put("name", "合计");
		for(SelectItem shengfen : shengfenList) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("code",  shengfen.getCode());
			row.put("name", shengfen.getName());
			for(int i = 101; i <= 115; ++i) {
				row.put("Col_" + i, 0);
			}
			resultList.add(row);
		}
		for(int i = 101; i <= 115; ++i) {
			sum.put("Col_" + i, 0);
		}
		
		List<Map<String, Object>> mingxis = tongjiDao.getRenyuanbianzhi();
		for(Map<String, Object> mingxi : mingxis) {
			if(mingxi == null) continue;
			if(mingxi.get("shengfen") == null) continue;
			Integer shengfen = (Integer)mingxi.get("shengfen");
			Map<String, Object> row = null;
			for(Map<String, Object> curRow : resultList) {
				if(curRow.get("code") == shengfen) {
					row = curRow;
					break;
				}
			}
			if(row == null || mingxi.get("code") == null || mingxi.get("num") == null) {
				continue;
			}
			String colName = "Col_" + mingxi.get("code");
			Integer curNum = (Integer)row.get(colName);
			curNum += ((BigDecimal)mingxi.get("num")).intValue();
			row.put(colName,  curNum);
			
			Integer curSumNum = (Integer)sum.get(colName);
			curSumNum += ((BigDecimal)mingxi.get("num")).intValue();
			sum.put(colName, curSumNum);
		}
		resultList.add(sum);
		return resultList;
	}

	public List<Map<String, Object>> zbrytj() {
		List<SelectItem> shengfenList = constantsDao.getSelectionListByType("省份");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> sum = new HashMap<String, Object>();
		sum.put("name", "合计");
		for(SelectItem shengfen : shengfenList) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("code",  shengfen.getCode());
			row.put("name", shengfen.getName());
			for(int i = 101; i <= 118; ++i) {
				row.put("Col_" + i, 0);
			}
			resultList.add(row);
		}
		for(int i = 101; i <= 118; ++i) {
			sum.put("Col_" + i, 0);
		}
		
		List<Map<String, Object>> mingxis = tongjiDao.getZaibianrenyuan();
		for(Map<String, Object> mingxi : mingxis) {
			if(mingxi == null) continue;
			if(mingxi.get("shengfen") == null) continue;
			Integer shengfen = (Integer)mingxi.get("shengfen");
			Map<String, Object> row = null;
			for(Map<String, Object> curRow : resultList) {
				if(curRow.get("code") == shengfen) {
					row = curRow;
					break;
				}
			}
			if(row == null || mingxi.get("code") == null || mingxi.get("num") == null) {
				continue;
			}
			String colName = "Col_" + mingxi.get("code");
			Integer curNum = (Integer)row.get(colName);
			curNum += ((BigDecimal)mingxi.get("num")).intValue();
			row.put(colName,  curNum);
			
			Integer curSumNum = (Integer)sum.get(colName);
			curSumNum += ((BigDecimal)mingxi.get("num")).intValue();
			sum.put(colName, curSumNum);
		}
		resultList.add(sum);
		return resultList;
	}

	public List<Map<String, Object>> jgtj() {
		List<SelectItem> shengfenList = constantsDao.getSelectionListByType("省份");
		List<SelectItem> xingzhengjibieList = constantsDao.getSelectionListByType("行政级别_2");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> sum = new HashMap<String, Object>();
		sum.put("name", "合计");
		for(SelectItem shengfen : shengfenList) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("code",  shengfen.getCode());
			row.put("name", shengfen.getName());
			for(SelectItem jibie : xingzhengjibieList) {
				row.put("xzglbm_" + jibie.getCode() + "_bianzhirenshu", 0);
				row.put("xzglbm_" + jibie.getCode() + "_shiyourenshu", 0);
				row.put("czgljg_" + jibie.getCode() + "_bianzhirenshu", 0);
				row.put("czgljg_" + jibie.getCode() + "_shiyourenshu", 0);
				row.put("sydw_" + jibie.getCode() + "_bianzhirenshu", 0);
				row.put("sydw_" + jibie.getCode() + "_shiyourenshu", 0);
			}
			resultList.add(row);
		}
		for(SelectItem jibie : xingzhengjibieList) {
			sum.put("xzglbm_" + jibie.getCode() + "_bianzhirenshu", 0);
			sum.put("xzglbm_" + jibie.getCode() + "_shiyourenshu", 0);
			sum.put("czgljg_" + jibie.getCode() + "_bianzhirenshu", 0);
			sum.put("czgljg_" + jibie.getCode() + "_shiyourenshu", 0);
			sum.put("sydw_" + jibie.getCode() + "_bianzhirenshu", 0);
			sum.put("sydw_" + jibie.getCode() + "_shiyourenshu", 0);
		}
	
		List<Map<String, Object>> mingxis = tongjiDao.getJigou();
		for(Map<String, Object> mingxi : mingxis) {
			if(mingxi == null) continue;
			if(mingxi.get("shengfen") == null) continue;
			Integer shengfen = (Integer)mingxi.get("shengfen");
			Map<String, Object> row = null;
			for(Map<String, Object> curRow : resultList) {
				if(curRow.get("code") == shengfen) {
					row = curRow;
					break;
				}
			}
			if(row == null || mingxi.get("leibie") == null || mingxi.get("xingzhengjibie") == null) {
				continue;
			}
			
			String bianzhirenshuColName = "" + mingxi.get("leibie") + "_" + mingxi.get("xingzhengjibie") + "_" + "bianzhirenshu";
			String shiyourenshuColName = "" + mingxi.get("leibie") + "_" + mingxi.get("xingzhengjibie") + "_" + "shiyourenshu";
			Integer curBianzhirenshuNum = (Integer)row.get(bianzhirenshuColName);
			Integer curshiyourenshuNum = (Integer)row.get(shiyourenshuColName);
			
			if(mingxi.get("bianzhirenshu") != null && curBianzhirenshuNum != null) {
				_logger.debug("mingxi:" + mingxi);
				curBianzhirenshuNum += ((BigDecimal)mingxi.get("bianzhirenshu")).intValue();
				row.put(bianzhirenshuColName,  curBianzhirenshuNum);
				
				Integer curSumNum = (Integer)sum.get(bianzhirenshuColName);
				curSumNum += ((BigDecimal)mingxi.get("bianzhirenshu")).intValue();
				sum.put(bianzhirenshuColName,  curSumNum);
			}
	
			if(mingxi.get("shiyourenshu") != null && curshiyourenshuNum != null) {
				curshiyourenshuNum += ((BigDecimal)mingxi.get("shiyourenshu")).intValue();
				row.put(shiyourenshuColName,  curshiyourenshuNum);
				
				Integer curSumNum = (Integer)sum.get(shiyourenshuColName);
				curSumNum += ((BigDecimal)mingxi.get("shiyourenshu")).intValue();
				sum.put(shiyourenshuColName,  curSumNum);
			}
		}
		resultList.add(sum);
		return resultList;
	}

	@Autowired
	private ConstantsDao constantsDao;
	@Autowired
	private TongjiDao tongjiDao;
	
	private static Logger _logger=Logger.getLogger(TongjiService.class);
}
