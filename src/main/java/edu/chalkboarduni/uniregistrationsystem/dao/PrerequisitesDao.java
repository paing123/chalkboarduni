package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import edu.chalkboarduni.uniregistrationsystem.model.Prerequisites;

public interface PrerequisitesDao {
	 
	 void save(Prerequisites prerequisites);
	
	 List<Prerequisites> findPrerequisites(Prerequisites prerequisites);
	 
	 void delete(Integer id);
	 
	 void update(Prerequisites prerequisites);
}
