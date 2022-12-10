package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.HoldDao;
import edu.chalkboarduni.uniregistrationsystem.model.Hold;

@Service
public class HoldServiceImpl implements HoldService {
	
	@Autowired
	private HoldDao holdDao;

	@Override
	public void save(Hold hold) {
		holdDao.save(hold);
	}

	@Override
	public List<Hold> findHold(Hold hold) {
		return holdDao.findHold(hold);
	}
	
	@Override
	public void delete(String id) {
		holdDao.delete(id);
	}

	@Override
	public void update(Hold hold) {
		holdDao.update(hold);
	}
}
