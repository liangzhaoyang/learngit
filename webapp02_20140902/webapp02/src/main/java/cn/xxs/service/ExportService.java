package cn.xxs.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.ChengguoDao;
import cn.xxs.dao.ConstantsDao;
import cn.xxs.dao.GuanliJigouDao;
import cn.xxs.dao.JiangchengDao;
import cn.xxs.dao.JiatingChengyuanDao;
import cn.xxs.dao.JichuSheshiDao;
import cn.xxs.dao.OrgnizationDao;
import cn.xxs.dao.PersonDao;
import cn.xxs.dao.RenyuanBianzhiMingxiDao;
import cn.xxs.dao.ResumeDao;
import cn.xxs.dao.ShiyeDanweiDao;
import cn.xxs.dao.XingzhengBumenDao;
import cn.xxs.dao.XueliMingxiDao;
import cn.xxs.dao.ZaibianRenyuanMingxiDao;
import cn.xxs.entity.Chengguo;
import cn.xxs.entity.GuanliJigou;
import cn.xxs.entity.Jiangcheng;
import cn.xxs.entity.JiatingChengyuan;
import cn.xxs.entity.JichuSheshi;
import cn.xxs.entity.Orgnization;
import cn.xxs.entity.Person;
import cn.xxs.entity.RenyuanBianzhiMingxi;
import cn.xxs.entity.Resume;
import cn.xxs.entity.ResumePerson;
import cn.xxs.entity.SelectItem;
import cn.xxs.entity.ShiyeDanwei;
import cn.xxs.entity.XingzhengBumen;
import cn.xxs.entity.XueliMingxi;
import cn.xxs.entity.ZaibianRenyuanMingxi;

/**
 * @author 作者 :wan 创建时间：2014年9月5日 上午9:16:11 类说明
 */
@Service
public class ExportService {
	@Autowired
	private OrgnizationDao orgnizationDao;
	@Autowired
	private XingzhengBumenDao xingzhengBumenDao;
	@Autowired
	private JichuSheshiDao jichuSheshiDao;
	@Autowired
	private GuanliJigouDao guanliJigouDao;
	@Autowired
	private ShiyeDanweiDao shiyeDanweiDao;
	@Autowired
	private ConstantsDao constantsDao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private ZaibianRenyuanMingxiDao zbryDao;
	@Autowired
	private RenyuanBianzhiMingxiDao rybzDao;
	@Autowired
	private ChengguoDao chengguoDao;
	@Autowired
	private JiangchengDao jiangchengDao;
	@Autowired
	private XueliMingxiDao xueliMingxiDao;
	@Autowired
	private JiatingChengyuanDao jiatingChengyuanDao;
	@Autowired
	private ResumeDao resumeDao;
	
	private  Calendar cal = Calendar.getInstance();
	private static Logger logger = Logger.getLogger(ExportService.class);
	
