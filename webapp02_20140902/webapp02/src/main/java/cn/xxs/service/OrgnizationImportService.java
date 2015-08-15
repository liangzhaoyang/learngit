package cn.xxs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.xxs.dao.ConstantsDao;
import cn.xxs.dao.GanbuDao;
import cn.xxs.dao.OrgnizationDao;
import cn.xxs.dao.PersonDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.entity.Chengguo;
import cn.xxs.entity.GuanliJigou;
import cn.xxs.entity.Jiangcheng;
import cn.xxs.entity.JiatingChengyuan;
import cn.xxs.entity.JichuSheshi;
import cn.xxs.entity.Orgnization;
import cn.xxs.entity.Person;
import cn.xxs.entity.R_Person;
import cn.xxs.entity.RenyuanBianzhiMingxi;
import cn.xxs.entity.Resume;
import cn.xxs.entity.ResumeForImport;
import cn.xxs.entity.SelectItem;
import cn.xxs.entity.ShiyeDanwei;
import cn.xxs.entity.XingzhengBumen;
import cn.xxs.entity.XueliMingxi;
import cn.xxs.entity.ZaibianRenyuanMingxi;

@Service
public class OrgnizationImportService {
	private final String orgBaseInfoFileName = "保密行政部门及机构设置基本情况填报表";
	private final String lingdaoBanziInfoFileName = "保密委员会办公室、保密局领导班子及工作人员基本情况填报表";
	private final String renyuanBianzhiInfoFileName = "保密行政管理部门人员编制情况填报表";
	private final String zaibianRenyuanInfoFileName = "保密行政管理部门在编人员情况填报表";
	private final String baomiGanbuInfoFileName = "保密工作机构保密干部基本情况填报表";
	private final String shemiRenyuanInfoFileName = "涉密人员基本情况填报表";
	private final String fujianFileName = "附件";
	private final String resumeFileName = "简历";
	
	private void createDir(String path) throws IOException {
		File file = new File(path);
		if(!file.exists()) {
			createDir(file.getParent());
			if(!file.mkdir()) {
				throw new IOException("文件创建失败");
			}
		}
	}

	class FileMapping {
		private String orgBaseInfoFile;
		private String lindaoBanziInfoFile;
		private String renyuanBianzhiInfoFile;
		private String zaibianRenyuanInfoFile;
		private String baomiGanbuInfoFile;
		private String shemirenyuanInfoFile;
		private String fujianFile;
		private List<String> resumes;
		public String getOrgBaseInfoFile() {
			return orgBaseInfoFile;
		}
		public void setOrgBaseInfoFile(String orgBaseInfoFile) {
			this.orgBaseInfoFile = orgBaseInfoFile;
		}
		public String getLindaoBanziInfoFile() {
			return lindaoBanziInfoFile;
		}
		public void setLindaoBanziInfoFile(String lindaoBanziInfoFile) {
			this.lindaoBanziInfoFile = lindaoBanziInfoFile;
		}
		public String getRenyuanBianzhiInfoFile() {
			return renyuanBianzhiInfoFile;
		}
		public void setRenyuanBianzhiInfoFile(String renyuanBianzhiInfoFile) {
			this.renyuanBianzhiInfoFile = renyuanBianzhiInfoFile;
		}
		public String getZaibianRenyuanInfoFile() {
			return zaibianRenyuanInfoFile;
		}
		public void setZaibianRenyuanInfoFile(String zaibianRenyuanInfoFile) {
			this.zaibianRenyuanInfoFile = zaibianRenyuanInfoFile;
		}
		public String getBaomiGanbuInfoFile() {
			return baomiGanbuInfoFile;
		}
		public void setBaomiGanbuInfoFile(String baomiGanbuInfoFile) {
			this.baomiGanbuInfoFile = baomiGanbuInfoFile;
		}
		public String getShemirenyuanInfoFile() {
			return shemirenyuanInfoFile;
		}
		public void setShemirenyuanInfoFile(String shemirenyuanInfoFile) {
			this.shemirenyuanInfoFile = shemirenyuanInfoFile;
		}
		public String getFujianFile() {
			return fujianFile;
		}
		public void setFujianFile(String fujianFile) {
			this.fujianFile = fujianFile;
		}
		public List<String> getResumes() {
			return resumes;
		}
		public void setResumes(List<String> resumes) {
			this.resumes = resumes;
		}
		public void addresumeFile(String resumeFile) {
			if(resumes == null) {
				resumes = new ArrayList<String>();
			}
			resumes.add(resumeFile);
		}
		public boolean checkDocumentsCompleted() {
			if(orgBaseInfoFile == null || orgBaseInfoFile.isEmpty()) {
				if(_logger.isDebugEnabled()) {
					_logger.debug("没有文件：" + orgBaseInfoFileName);
				}
				return false;
			}
			if(lindaoBanziInfoFile == null || lindaoBanziInfoFile.isEmpty()) {
				if(_logger.isDebugEnabled()) {
					_logger.debug("没有文件：" + lingdaoBanziInfoFileName);
				}
				return false;
			}
			if(renyuanBianzhiInfoFile == null || renyuanBianzhiInfoFile.isEmpty()) {
				if(_logger.isDebugEnabled()) {
					_logger.debug("没有文件：" + renyuanBianzhiInfoFileName);
				}
				return false;
			}
			if(zaibianRenyuanInfoFile == null || zaibianRenyuanInfoFile.isEmpty()) {
				if(_logger.isDebugEnabled()) {
					_logger.debug("没有文件：" + zaibianRenyuanInfoFileName);
				}
				return false;
			}
			if(baomiGanbuInfoFile == null || baomiGanbuInfoFile.isEmpty()) {
				if(_logger.isDebugEnabled()) {
					_logger.debug("没有文件：" + baomiGanbuInfoFileName);
				}
				return false;
			}
			if(shemirenyuanInfoFile == null || shemirenyuanInfoFile.isEmpty()) {
				if(_logger.isDebugEnabled()) {
					_logger.debug("没有文件：" + shemiRenyuanInfoFileName);
				}
				return false;
			}
			return true;
		}
	}
	
	abstract class ExcelParser {
		public static final int stringType = 0;
		public static final int intType = 1;
		public static final int doubleType = 2;
		public static final int dateType = 3;
		
		public abstract void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception;
		
		protected Object getCellValue(HSSFCell cell, int cellType, String fileLabel, String labelName, boolean mustInputed) throws Exception {
			if(cell == null) {
				throw new Exception(fileLabel + "文件格式不正确(" + labelName + ")，请检查。");
			}
			Object obj = null;
			switch(cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				if(cellType != intType && cellType != doubleType) {
					throw new Exception(fileLabel + "文件数据类型不正确(" + labelName + ")，请检查。");
				}
				obj = cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_STRING:
				if(cellType != stringType) {
					throw new Exception(fileLabel + "文件数据类型不正确(" + labelName + ")，请检查。");
				}
				obj = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				obj = null;
				break;
			default:
				throw new Exception(fileLabel + "文件数据类型不正确(" + labelName + ")，请检查。");
			}
			
			if(mustInputed && obj == null) {
				throw new Exception("文件格式不正确(" + labelName + "是必须输入项目)，请检查。");
			}
			return obj;
		}
		
		protected int getCellValue(HSSFCell cell, String fileLabel, String labelName, boolean mustInputed, String typeName, Map<String, Map<String, Integer>> constantsMap) throws Exception {
			String key = (String)getCellValue(cell, stringType, fileLabel, labelName, mustInputed);
			if(key == null) {
				return -1;
			}
			
			Map<String, Integer> typeMap = constantsMap.get(typeName);
			if(typeMap == null) {
				throw new Exception(fileLabel + "文件数据不正确(" + labelName + ")，请检查。");
			}
			
			Integer val = typeMap.get(key);
			if(val == null) {
				throw new Exception(fileLabel + "文件数据不正确(" + labelName + ")，请检查。");
			}
			return val;
		}
		
		protected java.sql.Date getDateCellValue(HSSFCell cell, String fileLabel, String labelName, boolean mustInputed) throws Exception {
			int type = cell.getCellType();
			if(type != HSSFCell.CELL_TYPE_BLANK && type != HSSFCell.CELL_TYPE_NUMERIC ) {
				throw new Exception(fileLabel + "的" + labelName + "不是日期格式，请确认。");
			}
			return new java.sql.Date(cell.getDateCellValue().getTime());
		}
	}
	
