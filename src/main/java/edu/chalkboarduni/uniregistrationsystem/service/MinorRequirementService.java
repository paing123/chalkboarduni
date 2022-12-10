package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.MinorRequirement;

public interface MinorRequirementService {
	
	void save(MinorRequirement minorRequirement);

	List<MinorRequirement> findMinorRequirement(MinorRequirement minorRequirement);
	
	void delete(MinorRequirement minorRequirement);
}
