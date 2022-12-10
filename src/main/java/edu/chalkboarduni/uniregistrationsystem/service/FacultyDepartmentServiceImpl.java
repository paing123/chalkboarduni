package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.FacultyDepartmentDao;
import edu.chalkboarduni.uniregistrationsystem.dao.UserDao;
import edu.chalkboarduni.uniregistrationsystem.model.FacultyDepartment;
import edu.chalkboarduni.uniregistrationsystem.model.User;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;

@Service
public class FacultyDepartmentServiceImpl implements FacultyDepartmentService {
	
	@Autowired
	private FacultyDepartmentDao facultyDepartmentDao;

	@Override
	public void save(FacultyDepartment facultyDepartment) {
		facultyDepartmentDao.save(facultyDepartment);
	}

	@Override
	public List<FacultyDepartment> findFacultyDepartment(FacultyDepartment facultyDepartment) {
		return facultyDepartmentDao.findFacultyDepartment(facultyDepartment);
	}
	
	@Override
	public void delete(Integer facultyId,String departmentId) {
		facultyDepartmentDao.delete(facultyId,departmentId);
	}

	@Override
	public void update(FacultyDepartment facultyDepartment) {
		facultyDepartmentDao.update(facultyDepartment);
	}	
}
