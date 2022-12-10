package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.FacultyHistory;

@Mapper
public interface FacultyHistoryMapper {
	public void save(@Param("facultyHistory") FacultyHistory facultyHistory);
	
	public List<FacultyHistory> findFacultyHistory(@Param("facultyHistory") FacultyHistory facultyHistory);
	
	public void update(@Param("facultyHistory") FacultyHistory FacultyHistory);
	
	public void delete(@Param("facId") Integer facId,@Param("courseSectionId") Integer courseSectionId);
}
