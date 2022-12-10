package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.DepartmentDao;
import edu.chalkboarduni.uniregistrationsystem.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	public List<Department> findDepartment(Department department) {
		return departmentDao.findDepartment(department);
	}
	
	@Override
	public void delete(String id) {
		departmentDao.delete(id);
	}

	@Override
	public void update(Department department) {
		departmentDao.update(department);
	}
}
