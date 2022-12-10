package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.DepartmentMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public void save(Department department) {
		departmentMapper.save(department);
	}
	
	public List<Department> findDepartment(Department department) {
		List<Department> departments = departmentMapper.findDepartment(department);
		return departments;
	}
	
	public void delete(String id) {
		departmentMapper.delete(id);
	}
	
	public void update(Department department) {
		departmentMapper.update(department);
	}
}
