package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.FacultyDepartment;


public interface FacultyDepartmentDao {
	 
	 void save(FacultyDepartment facultyDepartment);
	
	 List<FacultyDepartment> findFacultyDepartment(FacultyDepartment facultyDepartment);
	 
	 void delete(Integer facultyId,String departmentId);
	 
	 void update(FacultyDepartment facultyDepartment);
}
