package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.FacultyDepartmentMapper;
import edu.chalkboarduni.uniregistrationsystem.model.FacultyDepartment;

@Repository
public class FacultyDepartmentDaoImpl implements FacultyDepartmentDao {
	
	@Autowired
	private FacultyDepartmentMapper facultyDepartmentMapper;
	
	public void save(FacultyDepartment facultyDepartment) {
		facultyDepartmentMapper.save(facultyDepartment);
	}
	
	public List<FacultyDepartment> findFacultyDepartment(FacultyDepartment facultyDepartment) {
		List<FacultyDepartment> facultyDepartments = facultyDepartmentMapper.findFacultyDepartment(facultyDepartment);
		return facultyDepartments;
	}
	
	public void delete(Integer facultyId,String departmentId) {
		facultyDepartmentMapper.delete(facultyId,departmentId);
	}
	
	public void update(FacultyDepartment facultyDepartment) {
		facultyDepartmentMapper.update(facultyDepartment);
	}
}
