package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.StudentHoldMapper;
import edu.chalkboarduni.uniregistrationsystem.model.StudentHold;

@Repository
public class StudentHoldDaoImpl implements StudentHoldDao {
	
	@Autowired
	private StudentHoldMapper studentHoldMapper;
	
	public void save(StudentHold studentHold) {
		studentHoldMapper.save(studentHold);
	}
	
	public List<StudentHold> findStudentHold(StudentHold studentHold) {
		List<StudentHold> studentHolds = studentHoldMapper.findStudentHold(studentHold);
		return studentHolds;
	}
	
	public void delete(StudentHold studentHold) {
		studentHoldMapper.delete(studentHold);
	}
	
}
