package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.FacultyDepartment;

@Mapper
public interface FacultyDepartmentMapper {
	public void save(@Param("facultyDepartment") FacultyDepartment facultyDepartment);
	
	public List<FacultyDepartment> findFacultyDepartment(@Param("facultyDepartment") FacultyDepartment facultyDepartment);
	
	public void update(@Param("facultyDepartment") FacultyDepartment facultyDepartment);
	
	public void delete(@Param("facultyId") Integer facId,@Param("departmentId") String departmentId);
}
