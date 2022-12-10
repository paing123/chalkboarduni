package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.StudentHistory;

@Mapper
public interface StudentHistoryMapper {
	public void save(@Param("studentHistory") StudentHistory studentHistory);
	
	public List<StudentHistory> findStudentHistory(@Param("studentHistory") StudentHistory studentHistory);
	
	public void update(@Param("studentHistory") StudentHistory studentHistory);
	
	public void delete(@Param("studentId") Integer studentId,@Param("courseSectionId") Integer courseSectionId);
}
