package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.StudentMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	private StudentMapper studentMapper;
	
	public void save(Student student) {
		studentMapper.save(student);
	}
	
	public List<Student> findStudent(Student student) {
		List<Student> Students = studentMapper.findStudent(student);
		return Students;
	}
	
	public void delete(Integer id) {
		studentMapper.delete(id);
	}
	
	public void update(Student student) {
		studentMapper.update(student);
	}
}