	class OrgnizationBaseInfoParser extends ExcelParser {
		public void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap)  throws Exception {
			InputStream inputStream = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;
			Orgnization orgnization = new Orgnization();
			try {
				inputStream = new FileInputStream(fileName);
				try {
					workbook = new HSSFWorkbook(inputStream);
				} catch(FileNotFoundException e) {
					throw new Exception("不能读取文件" + orgBaseInfoFileName);
				}
				sheet = workbook.getSheetAt(0);
				int lastRow = sheet.getLastRowNum();		
				_logger.debug("最大行数：" + lastRow);
				row = sheet.getRow(BaseInfo_Row_1);	
				orgnization.setOrgname((String)getCellValue(row.getCell(BaseInfo_Name_VAL_Col), ExcelParser.stringType,  orgBaseInfoFileName, "机构名称", true));
				orgnization.setSuOrgName((String)getCellValue(row.getCell(BaseInfo_SuName_VAL_Col), ExcelParser.stringType, orgBaseInfoFileName, "上级单位", false));
				
				row =sheet.getRow(BaseInfo_Row_2);
				orgnization.setJigoufenlei1(getCellValue(row.getCell(BaseInfo_Fenlei1_VAL_Col), orgBaseInfoFileName, "机构分类1", true, "机构分类1", constantsMap));
				orgnization.setJigoufenlei2(getCellValue(row.getCell(BaseInfo_Fenlei2_VAL_Col), orgBaseInfoFileName, "机构分类2", true, "机构分类2", constantsMap));
				orgnization.setChenglishijian(getDateCellValue(row.getCell(BaseInfo_ChengliShijian_VAL_Col), orgBaseInfoFileName, "成立时间", true));
				orgnization.setXingzhengjibie(getCellValue(row.getCell(BaseInfo_XingzhengJibie_VAL_Col), orgBaseInfoFileName, "行政级别", true, "行政级别_1", constantsMap));
				
				row =sheet.getRow(BaseInfo_Row_3);
				orgnization.setZhengfuxulie(getCellValue(row.getCell(BaseInfo_ZhengfuXulie_VAL_Col), orgBaseInfoFileName, "是否政府序列", true, "政府序列", constantsMap));
				orgnization.setJingfeilaiyuan((String)getCellValue(row.getCell(BaseInfo_JingfeiLaiyuan_VAL_Col), ExcelParser.stringType, orgBaseInfoFileName, "经费来源", true));
				orgnization.setJigouleibie(getCellValue(row.getCell(BaseInfo_JigouLeibie_VAL_Col), orgBaseInfoFileName, "机构类别", true, "机构类别", constantsMap));

				int JigouShezhiStartRowIndex = BaseInfo_Row_3 + 4;
				int jichuSheshiTileRowIndex = 0;
				List<XingzhengBumen> xingzhengBumenLst = new ArrayList<XingzhengBumen>();
				List<GuanliJigou> guanliJigouLst = new ArrayList<GuanliJigou>();
				List<ShiyeDanwei> shiyeDanweiLst = new ArrayList<ShiyeDanwei>();
				for(int i = 0; ; ++i) {
					if(lastRow < (i + JigouShezhiStartRowIndex)) {
						throw new Exception(orgBaseInfoFileName + "文件格式不正确，请确认。");
					}
					row = sheet.getRow(JigouShezhiStartRowIndex + i);
					cell = row.getCell(0);
					
					if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						continue;
					}

					if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) { // 序号列，如果不是数字则表示机构设置情况结束
						jichuSheshiTileRowIndex = JigouShezhiStartRowIndex + i;
						break;
					}
					
					String bumen1 = (String)getCellValue(row.getCell(XingzhengBumen_Bumen_Col), ExcelParser.stringType, orgBaseInfoFileName, "行政工作部门设置情况 部门名称", false);
					Double bianzhishu1 = (Double)getCellValue(row.getCell(XingzhengBumen_BianzhiRenshu_Col), ExcelParser.intType, orgBaseInfoFileName, "行政工作部门设置情况 编制人数", false);
					Double shiyoushu1 = (Double)getCellValue(row.getCell(XingzhengBumen_ShiyouRenshu_Col), ExcelParser.intType, orgBaseInfoFileName, "行政工作部门设置情况 实有人数", false);
					int xingzhengJibie1 = getCellValue(row.getCell(XingzhengBumen_XingzhengJibie_Col), orgBaseInfoFileName, "行政工作部门设置情况 行政级别", false, "行政级别_2", constantsMap);
					
					String bumen2 = (String)getCellValue(row.getCell(CanzhaoGuanliJigou_Bumen_Col), ExcelParser.stringType, orgBaseInfoFileName, "参照管理机构设置情况 部门名称", false);
					Double bianzhishu2 = (Double)getCellValue(row.getCell(CanzhaoGuanliJigou_BianzhiRenshu_Col), ExcelParser.intType, orgBaseInfoFileName, "参照管理机构设置情况 编制人数", false);
					Double shiyoushu2 = (Double)getCellValue(row.getCell(CanzhaoGuanliJigou_ShiyouRenshu_Col), ExcelParser.intType, orgBaseInfoFileName, "参照管理机构设置情况 实有人数", false);
					int xingzhengJibie2 = getCellValue(row.getCell(CanzhaoGuanliJigou_XingzhengJibie_Col), orgBaseInfoFileName, "参照管理机构设置情况 行政级别", false, "行政级别_2", constantsMap);

					String bumen3 = (String)getCellValue(row.getCell(ShiyeDanwei_Bumen_Col), ExcelParser.stringType, orgBaseInfoFileName, "事业单位设置情况 部门名称", false);
					Double bianzhishu3 = (Double)getCellValue(row.getCell(ShiyeDanwei_BianzhiRenshu_Col), ExcelParser.intType, orgBaseInfoFileName, "事业单位设置情况 编制人数", false);
					Double shiyoushu3 = (Double)getCellValue(row.getCell(ShiyeDanwei_ShiyouRenshu_Col), ExcelParser.intType, orgBaseInfoFileName, "事业单位设置情况 实有人数", false);
					int xingzhengJibie3 = getCellValue(row.getCell(ShiyeDanwei_XingzhengJibie_Col), orgBaseInfoFileName, "事业单位设置情况 行政级别", false, "行政级别_2", constantsMap);
					
					if(bumen1 != null && !bumen1.isEmpty() && bianzhishu1 != null && shiyoushu1 != null && xingzhengJibie1 != -1) {
						XingzhengBumen entity = new XingzhengBumen();
						entity.setName(bumen1);
						entity.setBianzhirenshu(bianzhishu1.intValue());
						entity.setShijirenshu(shiyoushu1.intValue());
						entity.setXingzhengjibie(xingzhengJibie1);
						xingzhengBumenLst.add(entity);
					} else if((bumen1 == null || bumen1.isEmpty()) && bianzhishu1 == null && shiyoushu1 == null && xingzhengJibie1 == -1) {
					} else {
						throw new Exception(orgBaseInfoFileName + "行政工作部门设置情况第" + (i + 1) + "行信息输入不完全，请确认。");
					}

					if(bumen2 != null && !bumen2.isEmpty() && bianzhishu2 != null && shiyoushu2 != null && xingzhengJibie2 != -1) {
						GuanliJigou entity = new GuanliJigou();
						entity.setName(bumen2);
						entity.setBianzhirenshu(bianzhishu2.intValue());
						entity.setShijirenshu(shiyoushu2.intValue());
						entity.setXingzhengjibie(xingzhengJibie2);
						guanliJigouLst.add(entity);
					} else if((bumen2 == null || bumen2.isEmpty()) && bianzhishu2 == null && shiyoushu2 == null && xingzhengJibie2 == -1) {
					} else {
						throw new Exception(orgBaseInfoFileName + "参照管理机构设置情况第" + (i + 1) + "行信息输入不完全，请确认。");
					}

					if(bumen3 != null && !bumen3.isEmpty() && bianzhishu3 != null && shiyoushu3 != null && xingzhengJibie3 != -1) {
						ShiyeDanwei entity = new ShiyeDanwei();
						entity.setName(bumen3);
						entity.setBianzhirenshu(bianzhishu3.intValue());
						entity.setShijirenshu(shiyoushu3.intValue());
						entity.setXingzhengjibie(xingzhengJibie3);
						shiyeDanweiLst.add(entity);
					} else if((bumen1 == null || bumen1.isEmpty()) && bianzhishu1 == null && shiyoushu1 == null && xingzhengJibie1 == -1) {
					} else {
						throw new Exception(orgBaseInfoFileName + "事业单位设置情况第" + (i + 1) + "行信息输入不完全，请确认。");
					}
				}
				if(xingzhengBumenLst.size() > 0) {
					orgnization.setXingzhengBumenLst(xingzhengBumenLst);
				}
				if(guanliJigouLst.size() > 0) {
					orgnization.setGuanliJigouLst(guanliJigouLst);
				}
				if(shiyeDanweiLst.size() > 0) {
					orgnization.setShiyeDanweiLst(shiyeDanweiLst);
				}
								
