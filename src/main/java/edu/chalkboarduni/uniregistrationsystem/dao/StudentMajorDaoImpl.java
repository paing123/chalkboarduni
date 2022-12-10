package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.StudentMajorMapper;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMajor;

@Repository
public class StudentMajorDaoImpl implements StudentMajorDao {
	
	@Autowired
	private StudentMajorMapper studentMajorMapper;
	
	public void save(StudentMajor studentMajor) {
		studentMajorMapper.save(studentMajor);
	}
	
	public List<StudentMajor> findStudentMajor(StudentMajor studentMajor) {
		List<StudentMajor> studentMajors = studentMajorMapper.findStudentMajor(studentMajor);
		return studentMajors;
	}
	
	public void delete(StudentMajor studentMajor) {
		studentMajorMapper.delete(studentMajor);
	}
	
	public void update(StudentMajor studentMajor) {
		studentMajorMapper.update(studentMajor);
	}
}
