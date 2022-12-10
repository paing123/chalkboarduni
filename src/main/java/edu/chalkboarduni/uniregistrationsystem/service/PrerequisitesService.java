package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Prerequisites;

public interface PrerequisitesService {
	
	void save(Prerequisites prerequisites);

	List<Prerequisites> findPrerequisites(Prerequisites prerequisites);
	
	void delete(Integer id);

	void update(Prerequisites prerequisites);
}