				if(cell.getCellType() != HSSFCell.CELL_TYPE_STRING || !JigouShezhiEndString.equals(cell.getStringCellValue())) {
					throw new Exception(orgBaseInfoFileName + "文件格式不正确（保密基础设施建设部分），请确认！");
				}
				
				List<JichuSheshi> jichuSheshiLst = new ArrayList<JichuSheshi>();
				jichuSheshiTileRowIndex += 1;
				int baomibanInfoTitleRow = -1; // 用于读取保密办附加信息、保密学院附加信息和最终行信息
				for(int i = 1; ; ++i) {
					if(lastRow < (i + jichuSheshiTileRowIndex)) {
						throw new Exception(orgBaseInfoFileName + "文件格式不正确，请确认。");
					}
					row = sheet.getRow(jichuSheshiTileRowIndex + i);
					cell = row.getCell(0);
					if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						continue;
					}
					if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						baomibanInfoTitleRow = jichuSheshiTileRowIndex + i;
						break;
					}
					JichuSheshi entity = new JichuSheshi();
					String name = (String)getCellValue(row.getCell(Sheshi_Danwei_Col), ExcelParser.stringType,  orgBaseInfoFileName, "保密基础设施 单位名称", false);
					int type = getCellValue(row.getCell(Sheshi_Leibie_Col), orgBaseInfoFileName, "保密基础设施 基础设施类别", false, "基础设施类别", constantsMap);
					Double mianji = (Double)getCellValue(row.getCell(Sheshi_Mianji_Col), ExcelParser.doubleType,  orgBaseInfoFileName, "保密基础设施 占地面积", false);
					Double zijin = (Double)(Double)getCellValue(row.getCell(Sheshi_Zijin_Col), ExcelParser.doubleType,  orgBaseInfoFileName, "保密基础设施 投入资金", false);
					String jiansheDanwei = (String)getCellValue(row.getCell(Sheshi_ShigongDanwei_Col), ExcelParser.stringType,  orgBaseInfoFileName, "保密基础设施 施工单位名称", false);
					if(name != null && !name.isEmpty() && type != -1 && mianji != null && zijin != null && jiansheDanwei != null && !jiansheDanwei.isEmpty()) {
						entity.setName(name);
						entity.setLeibie(type);
						entity.setMianji(mianji.doubleValue());
						entity.setTouruzijin(zijin.doubleValue());
						entity.setJianshedanwei(jiansheDanwei);
						jichuSheshiLst.add(entity);
					} else if((name == null || name.isEmpty()) && type == -1 && mianji == null && zijin == null && (jiansheDanwei == null || jiansheDanwei.isEmpty())) {
					} else {
						throw new Exception(orgBaseInfoFileName + "保密基础设施第" + i + "行信息输入不完全，请确认。");
					}
				}
				orgnization.setJichuSheshiLst(jichuSheshiLst);
				
				if(!BaomibanInfo_Title.equals(cell.getStringCellValue())) {
					throw new Exception(orgBaseInfoFileName + "文件格式不正确，请确认。");
				}
				
				if(orgnization.getJigoufenlei1() == JGFL1_Baomiban) {
					row = sheet.getRow(baomibanInfoTitleRow + 1);
					orgnization.setBianzhishu(((Double)getCellValue(row.getCell(Bianzhishu_Col), ExcelParser.intType, orgBaseInfoFileName, "编制数", true)).intValue());
					orgnization.setRenshu(((Double)getCellValue(row.getCell(Renshu_Col), ExcelParser.intType, orgBaseInfoFileName, "人数", true)).intValue());
					orgnization.setZhuanzhiganbushu(((Double)getCellValue(row.getCell(ZhuanzhiGanbushu_Col), ExcelParser.intType, orgBaseInfoFileName, "专职干部数", true)).intValue());
				}
				
				if(orgnization.getJigoufenlei1() == JGFL1_Qita && orgnization.getJigoufenlei2() == JGFL2_Baomixueyuan) {
					row = sheet.getRow(baomibanInfoTitleRow + 3);
					orgnization.setXueshengshu(((Double)getCellValue(row.getCell(baomiXueyuan_Col), ExcelParser.intType, orgBaseInfoFileName, "学生数", true)).intValue());
//					orgnization.setJiuyeqingkuang((String)getCellValue(row.getCell(baomiXueyuan_Col), ExcelParser.stringType, orgBaseInfoFileName, "就业情况", true)));
				}
				
				row = sheet.getRow(baomibanInfoTitleRow + 5);
				orgnization.setTianbiaoren((String)getCellValue(row.getCell(tianbiaoren_Col), ExcelParser.stringType,  orgBaseInfoFileName, "填表人", true));
				orgnization.setTianbiaoriqi(getDateCellValue(row.getCell(tianbiaoRiqi_Col),  orgBaseInfoFileName, "填表日期", true));
				
				orgMap.put(Key_Org,  orgnization);
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		private static final int BaseInfo_Row_1 = 4; // 基本信息 第一行
		private static final int BaseInfo_Name_VAL_Col = 2; // 基本信息 机构名称 Val
		private static final int BaseInfo_SuName_VAL_Col = 8; // 基本信息 上级单位 Val
		private static final int BaseInfo_Row_2 = 5; // 基本信息 第二行
		private static final int BaseInfo_Fenlei1_VAL_Col = 2; // 基本信息 机构分类1 Val
		private static final int BaseInfo_Fenlei2_VAL_Col = 5; // 基本信息 机构分类2 Val
		private static final int BaseInfo_ChengliShijian_VAL_Col = 8; // 基本信息 成立时间 Val
		private static final int BaseInfo_XingzhengJibie_VAL_Col = 12; // 基本信息 行政级别 Val
		private static final int BaseInfo_Row_3 = 6; // 基本信息 第三行
		private static final int BaseInfo_ZhengfuXulie_VAL_Col = 2; // 基本信息 是否政府序列 Val
		private static final int BaseInfo_JingfeiLaiyuan_VAL_Col = 8; // 基本信息 经费来源 Val
		private static final int BaseInfo_JigouLeibie_VAL_Col = 12; // 基本信息 机构类别 Val
//		private final String[]  ColumnName_JigouShezhi = {"部门名称", "编制人数", "实有人数", "行政级别"};
		private static final int XingzhengBumen_Bumen_Col = 1; // 行政工作部门设置情况 部门名称
		private static final int XingzhengBumen_BianzhiRenshu_Col = 2; // 行政工作部门设置情况 编制人数
		private static final int XingzhengBumen_ShiyouRenshu_Col = 3; // 行政工作部门设置情况 实有人数
		private static final int XingzhengBumen_XingzhengJibie_Col = 4; // 行政工作部门设置情况 行政级别

		private static final int CanzhaoGuanliJigou_Bumen_Col = 5; // 参照管理机构设置情况 部门名称
		private static final int CanzhaoGuanliJigou_BianzhiRenshu_Col = 6; // 参照管理机构设置情况 编制人数
		private static final int CanzhaoGuanliJigou_ShiyouRenshu_Col = 7; // 参照管理机构设置情况 实有人数
		private static final int CanzhaoGuanliJigou_XingzhengJibie_Col = 8; // 参照管理机构设置情况 行政级别

		private static final int ShiyeDanwei_Bumen_Col = 9; // 事业单位设置情况 部门名称
		private static final int ShiyeDanwei_BianzhiRenshu_Col = 10; // 事业单位设置情况 编制人数
		private static final int ShiyeDanwei_ShiyouRenshu_Col = 11; // 事业单位设置情况 实有人数
		private static final int ShiyeDanwei_XingzhengJibie_Col = 12; // 事业单位设置情况 行政级别

		private static final String JigouShezhiEndString = "保密基础设施建设";
		private static final int Sheshi_Danwei_Col = 1; //  保密基础设施建设 单位名称
		private static final int Sheshi_Leibie_Col = 3; //  保密基础设施建设 基础设施类别
		private static final int Sheshi_Mianji_Col = 5; //  保密基础设施建设 占地面积
		private static final int Sheshi_Zijin_Col = 6; //  保密基础设施建设 投入资金
		private static final int Sheshi_ShigongDanwei_Col = 9; //  保密基础设施建设 施工单位名称
		
		private static final String BaomibanInfo_Title = "保密办附加信息（机构分类为保密办时必须填写）"; 
		private static final int Bianzhishu_Col = 2; // 保密办 编制数
		private static final int Renshu_Col = 5; // 保密办 人数
		private static final int ZhuanzhiGanbushu_Col = 9; // 保密办 专职干部数
		
		private static final int baomiXueyuan_Col = 2; // 
		
