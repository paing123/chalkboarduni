package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.HoldMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Hold;

@Repository
public class HoldDaoImpl implements HoldDao {
	
	@Autowired
	private HoldMapper holdMapper;
	
	public void save(Hold hold) {
		holdMapper.save(hold);
	}
	
	public List<Hold> findHold(Hold hold) {
		List<Hold> holds = holdMapper.findHold(hold);
		return holds;
	}
	
	public void delete(String id) {
		holdMapper.delete(id);
	}
	
	public void update(Hold hold) {
		holdMapper.update(hold);
	}
}
