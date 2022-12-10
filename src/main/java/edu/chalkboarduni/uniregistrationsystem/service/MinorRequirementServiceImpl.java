package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.MinorRequirementDao;
import edu.chalkboarduni.uniregistrationsystem.model.MinorRequirement;

@Service
public class MinorRequirementServiceImpl implements MinorRequirementService {
	
	@Autowired
	private MinorRequirementDao minorRequirementDao;

	@Override
	public void save(MinorRequirement minorRequirement) {
		minorRequirementDao.save(minorRequirement);
	}

	@Override
	public List<MinorRequirement> findMinorRequirement(MinorRequirement minorRequirement) {
		return minorRequirementDao.findMinorRequirement(minorRequirement);
	}
	
	@Override
	public void delete(MinorRequirement minorRequirement) {
		minorRequirementDao.delete(minorRequirement);
	}
}
