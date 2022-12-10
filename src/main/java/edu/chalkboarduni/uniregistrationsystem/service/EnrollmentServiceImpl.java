package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.EnrollmentDao;
import edu.chalkboarduni.uniregistrationsystem.model.Enrollment;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	private EnrollmentDao enrollmentDao;

	@Override
	public void save(Enrollment enrollment) {
		enrollmentDao.save(enrollment);
	}

	@Override
	public List<Enrollment> findEnrollment(Enrollment enrollment) {
		return enrollmentDao.findEnrollment(enrollment);
	}
	
	@Override
	public void delete(Enrollment enrollment) {
		enrollmentDao.delete(enrollment);
	}

	@Override
	public void update(Enrollment enrollment) {
		enrollmentDao.update(enrollment);
	}
}
