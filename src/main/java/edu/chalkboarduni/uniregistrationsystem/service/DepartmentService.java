package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Department;

public interface DepartmentService {
	
	void save(Department department);

	List<Department> findDepartment(Department department);
	
	void delete(String id);

	void update(Department department);
}