//		private static final int Sheheren_Label_Col = 1; // 审核人标题列
//		private static final String Shenheren_Label = "审核人:"; // 审核人标题
		private static final int tianbiaoren_Col = 6; // 填表人
		private static final int tianbiaoRiqi_Col = 9; // 填表日期
		
		private static final int JGFL1_Baomiban = 2;
		private static final int JGFL1_Qita = 3;
		private static final int JGFL2_Baomixueyuan = 3;
	}

	class LingdaoBanziInfoParser extends ExcelParser {
		public void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap)  throws Exception {
			InputStream inputStream = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;

			try {
				inputStream = new FileInputStream(fileName);
				try {
					workbook = new HSSFWorkbook(inputStream);
				} catch(FileNotFoundException e) {
					throw new Exception("不能读取文件" + lingdaoBanziInfoFileName);
				}
				sheet = workbook.getSheetAt(0);
				int lastRow = sheet.getLastRowNum();		
				_logger.debug("最大行数：" + lastRow);

				List<Person> persons = new ArrayList<Person>();
				for(int i = 1; ; ++i) {
					if(lastRow < (i + Start_Row)) {
						throw new Exception(lingdaoBanziInfoFileName + "文件格式不正确，请确认。");
					}
					row = sheet.getRow(Start_Row + i);
					cell = row.getCell(0);
					
					if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						break;
					}

					Person entity = new Person(); 
					entity.setZhiwu(getCellValue(row.getCell(1), lingdaoBanziInfoFileName, "职务", true, "职务", constantsMap));
					entity.setName((String)getCellValue(row.getCell(2), ExcelParser.stringType, lingdaoBanziInfoFileName, "姓名", true));
					entity.setXingbie(getCellValue(row.getCell(3), lingdaoBanziInfoFileName, "性别", true, "性别", constantsMap));
					entity.setChushengnianyue(getDateCellValue(row.getCell(4), lingdaoBanziInfoFileName, "出生年月", true));
					entity.setXueli(getCellValue(row.getCell(5), lingdaoBanziInfoFileName, "学历", true, "学历", constantsMap));
					entity.setZhengzhimianmao(getCellValue(row.getCell(6), lingdaoBanziInfoFileName, "政治面貌", true, "政治面貌", constantsMap));
					entity.setZhuanye((String)getCellValue(row.getCell(7), ExcelParser.stringType, lingdaoBanziInfoFileName, "专业", true));
					entity.setXingzhengjibie(getCellValue(row.getCell(8), lingdaoBanziInfoFileName, "行政级别", true, "行政级别", constantsMap));
					entity.setJishuzhicheng(getCellValue(row.getCell(9), lingdaoBanziInfoFileName, "技术职称", true, "技术职称", constantsMap));
					entity.setRenmingdanwei((String)getCellValue(row.getCell(10), ExcelParser.stringType, lingdaoBanziInfoFileName, "任命单位", true));
					entity.setJishuzhicheng(getCellValue(row.getCell(11), lingdaoBanziInfoFileName, "是否专职", true, "是否专职", constantsMap));
					entity.setJianrenqitazhiwu((String)getCellValue(row.getCell(12), ExcelParser.stringType, lingdaoBanziInfoFileName, "兼任其他职务情况", false));
					String riqiString = (String)getCellValue(row.getCell(13), ExcelParser.stringType, lingdaoBanziInfoFileName, "何时从事保密工作", true);
					Date riqi = null;
					try {
						riqi = new SimpleDateFormat("yyyy年").parse(riqiString);
					} catch(ParseException e) {
						throw new Exception(lingdaoBanziInfoFileName + "文件数据不正确(何时从事保密工作 格式不是 YYYY年 格式)，请检查。");
					}
					entity.setKaishiriqi(new java.sql.Date(riqi.getTime()));
					entity.setSuoshubumen((String)getCellValue(row.getCell(14), ExcelParser.stringType, lingdaoBanziInfoFileName, "部门名称", false));
					entity.setDanweimingcheng((String)getCellValue(row.getCell(15), ExcelParser.stringType, lingdaoBanziInfoFileName, "单位名称", false));
					
					persons.add(entity);
				}
				if(_logger.isDebugEnabled()) {
					_logger.debug("领导班子:" + persons);
				}
				orgMap.put(Key_Lingdaobanzi,  persons);
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		private static final int Start_Row = 4; // 起始行
	}

	class RenyuanBianzhiInfoParser extends ExcelParser {
		public void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap)  throws Exception {
			InputStream inputStream = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;

			try {
				inputStream = new FileInputStream(fileName);
				try {
					workbook = new HSSFWorkbook(inputStream);
				} catch(FileNotFoundException e) {
					throw new Exception("不能读取文件" + renyuanBianzhiInfoFileName);
				}
				sheet = workbook.getSheetAt(0);
				int lastRow = sheet.getLastRowNum();		
				_logger.debug("最大行数：" + lastRow);
				if(Max_Row != lastRow) {
					throw new Exception(renyuanBianzhiInfoFileName + "文件格式不正确，请确认。");
				}

				List<RenyuanBianzhiMingxi> mingxiLst = new ArrayList<RenyuanBianzhiMingxi>();
				for(int i = 0; i < (Max_Row - Start_Row - 1); ++i) {
					if(lastRow < (i + Start_Row)) {
						throw new Exception(renyuanBianzhiInfoFileName + "文件格式不正确，请确认。");
					}
					row = sheet.getRow(Start_Row + i);

					Double code = (Double)getCellValue(row.getCell(Code_Col), ExcelParser.intType, renyuanBianzhiInfoFileName, "第" + (Start_Row + i + 1) + "行代码", true);
					if((Min_Code + i) != code.intValue()) {
						throw new Exception(renyuanBianzhiInfoFileName + "第" + (Start_Row + i+1) + "行数据不正确(代码与预期代码不一致)，请检查。");
					}
					
					Double val = (Double)getCellValue(row.getCell(Val_Col), ExcelParser.intType, renyuanBianzhiInfoFileName, "第" + (Start_Row + i+1) + "行数量", true);

					RenyuanBianzhiMingxi entity = new RenyuanBianzhiMingxi();
					entity.setCode(code.intValue());
					entity.setNum(val.intValue());
					
					mingxiLst.add(entity);
				}
				
				if(_logger.isDebugEnabled()) {
					_logger.debug("人员编制:" + mingxiLst);
				}
				orgMap.put(Key_Renyuanbanzi,  mingxiLst);
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		private static final int Max_Row = 23; // 最大行数
		private static final int Start_Row = 7; // 开始行号
		private static final int Min_Code = 101; // 最小代码
		private static final int Code_Col = 6; // 代码列
		private static final int Val_Col = 7; // 数值列
	}

	class ZaibianRenyuanInfoParser extends ExcelParser {
		public void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap)  throws Exception {
			InputStream inputStream = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;

			try {
				inputStream = new FileInputStream(fileName);
				try {
					workbook = new HSSFWorkbook(inputStream);
				} catch(FileNotFoundException e) {
					throw new Exception("不能读取文件" + zaibianRenyuanInfoFileName);
				}
				sheet = workbook.getSheetAt(0);
				int lastRow = sheet.getLastRowNum();		
				_logger.debug("最大行数：" + lastRow);
				if(Max_Row != lastRow) {
					throw new Exception(zaibianRenyuanInfoFileName + "文件格式不正确，请确认。");
				}

				List<ZaibianRenyuanMingxi> mingxiLst = new ArrayList<ZaibianRenyuanMingxi>();
				for(int i = 0; i < Rows.length; ++i) {
					row = sheet.getRow(Rows[i]);

					cell = row.getCell(Code_Col);
					if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
						throw new Exception(zaibianRenyuanInfoFileName + "第" + (Rows[i]+1) + "行数据不正确(代码)，请检查。");
					}
					Double code = (Double)getCellValue(row.getCell(Code_Col), ExcelParser.intType, zaibianRenyuanInfoFileName, "第" + (Rows[i] + 1) + "行代码", true);
					if((Min_Code + i) != code.intValue()) {
						throw new Exception(zaibianRenyuanInfoFileName + "第" + (Rows[i]+1) + "行数据不正确(代码与预期代码不一致)，请检查。");
					}
					
					Double val = (Double)getCellValue(row.getCell(Val_Col), ExcelParser.intType, zaibianRenyuanInfoFileName, "第" + (Rows[i] + 1) + "行数量", true);

					ZaibianRenyuanMingxi entity = new ZaibianRenyuanMingxi();
					entity.setCode(code.intValue());
					entity.setNum(val.intValue());
					
					mingxiLst.add(entity);
				}
				
				if(_logger.isDebugEnabled()) {
					_logger.debug("在编人员:" + mingxiLst);
				}
				orgMap.put(Key_Zaibianrenyuan,  mingxiLst);
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		private static final int Max_Row = 29; // 最大行数
		private static final int Min_Code = 101; // 最小代码
		private static final int Code_Col = 6; // 代码列
		private static final int Val_Col = 7; // 数值列
		private final int[] Rows  = {6, 8, 9, 10, 11, 13, 14, 15, 16, 18, 19, 20, 21, 22, 24, 25, 26, 27};
	}

	class BaomiGanbuInfoParser extends ExcelParser {
		public void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap)  throws Exception {
			InputStream inputStream = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;

			try {
				inputStream = new FileInputStream(fileName);
				try {
					workbook = new HSSFWorkbook(inputStream);
				} catch(FileNotFoundException e) {
					throw new Exception("不能读取文件" + baomiGanbuInfoFileName);
				}
				sheet = workbook.getSheetAt(0);
				int lastRow = sheet.getLastRowNum();		
				_logger.debug("最大行数：" + lastRow);

				Orgnization org = (Orgnization)orgMap.get(Key_Org);

				row = sheet.getRow(BaseInfo_Row);
				// 机构类别
				int orgType = getCellValue(row.getCell(JigouLeibie_Col), baomiGanbuInfoFileName, "机构类别", true, "机构类别", constantsMap);
				if(orgType != org.getJigouleibie()) {
					throw new Exception("机构类别与保密行政部门及机构设置基本情况填报表中不一致，请确认。");
				}
				// 行政级别
				int orgXzjb = getCellValue(row.getCell(XingzhengJibie_Col), baomiGanbuInfoFileName, "行政级别", true, "行政级别_1", constantsMap);
				if(orgXzjb != org.getXingzhengjibie()) {
					throw new Exception("行政级别与保密行政部门及机构设置基本情况填报表中不一致，请确认。");
				}
				
				// 编制人数
				Double orgBzrs = (Double)getCellValue(row.getCell(BianzhiRenshu_Col), ExcelParser.intType, baomiGanbuInfoFileName, "编制人数", true);
				org.setBaomiganbubianzhirenshu(orgBzrs.intValue());
				
				List<Person> persons = new ArrayList<Person>();
				for(int i = 1; ; ++i) {
					if(lastRow < (i + Start_Row)) {
						throw new Exception(baomiGanbuInfoFileName + "文件格式不正确，请确认。");
					}
					row = sheet.getRow(Start_Row + i);
					cell = row.getCell(0);
					
					if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						break;
					}

					Person entity = new Person(); 
					entity.setZhiwu(getCellValue(row.getCell(1), baomiGanbuInfoFileName, "职务", true, "职务", constantsMap));
					entity.setName((String)getCellValue(row.getCell(2), ExcelParser.stringType, baomiGanbuInfoFileName, "姓名", true));
					entity.setXingbie(getCellValue(row.getCell(3), baomiGanbuInfoFileName, "性别", true, "性别", constantsMap));
					entity.setChushengnianyue(getDateCellValue(row.getCell(4), baomiGanbuInfoFileName, "出生年月", true));
					entity.setXueli(getCellValue(row.getCell(5), baomiGanbuInfoFileName, "学历", true, "学历", constantsMap));
					entity.setZhengzhimianmao(getCellValue(row.getCell(6), baomiGanbuInfoFileName, "政治面貌", true, "政治面貌", constantsMap));
					entity.setZhuanye((String)getCellValue(row.getCell(7), ExcelParser.stringType, baomiGanbuInfoFileName, "专业", true));
					entity.setXingzhengjibie(getCellValue(row.getCell(8), baomiGanbuInfoFileName, "行政级别", true, "行政级别", constantsMap));
					entity.setJishuzhicheng(getCellValue(row.getCell(9), baomiGanbuInfoFileName, "技术职称", true, "技术职称", constantsMap));
					entity.setJishuzhicheng(getCellValue(row.getCell(10), baomiGanbuInfoFileName, "是否专职", true, "是否专职", constantsMap));
					entity.setJianrenqitazhiwu((String)getCellValue(row.getCell(11), ExcelParser.stringType, baomiGanbuInfoFileName, "其他职务", false));
					String riqiString = (String)getCellValue(row.getCell(12), ExcelParser.stringType, baomiGanbuInfoFileName, "何时从事保密工作", true);
					Date riqi = null;
					try {
						riqi = new SimpleDateFormat("yyyy年").parse(riqiString);
					} catch(ParseException e) {
						throw new Exception(baomiGanbuInfoFileName + "文件数据不正确(何时从事保密工作 格式不是 YYYY年 格式)，请检查。");
					}
					entity.setKaishiriqi(new java.sql.Date(riqi.getTime()));
					entity.setTel((String)getCellValue(row.getCell(13), ExcelParser.stringType, baomiGanbuInfoFileName, "联系座机电话", true));
					Double mobileNum = (Double)getCellValue(row.getCell(14), ExcelParser.intType, baomiGanbuInfoFileName, "手机", false);
					entity.setMobilenum(mobileNum == null ? null : String.valueOf(mobileNum.longValue()));
					entity.setSuoshubumen((String)getCellValue(row.getCell(15), ExcelParser.stringType, baomiGanbuInfoFileName, "部门名称", false));
					entity.setDanweimingcheng((String)getCellValue(row.getCell(16), ExcelParser.stringType, baomiGanbuInfoFileName, "单位名称", false));
					
					persons.add(entity);
				}
				if(_logger.isDebugEnabled()) {
					_logger.debug("保密干部:" + persons);
				}
				orgMap.put(Key_Baomiganbu,  persons);
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		private static final int BaseInfo_Row = 5;
		private static final int JigouLeibie_Col = 2; // 机构类别
		private static final int XingzhengJibie_Col = 5; // 行政级别
		private static final int BianzhiRenshu_Col = 8; // 编制人数
		private static final int Start_Row = 6; // 起始行
	}

	class ShemiRenyuanInfoParser extends ExcelParser {
		public void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap)  throws Exception {
			InputStream inputStream = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;

			try {
				inputStream = new FileInputStream(fileName);
				try {
					workbook = new HSSFWorkbook(inputStream);
				} catch(FileNotFoundException e) {
					throw new Exception("不能读取文件" + shemiRenyuanInfoFileName);
				}
				sheet = workbook.getSheetAt(0);
				int lastRow = sheet.getLastRowNum();		
				_logger.debug("最大行数：" + lastRow);

				List<Person> persons = new ArrayList<Person>();
				for(int i = 1; ; ++i) {
					if(lastRow < (i + Start_Row)) {
						throw new Exception(shemiRenyuanInfoFileName + "文件格式不正确，请确认。");
					}
					row = sheet.getRow(Start_Row + i);
					cell = row.getCell(0);
					
					if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						break;
					}

					Person entity = new Person(); 
					entity.setName((String)getCellValue(row.getCell(1), ExcelParser.stringType, shemiRenyuanInfoFileName, "姓名", true));
					entity.setXingbie(getCellValue(row.getCell(2), shemiRenyuanInfoFileName, "性别", true, "性别", constantsMap));
					entity.setChushengnianyue(getDateCellValue(row.getCell(3), shemiRenyuanInfoFileName, "出生年月", true));
					entity.setMinzu(getCellValue(row.getCell(4), shemiRenyuanInfoFileName, "民族", true, "民族", constantsMap));
					entity.setZhengzhimianmao(getCellValue(row.getCell(5), shemiRenyuanInfoFileName, "政治面貌", true, "政治面貌", constantsMap));
					entity.setXueli(getCellValue(row.getCell(6), shemiRenyuanInfoFileName, "学历", true, "学历", constantsMap));
					entity.setShemidengji(getCellValue(row.getCell(7), shemiRenyuanInfoFileName, "涉密等级", true, "涉密等级", constantsMap));
					entity.setXingzhengjibie(getCellValue(row.getCell(8), shemiRenyuanInfoFileName, "行政级别", true, "行政级别", constantsMap));
					entity.setJishuzhicheng(getCellValue(row.getCell(9), shemiRenyuanInfoFileName, "技术职称", true, "技术职称", constantsMap));
					entity.setYaohaibumengrenyuan(getCellValue(row.getCell(10), shemiRenyuanInfoFileName, "是否属于要害部门工作人员", true, "是否要害部门人员", constantsMap));
					entity.setRenyuanleixing(getCellValue(row.getCell(11), shemiRenyuanInfoFileName, "人员类型", true, "人员类型", constantsMap));
					entity.setSuoshubumen((String)getCellValue(row.getCell(12), ExcelParser.stringType, shemiRenyuanInfoFileName, "部门名称", false));
					entity.setDanweimingcheng((String)getCellValue(row.getCell(13), ExcelParser.stringType, shemiRenyuanInfoFileName, "单位名称", false));
					
					persons.add(entity);
				}
				if(_logger.isDebugEnabled()) {
					_logger.debug("涉密人员:" + persons);
				}
				orgMap.put(Key_Shemirenyuan,  persons);
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		private static final int Start_Row = 5; // 起始行
	}

	class ResumeParser extends ExcelParser {
		public void parseFile(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap)  throws Exception {
			InputStream inputStream = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			HSSFRow row = null;
			HSSFCell cell = null;

			String displayFileName = fileName;
			
			ResumeForImport resume = new ResumeForImport();
			resume.setPersonInfo(new Person());
			resume.setResumeInfo(new Resume());
			try {
				inputStream = new FileInputStream(fileName);
				try {
					workbook = new HSSFWorkbook(inputStream);
				} catch(FileNotFoundException e) {
					throw new Exception("不能读取文件" + shemiRenyuanInfoFileName);
				}
				sheet = workbook.getSheetAt(0);
				int lastRow = sheet.getLastRowNum();		
				_logger.debug("最大行数：" + lastRow);

				row = sheet.getRow(Name_Row);
				resume.getPersonInfo().setName((String)getCellValue(row.getCell(Name_Col), ExcelParser.stringType, displayFileName, "姓名", true));

				row = sheet.getRow(Gender_Row);
				resume.getPersonInfo().setXingbie(getCellValue(row.getCell(Gender_Col), displayFileName, "性别", true, "性别", constantsMap));

				row = sheet.getRow(Minzu_Row);
				resume.getPersonInfo().setMinzu(getCellValue(row.getCell(Minzu_Col), displayFileName, "民族", true, "民族", constantsMap));

				row = sheet.getRow(Birthday_Row);
				resume.getPersonInfo().setChushengnianyue(getDateCellValue(row.getCell(Birthday_Col), displayFileName, "出生年月", true));

				row = sheet.getRow(Zhengzhimianmao_Row);
				resume.getPersonInfo().setZhengzhimianmao(getCellValue(row.getCell(Zhengzhimianmao_Col), displayFileName, "政治面貌", true, "政治面貌", constantsMap));

				row = sheet.getRow(Gongzuoshijian_Row);
				String riqiString = (String)getCellValue(row.getCell(Gongzuoshijian_Col), ExcelParser.stringType, displayFileName, "参加工作时间", true);
				Date riqi = null;
				try {
					riqi = new SimpleDateFormat("yyyy年").parse(riqiString);
				} catch(ParseException e) {
					throw new Exception(lingdaoBanziInfoFileName + "文件数据不正确(参加工作时间 格式不是 YYYY年 格式)，请检查。");
				}
				resume.getPersonInfo().setCanjiagongzuoshijian(new java.sql.Date(riqi.getTime()));
//				resume.getPersonInfo().setCanjiagongzuoshijian(getDateCellValue(row.getCell(Gongzuoshijian_Col), displayFileName, "参加工作时间", true));

				row = sheet.getRow(Jishuzhiwu_Row);
				resume.getResumeInfo().setZhuanyejishuzhiwu((String)getCellValue(row.getCell(Jishuzhiwu_Col), ExcelParser.stringType, displayFileName, "专业技术职务", true));

				row = sheet.getRow(Zhuanchang_Row);
				resume.getResumeInfo().setZhuanchang((String)getCellValue(row.getCell(Zhuanchang_Col), ExcelParser.stringType, displayFileName, "熟悉专业有何专长", true));

				XueliMingxi qrzXueli = new XueliMingxi();
				row = sheet.getRow(Qrz_Xuewei_Row);
				qrzXueli.setXueli(getCellValue(row.getCell(Qrz_Xuewei_Col), displayFileName, "全日制学历", true, "学历", constantsMap));
				row = sheet.getRow(Qrz_Biyexuexiao_Row);
				qrzXueli.setBiyeyuanxiao((String)getCellValue(row.getCell(Qrz_Biyexuexiao_Col), ExcelParser.stringType, displayFileName, "全日制毕业院校", true));
				row = sheet.getRow(Qrz_Zhuanye_Row);
				qrzXueli.setZhuanye((String)getCellValue(row.getCell(Qrz_Zhuanye_Col), ExcelParser.stringType, displayFileName, "全日制专业", true));
				if(qrzXueli.getXueli() != -1 && qrzXueli.getBiyeyuanxiao() != null && !qrzXueli.getBiyeyuanxiao().isEmpty() && qrzXueli.getZhuanye() != null && !qrzXueli.getZhuanye().isEmpty()) {
					resume.setQuanrizhiXueli(qrzXueli);
				} else if(qrzXueli.getXueli() == -1 && (qrzXueli.getBiyeyuanxiao() == null || qrzXueli.getBiyeyuanxiao().isEmpty()) && (qrzXueli.getZhuanye() == null || qrzXueli.getZhuanye().isEmpty())) {
				} else {
					throw new Exception(displayFileName + "全日制学历信息输入不正确，请确认。");
				}
				
				XueliMingxi zzXueli = new XueliMingxi();
				row = sheet.getRow(Zz_Xuewei_Row);
				zzXueli.setXueli(getCellValue(row.getCell(Zz_Xuewei_Col), displayFileName, "在职学历", true, "学历", constantsMap));
				row = sheet.getRow(Zz_Biyexuexiao_Row);
				zzXueli.setBiyeyuanxiao((String)getCellValue(row.getCell(Zz_Biyexuexiao_Col), ExcelParser.stringType, displayFileName, "在职毕业院校", true));
				row = sheet.getRow(Zz_Zhuanye_Row);
				zzXueli.setZhuanye((String)getCellValue(row.getCell(Zz_Zhuanye_Col), ExcelParser.stringType, displayFileName, "在职专业", true));
				if(zzXueli.getXueli() != -1 && zzXueli.getBiyeyuanxiao() != null && !zzXueli.getBiyeyuanxiao().isEmpty() && zzXueli.getZhuanye() != null && !zzXueli.getZhuanye().isEmpty()) {
					resume.setZaizhiXueli(zzXueli);
				} else if(zzXueli.getXueli() == -1 && (zzXueli.getBiyeyuanxiao() == null || zzXueli.getBiyeyuanxiao().isEmpty()) && (zzXueli.getZhuanye() == null || zzXueli.getZhuanye().isEmpty())) {
				} else {
					throw new Exception(displayFileName + "全日制学历信息输入不正确，请确认。");
				}
				
				row = sheet.getRow(Zhiwu_Row);
				resume.getPersonInfo().setZhiwu(getCellValue(row.getCell(Zhiwu_Col), displayFileName, "现任职务", true, "职务", constantsMap));

				row = sheet.getRow(Jianli_Row);
				resume.getResumeInfo().setJianli((String)getCellValue(row.getCell(Jianli_Col), ExcelParser.stringType, displayFileName, "简历", true));

				Jiangcheng jiangcheng = new Jiangcheng();
				row = sheet.getRow(Jiangcheng_Row);
				jiangcheng.setContent((String)getCellValue(row.getCell(Jiangcheng_Col), ExcelParser.stringType, displayFileName, "奖惩情况", true));
				resume.setJiangchengInfo(jiangcheng);

				Chengguo chengguo = new Chengguo();
				row = sheet.getRow(Chengguo_Row);
				chengguo.setContent((String)getCellValue(row.getCell(Chengguo_Col), ExcelParser.stringType, displayFileName, "主要成果", true));
				resume.setChengguoInfo(chengguo);

				List<JiatingChengyuan> jiatingchengyuanLst = new ArrayList<JiatingChengyuan>();
				for(int i = 1; ; ++i) {
					if(lastRow == (i + Familly_Row)) {
						throw new Exception(displayFileName + "文件格式不正确，请确认。");
					}
					
					row = sheet.getRow(Familly_Row + i);
					cell = row.getCell(Comment_Label_Col);
					if(cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING && Comment_Label.equals(cell.getStringCellValue())) {
						break;
					}
					
					cell = row.getCell(Familly_Col1);
					if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						break;
					}

					JiatingChengyuan entity = new JiatingChengyuan(); 
					entity.setChengwei((String)getCellValue(row.getCell(Familly_Col1), ExcelParser.stringType, displayFileName, "称谓", true));
					entity.setXingming((String)getCellValue(row.getCell(Familly_Col2), ExcelParser.stringType, displayFileName, "姓名", true));
					Double age = (Double)getCellValue(row.getCell(Familly_Col3), ExcelParser.intType, displayFileName, "年龄", true);
					Calendar now = Calendar.getInstance();
					now.setTime(new Date());
					now.add(Calendar.YEAR, 0 - age.intValue());
					entity.setChushengnianfen(new java.sql.Date(now.getTime().getTime()));
					entity.setZhengzhimianmao((String)getCellValue(row.getCell(Familly_Col4), ExcelParser.stringType, displayFileName, "政治面貌", true));
					entity.setGongzuodanweijizhiwu((String)getCellValue(row.getCell(Familly_Col5), ExcelParser.stringType, displayFileName, "工作单位及职务", false));
					
					jiatingchengyuanLst.add(entity);
				}
				resume.setJiatingchengyuanInfo(jiatingchengyuanLst);
				
				row = sheet.getRow(lastRow);
				resume.getResumeInfo().setBeizhu((String)getCellValue(row.getCell(Comment_Col), ExcelParser.stringType, displayFileName, "备注", false));

				((List<ResumeForImport>)orgMap.get(Key_Resume)).add(resume);
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		private static final int Name_Row = 1;
		private static final int Name_Col = 1;
		private static final int Gender_Row = 1;
		private static final int Gender_Col = 6;
		private static final int Minzu_Row = 1;
		private static final int Minzu_Col = 10;
		private static final int Birthday_Row = 2;
		private static final int Birthday_Col = 1;
		private static final int Zhengzhimianmao_Row = 2;
		private static final int Zhengzhimianmao_Col = 6;
		private static final int Gongzuoshijian_Row = 2;
		private static final int Gongzuoshijian_Col = 10;
		private static final int Jishuzhiwu_Row = 3;
		private static final int Jishuzhiwu_Col = 1;
		private static final int Zhuanchang_Row = 3;
		private static final int Zhuanchang_Col = 8;
		private static final int Qrz_Xuewei_Row = 5;
		private static final int Qrz_Xuewei_Col = 3;
		private static final int Qrz_Biyexuexiao_Row = 5;
		private static final int Qrz_Biyexuexiao_Col = 8;
		private static final int Qrz_Zhuanye_Row = 6;
		private static final int Qrz_Zhuanye_Col = 8;
		private static final int Zz_Xuewei_Row = 7;
		private static final int Zz_Xuewei_Col = 3;
		private static final int Zz_Biyexuexiao_Row = 7;
		private static final int Zz_Biyexuexiao_Col = 8;
		private static final int Zz_Zhuanye_Row = 8;
		private static final int Zz_Zhuanye_Col = 8;
		private static final int Zhiwu_Row = 9;
		private static final int Zhiwu_Col = 3;
		private static final int Jianli_Row = 10;
		private static final int Jianli_Col = 1;
		private static final int Jiangcheng_Row = 11;
		private static final int Jiangcheng_Col = 1;
		private static final int Chengguo_Row = 12;
		private static final int Chengguo_Col = 1;
		
		private static final int Familly_Row = 13;
		private static final int Familly_Col1 = 1;
		private static final int Familly_Col2 = 2;
		private static final int Familly_Col3 = 5;
		private static final int Familly_Col4 = 7;
		private static final int Familly_Col5 = 9;
		
		private static final String Comment_Label = "备注";
		private static final int Comment_Label_Col = 0;
		private static final int Comment_Col = 1;
	}

	private void uncompress(String zipFileName, String dirPath, FileMapping fileMapping) throws Exception {
		File openFile = null; 
		ZipFile zipFile = null;
		FileOutputStream outStream = null;
		InputStream inStream = null;
		byte[] buffer = new byte[10240];
		try {
			openFile = new File(zipFileName);
			zipFile = new ZipFile(openFile);
			Enumeration<ZipEntry> entries = zipFile.getEntries();
			while(entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				if(entry.getSize() == 0) {
					continue;
				}
				String fullFilename = dirPath + entry.getName();
				File fileTmp = new File(fullFilename);
				
				createDir(fileTmp.getParent());
				
				inStream = zipFile.getInputStream(entry);
				outStream = new FileOutputStream(fileTmp);
				
				int len = 0;
				while((len = inStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, len);
				}
				
				outStream.flush();
				
				if(fullFilename.contains(orgBaseInfoFileName)) {
					fileMapping.setOrgBaseInfoFile(fullFilename);
				} else if(fullFilename.contains(lingdaoBanziInfoFileName)) {
					fileMapping.setLindaoBanziInfoFile(fullFilename);
				} else if(fullFilename.contains(renyuanBianzhiInfoFileName)) {
					fileMapping.setRenyuanBianzhiInfoFile(fullFilename);
				} else if(fullFilename.contains(zaibianRenyuanInfoFileName)) {
					fileMapping.setZaibianRenyuanInfoFile(fullFilename);
				} else if(fullFilename.contains(baomiGanbuInfoFileName)) {
					fileMapping.setBaomiGanbuInfoFile(fullFilename);
				} else if(fullFilename.contains(shemiRenyuanInfoFileName)) {
					fileMapping.setShemirenyuanInfoFile(fullFilename);
				} else if(fullFilename.contains(fujianFileName)) {
					fileMapping.setFujianFile(fullFilename);
				} else if(fullFilename.contains(resumeFileName)){
					fileMapping.addresumeFile(fullFilename);
				}
			}
		} catch (IOException e) {
			throw new Exception("文件解压失败。");
		} finally {
			if(zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
				}
			}
			if(outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
				}
			}
			if(inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
				}
			}
		}
		
		if(!fileMapping.checkDocumentsCompleted()) {
			throw new Exception("填报表不全，不能导入。");
		}
	}
	
	private void parseOrgnizationBaseInfo(String fileName, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception {
		new OrgnizationBaseInfoParser().parseFile(fileName, constantsMap, orgMap);
	}

	private void parseLingdaoBanziInfo(String file, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception {
		new LingdaoBanziInfoParser().parseFile(file, constantsMap, orgMap);
	}

	private void parseRenyuanBianzhiInfo(String file, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception {
		new RenyuanBianzhiInfoParser().parseFile(file, constantsMap, orgMap);
	}

	private void parseZaibianRenyuanInfo(String file, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception {
		new ZaibianRenyuanInfoParser().parseFile(file, constantsMap, orgMap);
	}

	private void parseBaomiGanbuInfo(String file, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception {
		new BaomiGanbuInfoParser().parseFile(file, constantsMap, orgMap);
	}

	private void parseShemiRenyuanInfo(String file, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception {
		new ShemiRenyuanInfoParser().parseFile(file, constantsMap, orgMap);
	}

	public void parseResumes(List<String> resumeFileNames, Map<String, Map<String, Integer>> constantsMap, Map<String, Object> orgMap) throws Exception {
		orgMap.put("resume", new ArrayList<ResumeForImport>());
		ResumeParser parser = new ResumeParser();
		for(String resumeFileName : resumeFileNames) {
			parser.parseFile(resumeFileName, constantsMap, orgMap);
		}
	}

	private Map<String, Object> parseFiles(FileMapping fileMapping, Map<String, Map<String, Integer>> constantsMap) throws Exception {
		Map<String, Object> orgMap = new HashMap<String, Object>();
		
		parseOrgnizationBaseInfo(fileMapping.getOrgBaseInfoFile(), constantsMap, orgMap);
		parseLingdaoBanziInfo(fileMapping.getLindaoBanziInfoFile(), constantsMap, orgMap);
		parseRenyuanBianzhiInfo(fileMapping.getRenyuanBianzhiInfoFile(), constantsMap, orgMap);
		parseZaibianRenyuanInfo(fileMapping.getZaibianRenyuanInfoFile(), constantsMap, orgMap);
		parseBaomiGanbuInfo(fileMapping.getBaomiGanbuInfoFile(), constantsMap, orgMap);
		parseShemiRenyuanInfo(fileMapping.getShemirenyuanInfoFile(), constantsMap, orgMap);
		parseResumes(fileMapping.getResumes(), constantsMap, orgMap);
		
		_logger.debug(orgMap);
		return orgMap;
	}
	
	Person getPerson(List<Person> allPersonInOrg, Person person) {
		// TODO: 先在allPersonInOrg里找，没找到去DB里找， 都没找到返回null， 找到多个则取第一个
		for(Person personInList : allPersonInOrg) {
			if(	personInList.getName().equals(person.getName()) &&
				personInList.getXingbie() == person.getXingbie() &&
				personInList.getChushengnianyue() == person.getChushengnianyue() &&
				personInList.getZhengzhimianmao() == person.getZhengzhimianmao()) {
				return personInList;
			}
		}
		
		
		return null;
	}
	
	void manageData(Map<String, Object> orgnizationMap) throws Exception {
		Orgnization orgnization = (Orgnization)orgnizationMap.get(Key_Org);
		
		// 1. 机构信息（表六）
		int orgId = 0;
		String suOrgName = orgnization.getSuOrgName();
		if(suOrgName != null && !suOrgName.isEmpty()) { // 上级单位
			Orgnization suOrgnization = orgnizationDao.getOrgnizationByName(suOrgName);
			if(suOrgnization == null) {
				suOrgnization = new Orgnization();
				suOrgnization.setId(sequenceDao.getSequence("orgnization"));
				suOrgnization.setOrgname(suOrgName);
				orgnizationMap.put("suRognization", suOrgnization);
			}
			orgnization.setSuorgid(suOrgnization.getId());
		}
		String orgName = orgnization.getOrgname();
		Orgnization orgnizationInDb = orgnizationDao.getOrgnizationByName(orgName);
		if(orgnizationInDb == null) {
			orgId = sequenceDao.getSequence("orgnization");
		} else {
			orgId = orgnizationInDb.getId();
			orgnizationMap.put(Key_OrgExistedFlg, true);
		}
		orgnization.setId(orgId);
		
		// 2. 人员编制（表十三）
		List<RenyuanBianzhiMingxi> renyuanBianzhiLst = (List<RenyuanBianzhiMingxi>)orgnizationMap.get(Key_Renyuanbanzi);
		for(RenyuanBianzhiMingxi mingxi : renyuanBianzhiLst) {
			mingxi.setOrgid(orgId);
		}
		
		// 3. 在编人员（表十四）
		List<ZaibianRenyuanMingxi> zaibianRenyuanLst = (List<ZaibianRenyuanMingxi>)orgnizationMap.get(Key_Zaibianrenyuan);
		for(ZaibianRenyuanMingxi mingxi : zaibianRenyuanLst) {
			mingxi.setOrgid(orgId);
		}

		List<Person> allPersonInOrg = new ArrayList<Person>();
		// 4. 领导班子成员及工作人员（表十二）
		List<Person> lingdaobanziLst = (List<Person>)orgnizationMap.get(Key_Lingdaobanzi);
		List<R_Person> r_ldbzList = new ArrayList<R_Person>();
		for(Person person : lingdaobanziLst) {
			Person personInDb = getPerson(allPersonInOrg, person);
			if(personInDb == null) {
				person.setId(sequenceDao.getSequence("person"));
				allPersonInOrg.add(person);
			}
			
			R_Person r_ldbz_person = new R_Person();
			r_ldbz_person.setOrgId(orgId);
			r_ldbz_person.setPersonid(person.getId());
			r_ldbzList.add(r_ldbz_person);
		}
		orgnizationMap.put(Key_R_Ldbz_Person, r_ldbzList);
		
		// 5. 保密干部（表十五）
		List<Person> baomiGanbuLst = (List<Person>)orgnizationMap.get(Key_Baomiganbu);
		List<R_Person> r_ganbuList = new ArrayList<R_Person>();
		for(Person person : baomiGanbuLst) {
			Person personInDb = getPerson(allPersonInOrg, person);
			if(personInDb == null) {
				person.setId(sequenceDao.getSequence("person"));
				allPersonInOrg.add(person);
			}
			
			R_Person r_ganbu_person = new R_Person();
			r_ganbu_person.setOrgId(orgId);
			r_ganbu_person.setPersonid(person.getId());
			r_ganbuList.add(r_ganbu_person);
		}
		orgnizationMap.put(Key_R_Ganbu_Person, r_ganbuList);
		
		// 6. 涉密人员（表四）
		List<Person> shemiRenyuanLst = (List<Person>)orgnizationMap.get(Key_Shemirenyuan);
		List<R_Person> r_shemiList = new ArrayList<R_Person>();
		for(Person person : shemiRenyuanLst) {
			Person personInDb = getPerson(allPersonInOrg, person);
			if(personInDb == null) {
				person.setId(sequenceDao.getSequence("person"));
				allPersonInOrg.add(person);
			}
			
			R_Person r_shemi_person = new R_Person();
			r_shemi_person.setOrgId(orgId);
			r_shemi_person.setPersonid(person.getId());
			r_shemiList.add(r_shemi_person);
		}
		orgnizationMap.put(Key_R_Shemi_Person, r_shemiList);
		
		// 7. 简历
		List<ResumeForImport> resumeList = (List<ResumeForImport>)orgnizationMap.get(Key_Resume);
		for(ResumeForImport resumeForImport : resumeList) {
			Person personInDb = getPerson(allPersonInOrg, resumeForImport.getPersonInfo());
			if(personInDb == null) {
				resumeForImport.getPersonInfo().setId(sequenceDao.getSequence("person"));
				allPersonInOrg.add(resumeForImport.getPersonInfo());
			}
		}
	}
	
	void writeDataToDb(Map<String, Object> orgnizationMap) throws Exception {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
		} catch(Exception e) {
			transactionManager.rollback(status);
			_logger.error(e.getMessage());
			throw new Exception("数据写入失败。");
		}
		
		transactionManager.commit(status);
	}
	void writeOrgnizationToDb(Map<String, Object> orgnizationMap) throws Exception {
		manageData(orgnizationMap);
		writeDataToDb(orgnizationMap);
	}
	
	public Map<String, Map<String, Integer>> makeConstantsMap() throws Exception {
		List<SelectItem> items = null;
		try {
			items = constantsDao.getAllConstants();
		} catch(Exception e) {
			throw new Exception("数据库操作失败。");
		}
		
		Map<String, Map<String, Integer>> rtnMap = new HashMap<String, Map<String, Integer>>();
		for(SelectItem item : items) {
			Map<String, Integer> typeMap = rtnMap.get(item.getType());
			if(typeMap == null) {
				rtnMap.put(item.getType(), typeMap = new HashMap<String, Integer>());
			}
			typeMap.put(item.getName(), item.getCode());
		}
		
		return rtnMap;
	}
	
	void deletePath(String dirPath) {
		File f = new File(dirPath);          
		if(f.exists() && f.isDirectory()){  
			if(f.listFiles().length==0){ 
				f.delete();  
			}else{ 
				File delFile[]=f.listFiles();  
				int i =f.listFiles().length;  
				for(int j=0;j<i;j++){  
					if(delFile[j].isDirectory()){  
						deletePath(delFile[j].getAbsolutePath()); 
					}  
					delFile[j].delete();  
				}  
			}  
		}
	}
	
	public boolean importOrgnizationByZipFile(String zipFileName, String basePath) throws Exception {
		// 解压zip文件
		// 创建根目录
		String dirPath = basePath + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "/";

		FileMapping fileMapping = new FileMapping();
		uncompress(zipFileName, dirPath, fileMapping);
		
		Map<String, Map<String, Integer>> constantsMap = makeConstantsMap();
		Map<String, Object> orgnizationMap = parseFiles(fileMapping, constantsMap);
		
		writeOrgnizationToDb(orgnizationMap);
		
		deletePath(dirPath);
		return true;
	}
	
	@Autowired
	private OrgnizationDao orgnizationDao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private GanbuDao ganbuDao;
	@Autowired
	private ConstantsDao constantsDao;
	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	
	private static final String Key_Org = "orgnization";
	private static final String Key_SuOrg = "suOrgnization";
	private static final String Key_OrgExistedFlg = "orgExistedFlg";
	private static final String Key_Lingdaobanzi = "lingdaobanzi";
	private static final String Key_R_Ldbz_Person = "r_Ldbz_Person";
	private static final String Key_Renyuanbanzi = "renyuanbianzhi";
	private static final String Key_Zaibianrenyuan = "zaibianrenyuan";
	private static final String Key_Baomiganbu = "baomiganbu";
	private static final String Key_R_Ganbu_Person = "r_Ganbu_Person";
	private static final String Key_Shemirenyuan = "shemirenyuan";
	private static final String Key_R_Shemi_Person = "r_Shemi_Person";
	private static final String Key_Resume = "resume";
//	private static final String Key_
	
	private static Logger _logger = Logger.getLogger(OrgnizationImportService.class);
}
