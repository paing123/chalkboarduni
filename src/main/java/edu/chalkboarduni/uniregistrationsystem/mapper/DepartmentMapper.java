package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Department;

@Mapper
public interface DepartmentMapper {
	public void save(@Param("department") Department department);
	
	public List<Department> findDepartment(@Param("department") Department department);
	
	public void update(@Param("department") Department department);
	
	public void delete(@Param("id") String id);
}
