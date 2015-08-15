package cn.xxs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xxs.entity.Person;
import cn.xxs.entity.Resume;
import cn.xxs.entity.ResumeInfo;

public interface ResumeDao {
	public List<Person> getAllResumePage(String orgid);
	public List<Map<String, Object>> selectResumePage(ResumeInfo resumeIfo);
	public void deleteResume(@Param(value = "id") String id );
	public void insertResume(Resume resume);
	public void updateResume(Resume resume);
	public Resume selectResumeByPersonid(int id);
}
