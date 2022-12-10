package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.MajorDao;
import edu.chalkboarduni.uniregistrationsystem.model.Major;

@Service
public class MajorServiceImpl implements MajorService {
	
	@Autowired
	private MajorDao majorDao;

	@Override
	public void save(Major major) {
		majorDao.save(major);
	}

	@Override
	public List<Major> findMajor(Major major) {
		return majorDao.findMajor(major);
	}
	
	@Override
	public void delete(String id) {
		majorDao.delete(id);
	}

	@Override
	public void update(Major major) {
		majorDao.update(major);
	}
}