	//保密行政管理部门及机构设置基本情况
	@SuppressWarnings("deprecation")
	public void exportOrgInfo(HttpServletRequest request,
			HttpServletResponse response, int orgid)
			throws FileNotFoundException, IOException {
		// 获取模板
		String excelPath = getClass().getResource("/exceltemplate").getFile()
				+ "/保密行政部门及机构设置基本情况填报表.xls";
		excelPath = java.net.URLDecoder.decode(excelPath);
		logger.debug("---" + java.net.URLDecoder.decode(excelPath));

		// 获取数据库数据
		Orgnization org = orgnizationDao.getOrgnizationById(orgid);
		List<XingzhengBumen> xzbms = xingzhengBumenDao
				.queryXingzhengBumenByOrg(orgid);
		List<GuanliJigou> gljgs = guanliJigouDao.queryGuanliJigouByOrg(orgid);
		List<ShiyeDanwei> sydws = shiyeDanweiDao.queryShiyeDanweiByOrg(orgid);
		List<JichuSheshi> jcsss = jichuSheshiDao.queryJichuSheshiByOrg(orgid);

		List<SelectItem> jglbs = constantsDao.getSelectionListByType("机构类别");
		List<SelectItem> xzjbs = constantsDao.getSelectionListByType("行政级别_1");
		List<SelectItem> zfxls = constantsDao.getSelectionListByType("政府序列");
		List<SelectItem> jifoufenlei_1s = constantsDao
				.getSelectionListByType("机构分类1");
		List<SelectItem> jifoufenlei_2s = constantsDao
				.getSelectionListByType("机构分类2");
		List<SelectItem> shengfens = constantsDao.getSelectionListByType("省份");
		List<SelectItem> leibies = constantsDao
				.getSelectionListByType("基础设施类别");
		// 设置表格信息
		int i;
		logger.debug("##############path:" + request.getContextPath());
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("基本信息");
		// 给excel表导入数据信息
		 HSSFRow row;
		 HSSFCell cell;
		 
		 row = sheet.getRow(3);
		 cell = row.getCell((short) 7);
		 cell.setCellValue(""+cal.get(Calendar.YEAR)+"年");
		row = sheet.getRow(4);
		cell = row.getCell((short) 2);
		cell.setCellValue(org.getOrgname());
		cell = row.getCell((short) 8);
		if (org.getSuorgid() != null)
			cell.setCellValue(orgnizationDao.getOrgnizationById(
					org.getSuorgid()).getOrgname());
		else
			cell.setCellValue("");
		row = sheet.getRow(5);
		cell = row.getCell((short) 2);
		if (org.getJigoufenlei1() > 0)
			cell.setCellValue(jifoufenlei_1s.get(org.getJigoufenlei1() - 1)
					.getName());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 5);
		if (org.getJigoufenlei2() > 0)
			cell.setCellValue(jifoufenlei_2s.get(org.getJigoufenlei2() - 1)
					.getName());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 8);
		if (org.getChenglishijianString() != null)
			cell.setCellValue(org.getChenglishijianString());
		cell = row.getCell((short) 12);
		if (org.getXingzhengjibie() > 0)
			cell.setCellValue(xzjbs.get(org.getXingzhengjibie() - 1).getName());
		else
			cell.setCellValue("");
		row = sheet.getRow(6);
		cell = row.getCell((short) 2);
		if (org.getZhengfuxulie() > 0)
			cell.setCellValue(zfxls.get(org.getZhengfuxulie() - 1).getName());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 5);
		if (org.getShengfen() > 0)
			cell.setCellValue(shengfens.get(org.getShengfen() - 1).getName());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 8);
		cell.setCellValue(org.getJingfeilaiyuan());
		cell = row.getCell((short) 12);
		if (org.getJigouleibie() > 0)
			cell.setCellValue(jglbs.get(org.getJigouleibie() - 1).getName());
		else
			cell.setCellValue("");

		// 机构设置情况信息
		int insertRows = 10;
		logger.debug("xingzhengbumen:" + xzbms.size() + ",guanlijigou:"
				+ gljgs.size() + ",shiyedanwei:" + sydws.size());

		if (xzbms.size() > 10 || (gljgs.size() > 10) || (sydws.size() > 10)) {
			insertRows = xzbms.size() > insertRows ? xzbms.size() : insertRows;
			insertRows = gljgs.size() > insertRows ? gljgs.size() : insertRows;
			insertRows = sydws.size() > insertRows ? sydws.size() : insertRows;
		}
		//
		insertRows = insertRows - 10;
		sheet.shiftRows(20, sheet.getLastRowNum(), insertRows, true, false);
		sheet.createRow(20);

		HSSFCellStyle seqStyle = sheet.getRow(10).getCell(0).getCellStyle();
		HSSFCellStyle constyle=sheet.getRow(10).getCell(1).getCellStyle();
		for (i = 0; i < insertRows + 10; i++) {
			row = sheet.getRow(10 + i);
			cell = row.createCell((short) 0);
			cell.setCellStyle(seqStyle);
			cell.setCellValue(1 + i);
			// 行政部门信息
			if (xzbms.size() > i) {
				cell = row.createCell((short) 1);
				cell.setCellStyle(constyle);
				cell.setCellValue(xzbms.get(i).getName());
				cell = row.createCell((short) 2);
				cell.setCellStyle(constyle);
				if (xzbms.get(i).getBianzhirenshu() > 0)
					cell.setCellValue(xzbms.get(i).getBianzhirenshu());
				else
					cell.setCellValue("");

				cell = row.createCell((short) 3);
				cell.setCellStyle(constyle);
				if (xzbms.get(i).getShijirenshu() > 0)
					cell.setCellValue(xzbms.get(i).getShijirenshu());
				else
					cell.setCellValue("");

				cell = row.createCell((short) 4);
				cell.setCellStyle(constyle);
				if (xzbms.get(i).getXingzhengjibie() > 0)
					cell.setCellValue(xzjbs.get(
							xzbms.get(i).getXingzhengjibie() - 1).getName());
				else
					cell.setCellValue("");
			} else {
				cell = row.createCell((short) 1);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 2);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 3);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 4);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
			}
			// 管理机构信息
			if (gljgs.size() > i) {

				cell = row.createCell((short) 5);
				cell.setCellStyle(constyle);
				cell.setCellValue(gljgs.get(i).getName());

				cell = row.createCell((short) 6);

				cell.setCellStyle(constyle);
				if (gljgs.get(i).getBianzhirenshu() > 0)
					cell.setCellValue(gljgs.get(i).getBianzhirenshu());
				else
					cell.setCellValue("");
				if (i < 10)
					cell = row.getCell((short) 7);
				else
					cell = row.createCell((short) 7);

				cell.setCellStyle(constyle);
				if (gljgs.get(i).getShijirenshu() > 0)
					cell.setCellValue(gljgs.get(i).getShijirenshu());
				else
					cell.setCellValue("");
				cell = row.createCell((short) 8);
				cell.setCellStyle(constyle);
				if (gljgs.get(i).getXingzhengjibie() > 0)
					cell.setCellValue(xzjbs.get(
							gljgs.get(i).getXingzhengjibie() - 1).getName());
				else
					cell.setCellValue("");
			} else {
				cell = row.createCell((short) 5);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 6);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 7);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 8);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
			}
			// 事业单位信息
			if (sydws.size() > i) {
				cell = row.createCell((short) 9);
				cell.setCellStyle(constyle);
				cell.setCellValue(sydws.get(i).getName());
				cell = row.createCell((short) 10);
				cell.setCellStyle(constyle);
				if (sydws.get(i).getBianzhirenshu() > 0)
					cell.setCellValue(sydws.get(i).getBianzhirenshu());
				else
					cell.setCellValue("");
				cell = row.createCell((short) 11);
				cell.setCellStyle(constyle);
				if (sydws.get(i).getShijirenshu() > 0)
					cell.setCellValue(sydws.get(i).getShijirenshu());
				else
					cell.setCellValue("");
				cell = row.createCell((short) 12);
				cell.setCellStyle(constyle);
				if (sydws.get(i).getXingzhengjibie() > 0)
					cell.setCellValue(xzjbs.get(
							sydws.get(i).getXingzhengjibie() - 1).getName());
				else
					cell.setCellValue("");
			} else {
				cell = row.createCell((short) 9);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 10);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 11);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
				cell = row.createCell((short) 12);
				cell.setCellStyle(constyle);
				cell.setCellValue("");
			}
		}

		// 基础设施
		int jcssInsertRows = 8;
		jcssInsertRows = jcsss.size() > jcssInsertRows ? jcsss.size()
				: jcssInsertRows;
		jcssInsertRows = jcssInsertRows - 8;
		sheet.shiftRows(30 + insertRows, sheet.getLastRowNum(), jcssInsertRows,
				true, false);
		constyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		//constyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		for (i = 0; i < jcsss.size(); i++) {
			row = sheet.getRow(22 + insertRows + i);
			cell = row.createCell((short) 0);
			cell.setCellStyle(seqStyle);
			cell.setCellValue(1 + i);

			cell = row.createCell((short) 1);
			Region region = new Region((short) 22 + insertRows + i, (short) 1,
					(short) 22 + insertRows + i, (short) 2);
			sheet.addMergedRegion(region);
			setRegionStyle(sheet, region, constyle);
			cell.setCellValue(jcsss.get(i).getName());
			cell = row.createCell((short) 3);
			region = new Region((short) 22 + insertRows + i, (short) 3,
					(short) 22 + insertRows + i, (short) 4);
			sheet.addMergedRegion(region);
			setRegionStyle(sheet, region, constyle);
			if (jcsss.get(i).getLeibie() > 0)
				cell.setCellValue(leibies.get(jcsss.get(i).getLeibie() - 1)
						.getName());
			else
				cell.setCellValue("");
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
			if (jcsss.get(i).getMianji() > 0)
				cell.setCellValue(jcsss.get(i).getMianji());
			else
				cell.setCellValue("");
			cell = row.createCell((short) 6);
			region = new Region((short) 22 + insertRows + i, (short) 6,
					(short) 22 + insertRows + i, (short) 8);
			sheet.addMergedRegion(region);
			setRegionStyle(sheet, region, constyle);
			if (jcsss.get(i).getTouruzijin() > 0)
				cell.setCellValue(jcsss.get(i).getTouruzijin());
			else
				cell.setCellValue("");
			cell = row.createCell((short) 9);
			constyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			region = new Region((short) 22 + insertRows + i, (short) 9,
					(short) 22 + insertRows + i, (short) 12);
			sheet.addMergedRegion(region);
			setRegionStyle(sheet, region, constyle);
			if(!jcsss.get(i).getJianshedanwei().trim().equals("#"))
			cell.setCellValue(jcsss.get(i).getJianshedanwei());
		}
		
		// 保密办附加信息
		row = sheet.getRow(31 + insertRows + jcssInsertRows);
		cell = row.getCell((short) 2);
		if (org.getBianzhishu() > 0)
			cell.setCellValue(org.getBianzhishu());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 5);
		if (org.getRenshu() > 0)
			cell.setCellValue(org.getRenshu());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 9);
		if (org.getZhuanzhiganbushu() > 0)
			cell.setCellValue(org.getZhuanzhiganbushu());
		else
			cell.setCellValue("");
		// 保密学院附加信息
		row = sheet.getRow(33 + insertRows + jcssInsertRows);
		cell = row.getCell((short) 2);
		if (org.getXueshengshu() > 0)
			cell.setCellValue(org.getXueshengshu());
		else
			cell.setCellValue("");
		row = sheet.getRow(34 + insertRows + jcssInsertRows);
		cell = row.getCell((short) 2);
		if (org.getJiuyeqingkuang() != null)
			cell.setCellValue(org.getJiuyeqingkuang());
		else
			cell.setCellValue("");
		row = sheet.getRow(35 + insertRows + jcssInsertRows);
		cell = row.getCell((short) 2);
		if (org.getShenheren() != null)
			cell.setCellValue(org.getShenheren());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 6);
		if (org.getTianbiaoren() != null)
			cell.setCellValue(org.getTianbiaoren());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 9);
		if (org.getTianbiaoriqiString() != null)
			cell.setCellValue(org.getTianbiaoriqiString());
		else
			cell.setCellValue("");

		// 信息导出
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		//
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("保密行政管理部门及机构设置基本情况填报表" + ".xls").getBytes(),
						"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	//领导班子成员及工作人员基本情况
	@SuppressWarnings("deprecation")
	public void exportLdbzInfo(HttpServletRequest request,
			HttpServletResponse response, int orgid) throws FileNotFoundException, IOException
	{
		// 获取模板
		String excelPath = getClass().getResource("/exceltemplate").getFile()
				+ "/保密委员会办公室、保密局领导班子成员及工作人员基本情况填报表.xls";
		excelPath = java.net.URLDecoder.decode(excelPath);
		logger.debug("---" + java.net.URLDecoder.decode(excelPath));
		
		//获取数据
		List<Person> persons=personDao.selectAllLeader(orgid);
		Orgnization org = orgnizationDao.getOrgnizationById(orgid);
		logger.debug("orgid:"+orgid+"########33"+org.toString());
		
		List<SelectItem> sfzzs = constantsDao.getSelectionListByType("是否专职");
		List<SelectItem> jszcs = constantsDao.getSelectionListByType("技术职称");
		List<SelectItem> xzjbs = constantsDao.getSelectionListByType("行政级别");
		List<SelectItem> zws = constantsDao.getSelectionListByType("职务");
		List<SelectItem> xbs = constantsDao.getSelectionListByType("性别");
		List<SelectItem> xls = constantsDao.getSelectionListByType("学历");
		List<SelectItem> zzmms = constantsDao.getSelectionListByType("政治面貌");
		
		// 设置表格信息
		int i;
		logger.debug("##############path:" + request.getContextPath());
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("sheet1");
		//设置格式
		HSSFCellStyle seqStyle = sheet.getRow(5).getCell(0).getCellStyle();
		HSSFCellStyle constyle=sheet.getRow(5).getCell(1).getCellStyle();
		constyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		constyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 给excel表导入数据信息
		int insertRows = 48;
		insertRows = persons.size() > insertRows ? persons.size() : insertRows;
		insertRows = insertRows - 48;
		sheet.shiftRows(52, sheet.getLastRowNum(), insertRows,
				true, false);
		
		HSSFRow row;
		HSSFCell cell;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		 row = sheet.getRow(3);
		 cell = row.getCell((short) 10);
		 cell.setCellValue(""+cal.get(Calendar.YEAR)+"年");
		 //如果没有数据，则清空样表中数据
		if(persons.size()==0)
		{
			row = sheet.getRow(5);
			//职务
			cell = row.createCell((short) 1);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//姓名
			cell = row.createCell((short) 2);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//性别
			cell = row.createCell((short) 3);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//出生年月
			cell = row.createCell((short) 4);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//学历
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//政治面貌
			cell = row.createCell((short) 6);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//专业
			cell = row.createCell((short) 7);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//行政级别
			cell = row.createCell((short) 8);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//技术职称
			cell = row.createCell((short) 9);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//任命单位
			cell = row.createCell((short) 10);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//是否专职
			cell = row.createCell((short) 11);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//兼任其他职务
			cell = row.createCell((short) 12);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//何时从事保密工作
			cell = row.createCell((short) 13);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//部门名称
			cell = row.createCell((short) 14);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//单位名称
			cell = row.createCell((short) 15);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
		}
		
		for(i=0;i<persons.size();i++)
		{
			//序号
			row = sheet.getRow(5+ i);
			cell = row.createCell((short) 0);
			cell.setCellStyle(seqStyle);
			cell.setCellValue(1 + i);
			//职务
			cell = row.createCell((short) 1);
			cell.setCellStyle(constyle);
			if (persons.get(i).getZhiwu() > 0)
				cell.setCellValue(zws.get(persons.get(i).getZhiwu()-1).getName());
			else
				cell.setCellValue("");
			//姓名
			cell = row.createCell((short) 2);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getName());
			//性别
			cell = row.createCell((short) 3);
			cell.setCellStyle(constyle);
			if (persons.get(i).getXingbie() > 0)
				cell.setCellValue(xbs.get(persons.get(i).getXingbie()-1).getName());
			else
				cell.setCellValue("");
			//出生年月
			cell = row.createCell((short) 4);
			cell.setCellStyle(constyle);
			cell.setCellValue(formatter.format(persons.get(i).getChushengnianyue()));
			//学历
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
			if (persons.get(i).getXueli() > 0)
				cell.setCellValue(xls.get(persons.get(i).getXueli()-1).getName());
			else
				cell.setCellValue("");
			//政治面貌
			cell = row.createCell((short) 6);
			cell.setCellStyle(constyle);
			if (persons.get(i).getZhengzhimianmao() == 1)
				cell.setCellValue(zzmms.get(persons.get(i).getZhengzhimianmao()-1).getName());
			else
				cell.setCellValue("非中共党员");
			//专业
			cell = row.createCell((short) 7);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getZhuanye());
			//行政级别
			cell = row.createCell((short) 8);
			cell.setCellStyle(constyle);
			if(persons.get(i).getXingzhengjibie()==99)
				cell.setCellValue("其他");
			else if (persons.get(i).getXingzhengjibie() > -1)
				cell.setCellValue(xzjbs.get(persons.get(i).getXingbie()).getName());
			else
				cell.setCellValue("");
			//技术职称
			cell = row.createCell((short) 9);
			cell.setCellStyle(constyle);
			if (persons.get(i).getJishuzhicheng() > 0)
				cell.setCellValue(jszcs.get(persons.get(i).getJishuzhicheng()-1).getName());
			else
				cell.setCellValue("");
			//任命单位
			cell = row.createCell((short) 10);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getRenmingdanwei());
			//是否专职
			cell = row.createCell((short) 11);
			cell.setCellStyle(constyle);
			if (persons.get(i).getShifouzhuanzhi() > 0)
				cell.setCellValue(sfzzs.get(persons.get(i).getShifouzhuanzhi()-1).getName());
			else
				cell.setCellValue("");
			//兼任其他职务
			cell = row.createCell((short) 12);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getJianrenqitazhiwu());
			//何时从事保密工作
			cell = row.createCell((short) 13);
			cell.setCellStyle(constyle);
			cell.setCellValue(formatter.format(persons.get(i).getKaishiriqi()));
			//部门名称
			cell = row.createCell((short) 14);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getSuoshubumen());
			//单位名称
			cell = row.createCell((short) 15);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getDanweimingcheng());
		}
		
		row = sheet.getRow(53+insertRows);
		//审核人
		cell = row.createCell((short) 2);
		cell.setCellValue(org.getShenheren());
		//填表人
		cell = row.createCell((short) 10);
		cell.setCellValue(org.getTianbiaoren());
		//填表日期
		cell = row.createCell((short) 15);
		cell.setCellValue(org.getTianbiaoriqiString());
		
		// 信息导出
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		//
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("保密委员会办公室、保密局领导班子成员及工作人员基本情况填报表" + ".xls").getBytes(),
						"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	//行政管理部门人员编制情况
	@SuppressWarnings("deprecation")
	public void exportRybzInfo(HttpServletRequest request,
			HttpServletResponse response, int orgid) throws FileNotFoundException, IOException
	{
		// 获取模板
		String excelPath = getClass().getResource("/exceltemplate").getFile()
				+ "/保密行政管理部门人员编制情况填报表.xls";
		excelPath = java.net.URLDecoder.decode(excelPath);
		logger.debug("---" + java.net.URLDecoder.decode(excelPath));
		
		//获取数据
		List<RenyuanBianzhiMingxi> rybzs =rybzDao.getAllBianzhi(orgid);
		Orgnization org = orgnizationDao.getOrgnizationById(orgid);
		
		// 设置表格信息
		int i;
		logger.debug("##############path:" + request.getContextPath());
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("sheet1");
		// 给excel表导入数据信息
		HSSFRow row;
		HSSFCell cell;
		int count=101;
		
	 row = sheet.getRow(3);
	 cell = row.getCell((short) 4);
	 cell.setCellValue(""+cal.get(Calendar.YEAR)+"年");
		 
		for(i=0;i<15;i++)
		{
			row = sheet.getRow(7+i);
			cell = row.getCell((short) 7);
			cell.setCellValue(getRybzVal(rybzs,count++));
		}
		
		row = sheet.getRow(22);
		//审核人
		cell = row.createCell((short) 1);
		cell.setCellValue(org.getShenheren());
		//填表人
		cell = row.createCell((short) 4);
		cell.setCellValue(org.getTianbiaoren());
		//填表日期
		cell = row.createCell((short) 7);
		cell.setCellValue(org.getTianbiaoriqiString());
		
		// 信息导出
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		//
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("保密行政管理部门人员编制情况填报表" + ".xls").getBytes(),
						"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	//行政管理部门在编人员情况
	@SuppressWarnings("deprecation")
	public void exportZbryInfo(HttpServletRequest request,
			HttpServletResponse response, int orgid) throws FileNotFoundException, IOException
	{
		// 获取模板
		String excelPath = getClass().getResource("/exceltemplate").getFile()
				+ "/保密行政管理部门在编人员情况填报表.xls";
		excelPath = java.net.URLDecoder.decode(excelPath);
		logger.debug("---" + java.net.URLDecoder.decode(excelPath));
		
		//获取数据
		List<ZaibianRenyuanMingxi> zbrys =zbryDao.getAllZaibian(orgid);
		Orgnization org = orgnizationDao.getOrgnizationById(orgid);
		
		// 设置表格信息
		int i;
		logger.debug("##############path:" + request.getContextPath());
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("sheet1");
		// 给excel表导入数据信息
		HSSFRow row;
		HSSFCell cell;
		int count=101;
		
		 row = sheet.getRow(3);
		 cell = row.getCell((short) 4);
		 cell.setCellValue(""+cal.get(Calendar.YEAR)+"年");
		 
		row = sheet.getRow(6);
		cell = row.getCell((short) 7);
		cell.setCellValue(getZbryVal(zbrys,count++));
		
		for(i=0;i<15;i++)
		{
			if(i%5==0)
				continue;
			row = sheet.getRow(7+i);
			cell = row.getCell((short) 7);
			cell.setCellValue(getZbryVal(zbrys,count++));
		}
		row = sheet.getRow(22);
		cell = row.getCell((short) 7);
		cell.setCellValue(getZbryVal(zbrys,count++));
		
		for(i=0;i<4;i++)
		{
			row = sheet.getRow(24+i);
			cell = row.getCell((short) 7);
			cell.setCellValue(getZbryVal(zbrys,count++));
		}
		
		row = sheet.getRow(28);
		//审核人
		cell = row.createCell((short) 1);
		cell.setCellValue(org.getShenheren());
		//填表人
		cell = row.createCell((short) 4);
		cell.setCellValue(org.getTianbiaoren());
		//填表日期
		cell = row.createCell((short) 7);
		cell.setCellValue(org.getTianbiaoriqiString());
		
		// 信息导出
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		//
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("保密行政管理部门在编人员情况填报表" + ".xls").getBytes(),
						"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	//保密干部基本情况
	@SuppressWarnings("deprecation")
	public void exportBmgbInfo(HttpServletRequest request,
			HttpServletResponse response, int orgid) throws FileNotFoundException, IOException
	{
		// 获取模板
		String excelPath = getClass().getResource("/exceltemplate").getFile()
				+ "/保密工作机构保密干部基本情况填报表.xls";
		excelPath = java.net.URLDecoder.decode(excelPath);
		logger.debug("---" + java.net.URLDecoder.decode(excelPath));
		
		//获取数据
		List<Person> persons=personDao.selectAllGanbu(orgid);
		Orgnization org = orgnizationDao.getOrgnizationById(orgid);
		
		List<SelectItem> jglbs = constantsDao.getSelectionListByType("机构类别");
		List<SelectItem> sfzzs = constantsDao.getSelectionListByType("是否专职");
		List<SelectItem> jszcs = constantsDao.getSelectionListByType("技术职称");
		List<SelectItem> xzjb2s = constantsDao.getSelectionListByType("行政级别");
		List<SelectItem> xzjb1s = constantsDao.getSelectionListByType("行政级别_1");
		List<SelectItem> zws = constantsDao.getSelectionListByType("职务");
		List<SelectItem> xbs = constantsDao.getSelectionListByType("性别");
		List<SelectItem> xls = constantsDao.getSelectionListByType("学历");
		List<SelectItem> zzmms = constantsDao.getSelectionListByType("政治面貌");
		
		// 设置表格信息
		int i;
		logger.debug("##############path:" + request.getContextPath());
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("sheet1");
		//设置格式
		HSSFCellStyle seqStyle = sheet.getRow(7).getCell(0).getCellStyle();
		HSSFCellStyle constyle=sheet.getRow(7).getCell(1).getCellStyle();
		constyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		constyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 给excel表导入数据信息
		HSSFRow row;
		HSSFCell cell;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		 row = sheet.getRow(3);
		 cell = row.getCell((short) 10);
		 cell.setCellValue(""+cal.get(Calendar.YEAR)+"年");
		 
		row = sheet.getRow(5);
		cell = row.getCell((short) 2);
		if(org.getJigouleibie()>0)
		cell.setCellValue(jglbs.get(org.getJigouleibie()-1).getName());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 5);
		if(org.getXingzhengjibie()>0)
		cell.setCellValue(xzjb1s.get(org.getXingzhengjibie()-1).getName());
		else
			cell.setCellValue("");
		cell = row.getCell((short) 8);
		if(org.getBaomiganbubianzhirenshu()>0)
		cell.setCellValue(org.getBaomiganbubianzhirenshu());
		else
			cell.setCellValue("");
		
		int insertRows = 45;
		insertRows = persons.size() > insertRows ? persons.size() : insertRows;
		insertRows = insertRows - 45;
		sheet.shiftRows(51, sheet.getLastRowNum(), insertRows,
				true, false);		
		 //如果没有数据，则清空样表中数据
		if(persons.size()==0)
		{
			row = sheet.getRow(7);
			//职务
			cell = row.createCell((short) 1);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//姓名
			cell = row.createCell((short) 2);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//性别
			cell = row.createCell((short) 3);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//出生年月
			cell = row.createCell((short) 4);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//学历
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//政治面貌
			cell = row.createCell((short) 6);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//专业
			cell = row.createCell((short) 7);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//行政级别
			cell = row.createCell((short) 8);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//技术职称
			cell = row.createCell((short) 9);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//是否专职
			cell = row.createCell((short) 10);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//兼任其他职务
			cell = row.createCell((short) 11);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//何时从事保密工作
			cell = row.createCell((short) 12);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//联系电话
			cell = row.createCell((short) 13);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//手机
			cell = row.createCell((short) 14);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//部门名称
			cell = row.createCell((short) 15);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//单位名称
			cell = row.createCell((short) 16);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
		}
		
		for(i=0;i<persons.size();i++)
		{
			//序号
			row = sheet.getRow(7+ i);
			cell = row.createCell((short) 0);
			cell.setCellStyle(seqStyle);
			cell.setCellValue(1 + i);
			//职务
			cell = row.createCell((short) 1);
			cell.setCellStyle(constyle);
			if (persons.get(i).getZhiwu() > 0)
				cell.setCellValue(zws.get(persons.get(i).getZhiwu()-1).getName());
			else
				cell.setCellValue("");
			//姓名
			cell = row.createCell((short) 2);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getName());
			//性别
			cell = row.createCell((short) 3);
			cell.setCellStyle(constyle);
			if (persons.get(i).getXingbie() > 0)
				cell.setCellValue(xbs.get(persons.get(i).getXingbie()-1).getName());
			else
				cell.setCellValue("");
			//出生年月
			cell = row.createCell((short) 4);
			cell.setCellStyle(constyle);
			cell.setCellValue(formatter.format(persons.get(i).getChushengnianyue()));
			//学历
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
			if (persons.get(i).getXueli() > 0)
				cell.setCellValue(xls.get(persons.get(i).getXueli()-1).getName());
			else
				cell.setCellValue("");
			//政治面貌
			cell = row.createCell((short) 6);
			cell.setCellStyle(constyle);
			if (persons.get(i).getZhengzhimianmao() == 1)
				cell.setCellValue(zzmms.get(persons.get(i).getZhengzhimianmao()-1).getName());
			else
				cell.setCellValue("非中共党员");
			//专业
			cell = row.createCell((short) 7);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getZhuanye());
			//行政级别
			cell = row.createCell((short) 8);
			cell.setCellStyle(constyle);
			if(persons.get(i).getXingzhengjibie()==99)
				cell.setCellValue("其他");
			else if (persons.get(i).getXingzhengjibie() > -1)
				cell.setCellValue(xzjb2s.get(persons.get(i).getXingbie()).getName());
			else
				cell.setCellValue("");
			//技术职称
			cell = row.createCell((short) 9);
			cell.setCellStyle(constyle);
			if (persons.get(i).getJishuzhicheng() > 0)
				cell.setCellValue(jszcs.get(persons.get(i).getJishuzhicheng()-1).getName());
			else
				cell.setCellValue("");
			//是否专职
			cell = row.createCell((short) 10);
			cell.setCellStyle(constyle);
			if (persons.get(i).getShifouzhuanzhi() > 0)
				cell.setCellValue(sfzzs.get(persons.get(i).getShifouzhuanzhi()-1).getName());
			else
				cell.setCellValue("");
			//兼任其他职务
			cell = row.createCell((short) 11);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getJianrenqitazhiwu());
			//何时从事保密工作
			cell = row.createCell((short) 12);
			cell.setCellStyle(constyle);
			cell.setCellValue(formatter.format(persons.get(i).getKaishiriqi()));
			//联系电话
			cell = row.createCell((short) 13);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getTel());
			//手机
			cell = row.createCell((short) 14);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getMobilenum());
			//部门名称
			cell = row.createCell((short) 15);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getSuoshubumen());
			//单位名称
			cell = row.createCell((short) 16);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getDanweimingcheng());
		}
		
		row = sheet.getRow(52+insertRows);
		//审核人
		cell = row.createCell((short) 2);
		cell.setCellValue(org.getShenheren());
		//填表人
		cell = row.createCell((short) 10);
		cell.setCellValue(org.getTianbiaoren());
		//填表日期
		cell = row.createCell((short) 15);
		cell.setCellValue(org.getTianbiaoriqiString());
		
		// 信息导出
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		//
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("保密工作机构保密干部基本情况填报表" + ".xls").getBytes(),
						"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	//涉密人员基本信息
	@SuppressWarnings("deprecation")
	public void exportSmryInfo(HttpServletRequest request,
			HttpServletResponse response, int orgid) throws FileNotFoundException, IOException
	{
		// 获取模板
		String excelPath = getClass().getResource("/exceltemplate").getFile()
				+ "/涉密人员基本情况填报表.xls";
		excelPath = java.net.URLDecoder.decode(excelPath);
		logger.debug("---" + java.net.URLDecoder.decode(excelPath));
		
		//获取数据
		List<Person> persons=personDao.selectAllShemiRenyuan(orgid);
		Orgnization org = orgnizationDao.getOrgnizationById(orgid);
		
		List<SelectItem> rylxs = constantsDao.getSelectionListByType("人员类型");
		List<SelectItem> sfyhbmrys =constantsDao.getSelectionListByType("是否要害部门人员");
		List<SelectItem> jszcs = constantsDao.getSelectionListByType("技术职称");
		List<SelectItem> xzjbs = constantsDao.getSelectionListByType("行政级别");
		List<SelectItem> xbs = constantsDao.getSelectionListByType("性别");
		List<SelectItem> xls = constantsDao.getSelectionListByType("学历");
		List<SelectItem> zzmms = constantsDao.getSelectionListByType("政治面貌_1");
		List<SelectItem> mzs = constantsDao.getSelectionListByType("民族");
		List<SelectItem> smdjs = constantsDao.getSelectionListByType("涉密等级");

		
		// 设置表格信息
		int i;
		logger.debug("##############path:" + request.getContextPath());
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("sheet1");
		//设置格式
		HSSFCellStyle seqStyle = sheet.getRow(6).getCell(0).getCellStyle();
		HSSFCellStyle constyle=sheet.getRow(6).getCell(1).getCellStyle();
		constyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		constyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 给excel表导入数据信息
		int insertRows = 48;
		insertRows = persons.size() > insertRows ? persons.size() : insertRows;
		insertRows = insertRows - 48;
		sheet.shiftRows(53, sheet.getLastRowNum(), insertRows,
				true, false);
		
		HSSFRow row;
		HSSFCell cell;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		 row = sheet.getRow(3);
		 cell = row.getCell((short) 10);
		 cell.setCellValue(""+cal.get(Calendar.YEAR)+"年");
		 //如果没有数据，则清空样表中数据
		 if(persons.size()==0)
		 {
			 row = sheet.getRow(6);
			//姓名
			cell = row.createCell((short) 1);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//性别
			cell = row.createCell((short) 2);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//出生年月
			cell = row.createCell((short) 3);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//民族
			cell = row.createCell((short) 4);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//政治面貌
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//学历
			cell = row.createCell((short) 6);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//涉密等级
			cell = row.createCell((short) 7);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//行政级别
			cell = row.createCell((short) 8);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//技术职称
			cell = row.createCell((short) 9);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//是否属于要害部门
			cell = row.createCell((short) 10);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//人员类型
			cell = row.createCell((short) 11);
			cell.setCellStyle(constyle);
				cell.setCellValue("");
			//部门名称
			cell = row.createCell((short) 12);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
			//单位名称
			cell = row.createCell((short) 13);
			cell.setCellStyle(constyle);
			cell.setCellValue("");
		 }
		 
		for(i=0;i<persons.size();i++)
		{
			//序号
			row = sheet.getRow(6+ i);
			cell = row.createCell((short) 0);
			cell.setCellStyle(seqStyle);
			cell.setCellValue(1 + i);
			//姓名
			cell = row.createCell((short) 1);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getName());
			//性别
			cell = row.createCell((short) 2);
			cell.setCellStyle(constyle);
			if (persons.get(i).getXingbie() > 0)
				cell.setCellValue(xbs.get(persons.get(i).getXingbie()-1).getName());
			else
				cell.setCellValue("");
			//出生年月
			cell = row.createCell((short) 3);
			cell.setCellStyle(constyle);
			cell.setCellValue(formatter.format(persons.get(i).getChushengnianyue()));
			//民族
			cell = row.createCell((short) 4);
			cell.setCellStyle(constyle);
			if (persons.get(i).getMinzu() > 0)
				cell.setCellValue(mzs.get(persons.get(i).getMinzu()-1).getName());
			else
				cell.setCellValue("");
			//政治面貌
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
			if (persons.get(i).getZhengzhimianmao()>0)
				cell.setCellValue(zzmms.get(persons.get(i).getZhengzhimianmao()-1).getName());
			else
				cell.setCellValue("");
			//学历
			cell = row.createCell((short) 6);
			cell.setCellStyle(constyle);
			if (persons.get(i).getXueli() > 0)
				cell.setCellValue(xls.get(persons.get(i).getXueli()-1).getName());
			else
				cell.setCellValue("");
			//涉密等级
			cell = row.createCell((short) 7);
			cell.setCellStyle(constyle);
			if (persons.get(i).getShemidengji() > 0)
				cell.setCellValue(smdjs.get(persons.get(i).getShemidengji()-1).getName());
			else
				cell.setCellValue("");
			//行政级别
			cell = row.createCell((short) 8);
			cell.setCellStyle(constyle);
			if(persons.get(i).getXingzhengjibie()==99)
				cell.setCellValue("其他");
			else if (persons.get(i).getXingzhengjibie() > -1)
				cell.setCellValue(xzjbs.get(persons.get(i).getXingbie()).getName());
			else
				cell.setCellValue("");
			//技术职称
			cell = row.createCell((short) 9);
			cell.setCellStyle(constyle);
			if (persons.get(i).getJishuzhicheng() > 0)
				cell.setCellValue(jszcs.get(persons.get(i).getJishuzhicheng()-1).getName());
			else
				cell.setCellValue("");
			//是否属于要害部门
			cell = row.createCell((short) 10);
			cell.setCellStyle(constyle);
			if (persons.get(i).getYaohaibumengrenyuan() > 0)
				cell.setCellValue(sfyhbmrys.get(persons.get(i).getYaohaibumengrenyuan()-1).getName());
			else
				cell.setCellValue("");
			//人员类型
			cell = row.createCell((short) 11);
			cell.setCellStyle(constyle);
			if (persons.get(i).getRenyuanleixing() > 0)
				cell.setCellValue(rylxs.get(persons.get(i).getRenyuanleixing()-1).getName());
			else
				cell.setCellValue("");
			//部门名称
			cell = row.createCell((short) 12);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getSuoshubumen());
			//单位名称
			cell = row.createCell((short) 13);
			cell.setCellStyle(constyle);
			cell.setCellValue(persons.get(i).getDanweimingcheng());
		}
		
		row = sheet.getRow(54+insertRows);
		//审核人
		cell = row.createCell((short) 2);
		cell.setCellValue(org.getShenheren());
		//填表人
		cell = row.createCell((short) 10);
		cell.setCellValue(org.getTianbiaoren());
		//填表日期
		cell = row.createCell((short) 13);
		cell.setCellValue(org.getTianbiaoriqiString());
		
		// 信息导出
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		//
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("涉密人员基本情况填报表" + ".xls").getBytes(),
						"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	//简历列表
	@SuppressWarnings("deprecation")
	public void exportJlInfo(HttpServletRequest request,
			HttpServletResponse response, int personid) throws FileNotFoundException, IOException
	{
		// 获取模板
		String excelPath = getClass().getResource("/exceltemplate").getFile()
				+ "/简历.xls";
		excelPath = java.net.URLDecoder.decode(excelPath);
		logger.debug("---" + java.net.URLDecoder.decode(excelPath));
		
		//获取数据
		ResumePerson person=personDao.selectResumePersonByPersonid(personid);
		Jiangcheng jiangcheng=jiangchengDao.selectJiangchengByPersonid(personid);
		Chengguo chengguo=chengguoDao.selectChengguoByPersonid(personid);
		XueliMingxi xueliMingxi=xueliMingxiDao.selectXueliMingxiByPersonid(personid);
		Resume resume=resumeDao.selectResumeByPersonid(personid);
		List<JiatingChengyuan> jiatingChengyuan=jiatingChengyuanDao.selectJiatingChengyuanByPersonid(personid);
		
		List<SelectItem> xbs=constantsDao.getSelectionListByType("性别");
		List<SelectItem> xls=constantsDao.getSelectionListByType("学历");
		List<SelectItem> zzmms=constantsDao.getSelectionListByType("政治面貌_1");
		List<SelectItem> mzs=constantsDao.getSelectionListByType("民族");
		// 设置表格信息
		int i;
		logger.debug("##############path:" + request.getContextPath());
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(excelPath));
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("sheet1");
		// 给excel表导入数据信息
		HSSFRow row;
		HSSFCell cell;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		row = sheet.getRow(1);
		//姓名
		cell = row.getCell((short) 1);
		cell.setCellValue(person.getName());
		//性别
		cell = row.getCell((short) 6);
		if(person.getXingbie()>0)
			cell.setCellValue(xbs.get(person.getXingbie()-1).getName());
		else
			cell.setCellValue("");
		//民族
		cell = row.getCell((short) 10);
		if(person.getMinzu()>0)
			cell.setCellValue(mzs.get(person.getMinzu()-1).getName());
		else
			cell.setCellValue("");
		//出生年月
		row = sheet.getRow(2);
		cell = row.getCell((short) 1);
		if(person.gettChushengnianyue()!=null)
			cell.setCellValue(formatter.format(person.gettChushengnianyue()));
		else
			cell.setCellValue("");
		//政治面貌
		cell = row.getCell((short) 6);
		if(person.getZhengzhimianmao()>0)
			cell.setCellValue(zzmms.get(person.getZhengzhimianmao()-1).getName());
		else
			cell.setCellValue("");
		//参加工作时间
		cell = row.getCell((short) 10);
		if(resume.getCanjiagongzuoshijian()!=null)
			cell.setCellValue(formatter.format(resume.getCanjiagongzuoshijian()));
		else
			cell.setCellValue("");
		//照片
		
		//专业技术职务
		row = sheet.getRow(3);
		cell = row.getCell((short) 1);
		cell.setCellValue(resume.getZhuanyejishuzhiwu());
		//专长
		cell = row.getCell((short) 8);
		cell.setCellValue(resume.getZhuanchang());
		if(xueliMingxi.getLeibie()==1)
		{
			//全日制教育
			row = sheet.getRow(5);
			cell = row.getCell((short) 3);
			if(xueliMingxi.getXueli()>0)
			cell.setCellValue(xls.get(xueliMingxi.getXueli()-1).getName());
			else
				cell.setCellValue("");
			//毕业院校
			cell = row.getCell((short) 8);
			cell.setCellValue(xueliMingxi.getBiyeyuanxiao());
			//专业
			row = sheet.getRow(6);
			cell = row.getCell((short) 8);
			cell.setCellValue(xueliMingxi.getZhuanye());
		}
		if(xueliMingxi.getLeibie()==2)
		{
			//在职教育
			row = sheet.getRow(7);
			cell = row.getCell((short) 3);
			if(xueliMingxi.getXueli()>0)
			cell.setCellValue(xls.get(xueliMingxi.getXueli()-1).getName());
			else
				cell.setCellValue("");
			//毕业院校
			cell = row.getCell((short) 8);
			cell.setCellValue(xueliMingxi.getBiyeyuanxiao());
			//专业
			row = sheet.getRow(8);
			cell = row.getCell((short) 8);
			cell.setCellValue(xueliMingxi.getZhuanye());
		}
		//现任职务
		row = sheet.getRow(9);
		cell = row.getCell((short) 3);
		cell.setCellValue(resume.getXianrenzhuiwu());
		//简历
		row = sheet.getRow(10);
		cell = row.getCell((short) 1);
		cell.setCellValue(resume.getJianli());
		//奖惩
		row = sheet.getRow(11);
		cell = row.getCell((short) 1);
		cell.setCellValue(jiangcheng.getContent());
		//主要成果
		row = sheet.getRow(12);
		cell = row.getCell((short) 1);
		cell.setCellValue(chengguo.getContent());
		//家庭成员及社会关系
		for(i=0;i<jiatingChengyuan.size();i++)
		{
			if(i<4)
			{
				row = sheet.getRow(14+i);
				//称谓
				cell = row.getCell((short) 1);
				cell.setCellValue(jiatingChengyuan.get(i).getChengwei());
				//姓名
				cell = row.getCell((short) 2);
				cell.setCellValue(jiatingChengyuan.get(i).getXingming());
				//年龄
				cell = row.getCell((short) 5);
				if(jiatingChengyuan.get(i).getChushengnianfen()!=null)
					cell.setCellValue(formatter.format(jiatingChengyuan.get(i).getChushengnianfen()));
				else
					cell.setCellValue("");
				//政治面貌
				cell = row.getCell((short) 7);
				cell.setCellValue(jiatingChengyuan.get(i).getZhengzhimianmao());
				//工作单位及职务
				cell = row.getCell((short) 9);
				cell.setCellValue(jiatingChengyuan.get(i).getGongzuodanweijizhiwu());
			}
		}
		//备注
		row = sheet.getRow(18);
		cell = row.getCell((short) 1);
		cell.setCellValue(resume.getBeizhu());
		
		// 信息导出
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		//
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(("简历" + ".xls").getBytes(),
						"iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {

			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);

			byte[] buff = new byte[2048];
			int bytesRead;

			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setRegionStyle(HSSFSheet sheet, Region region, HSSFCellStyle cs) {
		for (int i = region.getRowFrom(); i <= region.getRowTo(); i++) {
			HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
				HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
				cell.setCellStyle(cs);
			}
		}
	}
	
	public String getZbryVal(List<ZaibianRenyuanMingxi> zbrys,int code)
	{
		for(ZaibianRenyuanMingxi temp:zbrys)
		{
			if(temp.getCode()==code)
				return ""+temp.getNum();
		}
		return "";
	}
	
	public String getRybzVal(List<RenyuanBianzhiMingxi> zbrys,int code)
	{
		for(RenyuanBianzhiMingxi temp:zbrys)
		{
			if(temp.getCode()==code)
				return ""+temp.getNum();
		}
		return "";
	}
}
