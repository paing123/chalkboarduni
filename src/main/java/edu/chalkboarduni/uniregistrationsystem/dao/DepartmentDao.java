package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Department;

public interface DepartmentDao {
	 
	 void save(Department department);
	
	 List<Department> findDepartment(Department department);
	 
	 void delete(String id);
	 
	 void update(Department department);
}
