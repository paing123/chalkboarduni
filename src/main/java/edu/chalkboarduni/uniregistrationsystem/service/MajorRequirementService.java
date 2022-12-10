package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.MajorRequirement;

public interface MajorRequirementService {
	
	void save(MajorRequirement majorRequirement);

	List<MajorRequirement> findMajorRequirement(MajorRequirement majorRequirement);
	
	void delete(MajorRequirement majorRequirement);
}
