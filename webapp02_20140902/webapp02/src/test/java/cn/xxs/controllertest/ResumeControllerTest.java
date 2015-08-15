package cn.xxs.controllertest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;

@SuppressWarnings("deprecation")
public class ResumeControllerTest extends JUnitActionBase {
	// 简历加载测试
	/*
	 * @Test public void ResumeLoad() throws Exception { MockHttpServletRequest
	 * request = new MockHttpServletRequest();
	 * request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	 * MockHttpServletResponse response = new MockHttpServletResponse();
	 * request.setRequestURI("/ResumeLoad.do"); //参数设置
	 * request.addParameter("orgid", "1");
	 * //request.addParameter("zhuanyejishuzhiwu", "leo1"); //
	 * request.addParameter("num", "10");
	 * 
	 * request.setMethod("POST");
	 * 
	 * this.excuteAction(request, response); // 执行结果 String result =
	 * response.getContentAsString(); Assert.assertNotNull(result); } //简历条件查询
	 * 
	 * @Test public void ResumeQuery() throws Exception { MockHttpServletRequest
	 * request = new MockHttpServletRequest();
	 * request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	 * MockHttpServletResponse response = new MockHttpServletResponse();
	 * request.setRequestURI("/ResumeQuery.do"); //参数设置
	 * request.addParameter("orgid", "0"); request.addParameter("name", "o"); //
	 * request.addParameter("xianrenzhuiwu", "leo"); //
	 * request.addParameter("zhuanyejishuzhiwu", "leo1");
	 * 
	 * request.setMethod("POST");
	 * 
	 * this.excuteAction(request, response); // 执行结果 String result =
	 * response.getContentAsString(); Assert.assertNotNull(result); }
	 */
	// 删除简历
	/*
	 * @Test public void resumeDelete() throws Exception {
	 * MockHttpServletRequest request = new MockHttpServletRequest();
	 * request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	 * MockHttpServletResponse response = new MockHttpServletResponse();
	 * request.setRequestURI("/resumeDelete.do"); //参数设置
	 * request.addParameter("id", "1,2,3,4,5"); //request.addParameter("name",
	 * "Bob"); //request.addParameter("xianrenzhuiwu", "leo");
	 * //request.addParameter("zhuanyejishuzhiwu", "leo1");
	 * 
	 * request.setMethod("POST");
	 * 
	 * this.excuteAction(request, response); // 执行结果 String result =
	 * response.getContentAsString(); Assert.assertNotNull(result); }
	 */
	// 简历插入测试
	@Test
	public void ResumeInsert() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/ResumeInsert.do");
		// 参数设置
//		Map<String, Object> map = new HashMap<String, Object>();
		request.addParameter("zhuanye", "leo");
		request.addParameter("name", "leo1");
		request.addParameter("orgid", "1");
		request.addParameter("zhaopian", "leo2");
		request.addParameter("content1", "leo3");
		request.addParameter("content2", "leo4");
		request.addParameter("chengwei", "baba");
		request.addParameter("xingbie", "0");
		request.addParameter("chushengnianyue", "1989-8-23");
		request.addParameter("zhengzhimianmao", "1");
		request.addParameter("leibie", "1");
		request.addParameter("content1", "leo3");
		request.addParameter("content2", "leo4");
		request.addParameter("chengwei", "baba");
		request.addParameter("biyeyuanxiao", "湖南大学");
		request.addParameter("xianrenzhuiwu", "xxx");
		request.addParameter("zhuanchang", "aaaa");
		request.addParameter("zhuanyejishuzhiwu", "bbbbbb");
		request.addParameter("canjiagongzuoshijian", "2016-7-1");
		request.addParameter("xingming", "ccccccc");
		request.addParameter("chushengnianfen", "1968-1-1");
		request.addParameter("zhengzhimianmao", "ddddddd");
		request.addParameter("gongzuodanweijizhiwu", "eeeeeee");
		request.setMethod("POST");

		this.excuteAction(request, response);
		// 执行结果
		String result = response.getContentAsString();
		Assert.assertNotNull(result);
	}

	// 简历修改测试
	/*
	 * @Test public void ResumeUpdate() throws Exception {
	 * MockHttpServletRequest request = new MockHttpServletRequest();
	 * request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	 * MockHttpServletResponse response = new MockHttpServletResponse();
	 * request.setRequestURI("/ResumeUpdate.do"); //参数设置 Map map=new
	 * HashMap<String,Object>(); request.addParameter("personid", "17");
	 * request.addParameter("zhuanye", "Long"); request.addParameter("name",
	 * "Long"); request.addParameter("orgid", "1");
	 * request.addParameter("zhaopian", "Long");
	 * request.addParameter("content1", "Long");
	 * request.addParameter("content2", "Long");
	 * request.addParameter("chengwei", "baba,mama,jiejie");
	 * request.setMethod("POST");
	 * 
	 * this.excuteAction(request, response); // 执行结果 String result =
	 * response.getContentAsString(); Assert.assertNotNull(result);
	 * 
	 * } //简历详情测试 /*@Test public void ResumeInfo() throws Exception {
	 * MockHttpServletRequest request = new MockHttpServletRequest();
	 * request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true);
	 * MockHttpServletResponse response = new MockHttpServletResponse();
	 * request.setRequestURI("/ResumeInfo.do"); //参数设置 Map map=new
	 * HashMap<String,Object>(); request.addParameter("id", "17");
	 * 
	 * request.setMethod("POST");
	 * 
	 * this.excuteAction(request, response); // 执行结果 String result =
	 * response.getContentAsString(); Assert.assertNotNull(result);
	 * 
	 * }
	 */
}
