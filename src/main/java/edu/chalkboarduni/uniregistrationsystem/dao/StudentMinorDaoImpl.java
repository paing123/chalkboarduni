package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.StudentMinorMapper;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMinor;

@Repository
public class StudentMinorDaoImpl implements StudentMinorDao {
	
	@Autowired
	private StudentMinorMapper studentMinorMapper;
	
	public void save(StudentMinor studentMinor) {
		studentMinorMapper.save(studentMinor);
	}
	
	public List<StudentMinor> findStudentMinor(StudentMinor studentMinor) {
		List<StudentMinor> studentMinors = studentMinorMapper.findStudentMinor(studentMinor);
		return studentMinors;
	}
	
	public void delete(StudentMinor studentMinor) {
		studentMinorMapper.delete(studentMinor);
	}
	
	public void update(StudentMinor studentMinor) {
		studentMinorMapper.update(studentMinor);
	}
}
