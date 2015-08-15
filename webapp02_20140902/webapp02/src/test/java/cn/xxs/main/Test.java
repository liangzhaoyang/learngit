package cn.xxs.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.Region;

import cn.xxs.entity.Orgnization;
import cn.xxs.service.OrgnizationService;

/**
 * @author 作者 :wan 创建时间：2014年9月4日 上午9:28:13 类说明
 */
public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		// TODO Auto-generated method stub
//		OrgnizationService orgnizationService=new OrgnizationService();
//		Orgnization org=orgnizationService.getOrgnizationById(1);
//		System.out.println(org.toString());
		// Workbook wb = Workbook.getWorkbook(new
		// File("src/main/resources/exceltemplate/保密行政部门及机构设置基本情况填报表.xls"));
		int i;
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
				"src/main/resources/exceltemplate/保密行政部门及机构设置基本情况填报表.xls"));
		if (fs != null) {
			System.out.println("open success!");
		}
		//
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet("基本信息");
		//给excel表导入数据信息
		HSSFRow row = sheet.getRow(4);
		HSSFCell cell = row.getCell((short) 2);
		/*
		if (cell == null) {
			cell = row.createCell((short) 2);
		}
		*/
		cell.setCellValue("单位名称wrqewrqrqwtrqtqtqeqwtqwetqetqewtqetqtq");
		cell = row.getCell((short) 8);
		cell.setCellValue("主管单位");
		row = sheet.getRow(5);
		cell = row.getCell((short) 2);
		cell.setCellValue("jigoufenlei1");
		cell = row.getCell((short) 5);
		cell.setCellValue("jigoufenlei2");
		cell = row.getCell((short) 8);
		cell.setCellValue("chenglishijian");
		cell = row.getCell((short) 12);
		cell.setCellValue("xingzhengjibie");
		row = sheet.getRow(6);
		cell = row.getCell((short) 2);
		cell.setCellValue("zhengfuxulie");
		cell = row.getCell((short) 5);
		cell.setCellValue("shenfen");
		cell = row.getCell((short) 8);
		cell.setCellValue("jingfeilaiyuan");
		cell = row.getCell((short) 12);
		cell.setCellValue("jigouleibie");
		//机构设置情况信息
		int insertRows=2;
		sheet.shiftRows(20, sheet.getLastRowNum(), insertRows,true,false);
		sheet.createRow(20);
		HSSFCellStyle seqStyle=sheet.getRow(10).getCell(0).getCellStyle();
		HSSFCellStyle constyle=sheet.getRow(10).getCell(2).getCellStyle();
		
		row = sheet.getRow(10);
		cell = row.getCell((short) 1);
		cell.setCellValue("bumenmingcheng");
		cell = row.getCell((short) 2);
		cell.setCellValue("bianzhirenshu");
		cell = row.getCell((short) 3);
		cell.setCellValue("shijirenshu");
		cell = row.getCell((short) 4);
		cell.setCellValue("xingzhengjibie");
		cell = row.getCell((short) 5);
		cell.setCellValue("bumenmingcheng");
		cell = row.getCell((short) 6);
		cell.setCellValue("bianzhirenshu");
		cell = row.getCell((short) 7);
		cell.setCellValue("shijirenshu");
		cell = row.getCell((short) 8);
		cell.setCellValue("xingzhengjibie");
		cell = row.getCell((short) 9);
		cell.setCellValue("bumenmingcheng");
		cell = row.getCell((short) 10);
		cell.setCellValue("bianzhirenshu");
		cell = row.getCell((short) 11);
		cell.setCellValue("shijirenshu");
		cell = row.getCell((short) 12);
		cell.setCellValue("xingzhengjibie");
		//新建行内容
		for(i=0;i<insertRows;i++)
		{
			row = sheet.getRow(20+i);
			cell = row.createCell((short) 0);
			cell.setCellStyle(seqStyle);
			cell.setCellValue(11+i);
			cell = row.createCell((short) 1);
			cell.setCellStyle(constyle);
			cell.setCellValue("bumenmingcheng");
			cell = row.createCell((short) 2);
			cell.setCellStyle(constyle);
			cell.setCellValue("bianzhirenshu");
			cell = row.createCell((short) 3);
			cell.setCellStyle(constyle);
			cell.setCellValue("shiyourenshu");
			cell = row.createCell((short) 4);
			cell.setCellStyle(constyle);
			cell.setCellValue("xingzhengjibie");
			cell = row.createCell((short) 5);
			cell.setCellStyle(constyle);
			cell.setCellValue("bumenmingcheng");
			cell = row.createCell((short) 6);
			cell.setCellStyle(constyle);
			cell.setCellValue("bianzhirenshu");
			cell = row.createCell((short) 7);
			cell.setCellStyle(constyle);
			cell.setCellValue("shiyourenshu");
			cell = row.createCell((short) 8);
			cell.setCellStyle(constyle);
			cell.setCellValue("xingzhengjibie");
			cell = row.createCell((short) 9);
			cell.setCellStyle(constyle);
			cell.setCellValue("bumenmingcheng");
			cell = row.createCell((short) 10);
			cell.setCellStyle(constyle);
			cell.setCellValue("bianzhirenshu");
			cell = row.createCell((short) 11);
			cell.setCellStyle(constyle);
			cell.setCellValue("shiyourenshu");
			cell = row.createCell((short) 12);
			cell.setCellStyle(constyle);
			cell.setCellValue("xingzhengjibie");
		}
	
		//基础设施
		int jcssInsertRows=3;
		sheet.shiftRows(30+insertRows, sheet.getLastRowNum(), jcssInsertRows,true,false);
		
		row = sheet.getRow(22+insertRows);
		cell = row.getCell((short) 1);
		cell.setCellValue("danweimingcheng");
		cell = row.getCell((short) 3);
		cell.setCellValue("jichusheshileibie");
		cell = row.getCell((short) 5);
		cell.setCellValue("mianji");
		cell = row.getCell((short) 6);
		cell.setCellValue("touruzijiin");
		cell = row.getCell((short) 9);
		cell.setCellValue("shigongdanwei");
		//基础设施新建行内容
		for(i=0;i<jcssInsertRows;i++)
		{
			row = sheet.getRow(30+insertRows+i);
			cell = row.createCell((short) 0);
			cell.setCellStyle(seqStyle);
			cell.setCellValue(9+i);
			cell = row.createCell((short) 1);
			Region region = new Region((short)30+insertRows+i,(short) 1,(short)30+insertRows+i,(short) 2);
			sheet.addMergedRegion(region);
			constyle=sheet.getRow(22+insertRows).getCell(1).getCellStyle();
			setRegionStyle(sheet,region,constyle);
			//constyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cell.setCellType(sheet.getRow(22+insertRows).getCell(1).getCellType());
			cell.setCellStyle(constyle);
			cell.setCellValue("danweimingcheng");
			
			cell = row.createCell((short) 3);
			region = new Region((short)30+insertRows+i,(short) 3,(short)30+insertRows+i,(short) 4);
			sheet.addMergedRegion(region);
			constyle=sheet.getRow(22+insertRows).getCell(3).getCellStyle();
			setRegionStyle(sheet,region,constyle);
			cell.setCellStyle(constyle);
			cell.setCellValue("jichusheshileibie");
			cell = row.createCell((short) 5);
			constyle=sheet.getRow(22+insertRows).getCell(5).getCellStyle();
			cell.setCellStyle(constyle);
			cell.setCellValue("mianji");
			cell = row.createCell((short) 6);
			constyle=sheet.getRow(22+insertRows).getCell(6).getCellStyle();
			region = new Region((short)30+insertRows+i,(short) 6,(short)30+insertRows+i,(short) 8);
			sheet.addMergedRegion(region);
			setRegionStyle(sheet,region,constyle);
			cell.setCellStyle(constyle);
			cell.setCellValue("touruzijiin");
			cell = row.createCell((short) 9);
			constyle=sheet.getRow(22+insertRows).getCell(9).getCellStyle();
			constyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			region = new Region((short)30+insertRows+i,(short) 9,(short)30+insertRows+i,(short) 12);
			sheet.addMergedRegion(region);
			setRegionStyle(sheet,region,constyle);
			cell.setCellStyle(constyle);
			cell.setCellValue("shigongdanwei");
		}
		//保密办附加信息
		row = sheet.getRow(31+insertRows+jcssInsertRows);
		cell = row.getCell((short) 2);
		cell.setCellValue("bianzhishu");
		cell = row.getCell((short) 5);
		cell.setCellValue("renshu");
		cell = row.getCell((short) 9);
		cell.setCellValue("zhuanzhiganbushu");
		//保密学院附加信息
		row = sheet.getRow(33+insertRows+jcssInsertRows);
		cell = row.getCell((short) 2);
		cell.setCellValue("xueshengshu");
		row = sheet.getRow(34+insertRows+jcssInsertRows);
		cell = row.getCell((short) 2);
		cell.setCellValue("jiuyeqingkuang");
		row = sheet.getRow(35+insertRows+jcssInsertRows);
		cell = row.getCell((short) 2);
		cell.setCellValue("shenheren");
		cell = row.getCell((short) 6);
		cell.setCellValue("tianbiaoren");
		cell = row.getCell((short) 9);
		cell.setCellValue("tianbiaoriqi");
		//ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		 // 输出文件
		 String OnputimagePath ="d:\\test.xls";
		 FileOutputStream fileOut = new FileOutputStream(OnputimagePath);
		 wb.write(fileOut);
		 fileOut.close(); 
	}
	
	@SuppressWarnings("deprecation")
	public static void setRegionStyle(HSSFSheet sheet, Region region, HSSFCellStyle cs) {
		  for (int i = region.getRowFrom(); i <= region.getRowTo(); i++) {
		   HSSFRow row = HSSFCellUtil.getRow(i, sheet);
		   for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
		    HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
		    cell.setCellStyle(cs);
		   }
		  }
		 }
}
