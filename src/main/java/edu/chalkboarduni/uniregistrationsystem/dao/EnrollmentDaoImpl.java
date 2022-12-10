package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.EnrollmentMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;

@Repository
public class EnrollmentDaoImpl implements EnrollmentDao {
	
	@Autowired
	private EnrollmentMapper enrollmentMapper;
	
	public void save(Enrollment enrollment) {
		enrollmentMapper.save(enrollment);
	}
	
	public List<Enrollment> findEnrollment(Enrollment enrollment) {
		List<Enrollment> enrollments = enrollmentMapper.findEnrollment(enrollment);
		return enrollments;
	}
	
	public void delete(Enrollment enrollment) {
		enrollmentMapper.delete(enrollment);
	}
	
	public void update(Enrollment enrollment) {
		enrollmentMapper.update(enrollment);
	}
}
