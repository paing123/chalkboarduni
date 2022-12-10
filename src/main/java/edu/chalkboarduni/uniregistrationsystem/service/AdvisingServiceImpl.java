package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.AdvisingDao;
import edu.chalkboarduni.uniregistrationsystem.model.Advising;

@Service
public class AdvisingServiceImpl implements AdvisingService {
	
	@Autowired
	private AdvisingDao advisingDao;

	@Override
	public void save(Advising advising) {
		advisingDao.save(advising);
	}

	@Override
	public List<Advising> findAdvising(Advising advising) {
		return advisingDao.findAdvising(advising);
	}
	
	@Override
	public void delete(String id) {
		advisingDao.delete(id);
	}

	@Override
	public void update(Advising advising) {
		advisingDao.update(advising);
	}
}
