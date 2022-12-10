package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.StudentMinorDao;
import edu.chalkboarduni.uniregistrationsystem.model.StudentMinor;

@Service
public class StudentMinorServiceImpl implements StudentMinorService {
	
	@Autowired
	private StudentMinorDao studentMinorDao;

	@Override
	public void save(StudentMinor studentMinor) {
		studentMinorDao.save(studentMinor);
	}

	@Override
	public List<StudentMinor> findStudentMinor(StudentMinor studentMinor) {
		return studentMinorDao.findStudentMinor(studentMinor);
	}
	
	@Override
	public void delete(StudentMinor studentMinor) {
		studentMinorDao.delete(studentMinor);
	}

	@Override
	public void update(StudentMinor studentMinor) {
		studentMinorDao.update(studentMinor);
	}
}
