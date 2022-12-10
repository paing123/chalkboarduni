package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.FacultyDepartment;
import edu.chalkboarduni.uniregistrationsystem.model.User;

public interface FacultyDepartmentService {
	
	void save(FacultyDepartment facultyDepartment);

	List<FacultyDepartment> findFacultyDepartment(FacultyDepartment facultyDepartment);
	
	void delete(Integer facultyId,String departmentId);

	void update(FacultyDepartment facultyDepartment);
}
