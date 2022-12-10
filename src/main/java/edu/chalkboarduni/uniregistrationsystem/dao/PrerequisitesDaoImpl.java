package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.chalkboarduni.uniregistrationsystem.mapper.PrerequisitesMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Prerequisites;

@Repository
public class PrerequisitesDaoImpl implements PrerequisitesDao {
	
	@Autowired
	private PrerequisitesMapper prerequisitesMapper;
	
	public void save(Prerequisites prerequisites) {
		prerequisitesMapper.save(prerequisites);
	}
	
	public List<Prerequisites> findPrerequisites(Prerequisites prerequisites) {
		List<Prerequisites> prerequisitesList = prerequisitesMapper.findPrerequisites(prerequisites);
		return prerequisitesList;
	}
	
	public void delete(Integer id) {
		prerequisitesMapper.delete(id);
	}
	
	public void update(Prerequisites prerequisites) {
		prerequisitesMapper.update(prerequisites);
	}
}
