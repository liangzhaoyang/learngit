package cn.xxs.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.xxs.service.OrgnizationImportService;
import cn.xxs.utility.BaseController;
import cn.xxs.utility.Constants;

@Controller
public class OrgnizatioImportController extends BaseController {
	@RequestMapping(value="jgxxdr")
	public String toUpload() {
		return "orgnizationmanage/orgnizationImport";
	}
	
	private void outputMsg(PrintWriter out, String key, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, msg);
		try {
			out.print(new ObjectMapper().writeValueAsString(map));
		} catch (IOException e) {
		}
	}
	
	@RequestMapping(value="doJgdr")
	public void upload(	HttpServletRequest request, 
									HttpServletResponse response, 
									@RequestParam("fileName") CommonsMultipartFile file, 
									HttpSession session) {
		if(_logger.isDebugEnabled()) {
			_logger.debug("filename:" + file.getOriginalFilename());
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			return;
		}

		if(file.isEmpty()) {
			outputMsg(out, "errMsg", "请确认文件是否正确。");
			return;
		}
		
		String realPath = appConfig.uploadPath;
		String fullPath = realPath + "/" + file.getOriginalFilename();
		
		byte[] by = new byte[512];
		
		FileOutputStream os = null;
		InputStream in = null;
		int readCnt = 0;

		try {
			os = new FileOutputStream(fullPath);
			in = file.getInputStream();
			while((readCnt = in.read(by)) !=-1 ){
				os.write(by, 0, readCnt);
			}
			os.flush();
		} catch(IOException e) {
			if(_logger.isDebugEnabled()) {
				_logger.debug("errMsg:" + e.getMessage());
			}
			outputMsg(out, "errMsg", "文件保存失败，请检查文件后重试。");
			return;
		} finally {
			if(out != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		
		try {
			orgnizationImportService.importOrgnizationByZipFile(fullPath, realPath);
		} catch(Exception e) {
			outputMsg(out, "errMsg", e.getMessage()); //"文件保存失败，请检查文件后重试。");
			return;
		}
		
		outputMsg(out, "errMsg", null);;
		return;
	}

	@Autowired
	private Constants appConfig;
	
	@Autowired
	private OrgnizationImportService orgnizationImportService;

	private static Logger _logger = Logger.getLogger(OrgnizatioImportController.class);
}
