package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.MinorDao;
import edu.chalkboarduni.uniregistrationsystem.model.Minor;

@Service
public class MinorServiceImpl implements MinorService {
	
	@Autowired
	private MinorDao minorDao;

	@Override
	public void save(Minor minor) {
		minorDao.save(minor);
	}

	@Override
	public List<Minor> findMinor(Minor minor) {
		return minorDao.findMinor(minor);
	}
	
	@Override
	public void delete(String id) {
		minorDao.delete(id);
	}

	@Override
	public void update(Minor minor) {
		minorDao.update(minor);
	}
}
