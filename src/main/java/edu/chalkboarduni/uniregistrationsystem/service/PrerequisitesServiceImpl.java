package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.PrerequisitesDao;
import edu.chalkboarduni.uniregistrationsystem.model.Prerequisites;

@Service
public class PrerequisitesServiceImpl implements PrerequisitesService {
	
	@Autowired
	private PrerequisitesDao prerequisitesDao;

	@Override
	public void save(Prerequisites prerequisites) {
		prerequisitesDao.save(prerequisites);
	}

	@Override
	public List<Prerequisites> findPrerequisites(Prerequisites prerequisites) {
		return prerequisitesDao.findPrerequisites(prerequisites);
	}
	
	@Override
	public void delete(Integer id) {
		prerequisitesDao.delete(id);
	}

	@Override
	public void update(Prerequisites Prerequisites) {
		prerequisitesDao.update(Prerequisites);
	}
}
