package cn.xxs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xxs.dao.ChengguoDao;
import cn.xxs.dao.JiangchengDao;
import cn.xxs.dao.JiatingChengyuanDao;
import cn.xxs.dao.PersonDao;
import cn.xxs.dao.ResumeDao;
import cn.xxs.dao.SequenceDao;
import cn.xxs.dao.XueliMingxiDao;
import cn.xxs.entity.Chengguo;
import cn.xxs.entity.Jiangcheng;
import cn.xxs.entity.JiatingChengyuan;
import cn.xxs.entity.Person;
import cn.xxs.entity.Resume;
import cn.xxs.entity.ResumeInfo;
import cn.xxs.entity.XueliMingxi;

@Service
public class ResumeService {
	@Autowired
	private ChengguoDao chengguoDao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private JiangchengDao jiangchengDao;
	@Autowired
	private XueliMingxiDao xueliMingxiDao;
	@Autowired
	private JiatingChengyuanDao jiatingChengyuanDao;
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private SequenceDao sequenceDao;

	public List<Person> getAllResume(String orgid) {
		return resumeDao.getAllResumePage(orgid);
	}

	public List<Map<String, Object>> selectResumePage(ResumeInfo resumeIfo) {
		return resumeDao.selectResumePage(resumeIfo);
	}

	public void deleteResume(List<String> ids) {
	
		for (String id:ids) {

			resumeDao.deleteResume(id);

		}

	}

	public void insertResumeInfo(Map<String,Object> map) {
		Person person = (Person) map.get("person");
//		Integer pid = sequenceDao.getSequence("person");
//		person.setId(pid);
		personDao.insertPerson(person);

		Resume resume = (Resume) map.get("resume");
//		Integer rid = sequenceDao.getSequence("resume");
//		resume.setId(rid);
//		resume.setPersonid(pid);
		resumeDao.insertResume(resume);

		Chengguo chengguo = (Chengguo) map.get("chengguo");
//		Integer cid = sequenceDao.getSequence("chengguo");
//		chengguo.setId(cid);
//		chengguo.setPersonid(pid);
		chengguoDao.insertChengguo(chengguo);

		Jiangcheng jiangcheng = (Jiangcheng) map.get("jiangcheng");
//		Integer jcid = sequenceDao.getSequence("jiangcheng");
//		jiangcheng.setId(jcid);
//		jiangcheng.setPersonid(pid);
		jiangchengDao.insertJiangcheng(jiangcheng);
		if(map.get("jiatingchengyuan1")!=null){
		JiatingChengyuan jiatingChengyuan1 = (JiatingChengyuan) map.get("jiatingchengyuan1");
		
//		Integer jid1 = sequenceDao.getSequence("jiatingchengyuan");
//		jiatingChengyuan1.setId(jid1);
//		jiatingChengyuan1.setPersonid(pid);
		jiatingChengyuanDao.insertJiatingChengyuan(jiatingChengyuan1);
		}
		if( map.get("jiatingchengyuan2")!=null){
		JiatingChengyuan jiatingChengyuan2 = (JiatingChengyuan) map.get("jiatingchengyuan2");
//		Integer jid2 = sequenceDao.getSequence("jiatingchengyuan");
//		jiatingChengyuan1.setId(jid2);
//		jiatingChengyuan1.setPersonid(pid);
		jiatingChengyuanDao.insertJiatingChengyuan(jiatingChengyuan2);
		}
		if( map.get("jiatingchengyuan3")!=null){
		JiatingChengyuan jiatingChengyuan3 = (JiatingChengyuan) map.get("jiatingchengyuan3");
//		Integer jid3 = sequenceDao.getSequence("jiatingchengyuan");
//		jiatingChengyuan3.setId(jid3);
//		jiatingChengyuan3.setPersonid(pid);
		jiatingChengyuanDao.insertJiatingChengyuan(jiatingChengyuan3);
		}
		if(map.get("jiatingchengyuan4")!=null){
		JiatingChengyuan jiatingChengyuan4 = (JiatingChengyuan) map.get("jiatingchengyuan4");
//		Integer jid4 = sequenceDao.getSequence("jiatingchengyuan");
//		jiatingChengyuan4.setId(jid4);
//		jiatingChengyuan4.setPersonid(pid);
		jiatingChengyuanDao.insertJiatingChengyuan(jiatingChengyuan4);
		}
		XueliMingxi xueliMingxi = (XueliMingxi) map.get("xuelimingxi");
//		Integer xid = sequenceDao.getSequence("xuelimingxi");
//		xueliMingxi.setId(xid);
//		xueliMingxi.setPersonid(pid);
		xueliMingxiDao.insertXueliMingxi(xueliMingxi);

	}

	public void updateResumeInfo(Map<String,Object> map) {
		Person person = (Person) map.get("person");
		personDao.updatePerson(person);

		Resume resume = (Resume) map.get("resume");
		resumeDao.updateResume(resume);

		Chengguo chengguo = (Chengguo) map.get("chengguo");
		chengguoDao.updateChengguo(chengguo);

		Jiangcheng jiangcheng = (Jiangcheng) map.get("jiangcheng");
		jiangchengDao.updateJiangcheng(jiangcheng);

		if(map.get("jiatingchengyuan1")!=null){
			JiatingChengyuan jiatingChengyuan1 = (JiatingChengyuan) map.get("jiatingchengyuan1");
		jiatingChengyuanDao.updateJiatingChengyuan(jiatingChengyuan1);
		}
		if(map.get("jiatingchengyuan2")!=null){
			JiatingChengyuan jiatingChengyuan2 = (JiatingChengyuan) map.get("jiatingchengyuan2");
		jiatingChengyuanDao.updateJiatingChengyuan(jiatingChengyuan2);
		}
		if(map.get("jiatingchengyuan3")!=null){
			JiatingChengyuan jiatingChengyuan3 = (JiatingChengyuan) map.get("jiatingchengyuan3");
		jiatingChengyuanDao.updateJiatingChengyuan(jiatingChengyuan3);
		}
		if(map.get("jiatingchengyuan4")!=null){
			JiatingChengyuan jiatingChengyuan4= (JiatingChengyuan) map.get("jiatingchengyuan4");
		jiatingChengyuanDao.updateJiatingChengyuan(jiatingChengyuan4);
		}
		XueliMingxi xueliMingxi = (XueliMingxi) map.get("xuelimingxi");
		xueliMingxiDao.updateXueliMingxi(xueliMingxi);
	}


	public Map<String, Object> selectResumeInfo(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chengguo", chengguoDao.selectChengguoByPersonid(id));
		map.put("resume", resumeDao.selectResumeByPersonid(id));
		map.put("jiangcheng", jiangchengDao.selectJiangchengByPersonid(id));
		map.put("jiatingChengyuan", jiatingChengyuanDao.selectJiatingChengyuanByPersonid(id));
		map.put("xueliMingxi", xueliMingxiDao.selectXueliMingxiByPersonid(id));
		map.put("person", personDao.selectResumePersonByPersonid(id));
		return map;
	}
}
