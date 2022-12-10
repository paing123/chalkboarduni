package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.StudentMajorDao;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMajor;

@Service
public class StudentMajorServiceImpl implements StudentMajorService {
	
	@Autowired
	private StudentMajorDao studentMajorDao;

	@Override
	public void save(StudentMajor studentMajor) {
		studentMajorDao.save(studentMajor);
	}

	@Override
	public List<StudentMajor> findStudentMajor(StudentMajor studentMajor) {
		return studentMajorDao.findStudentMajor(studentMajor);
	}
	
	@Override
	public void delete(StudentMajor studentMajor) {
		studentMajorDao.delete(studentMajor);
	}

	@Override
	public void update(StudentMajor studentMajor) {
		studentMajorDao.update(studentMajor);
	}
}
