package edu.chalkboarduni.uniregistrationsystem.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.MajorRequirementDao;
import edu.chalkboarduni.uniregistrationsystem.model.MajorRequirement;

@Service
public class MajorRequirementServiceImpl implements MajorRequirementService {
	
	@Autowired
	private MajorRequirementDao majorRequirementDao;

	@Override
	public void save(MajorRequirement majorRequirement) {
		majorRequirementDao.save(majorRequirement);
	}

	@Override
	public List<MajorRequirement> findMajorRequirement(MajorRequirement majorRequirement) {
		return majorRequirementDao.findMajorRequirement(majorRequirement);
	}
	
	@Override
	public void delete(MajorRequirement majorRequirement) {
		majorRequirementDao.delete(majorRequirement);
	}
}
