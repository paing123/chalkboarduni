package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.chalkboarduni.uniregistrationsystem.dao.RoomDao;
import edu.chalkboarduni.uniregistrationsystem.model.Room;

@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private RoomDao roomDao;

	@Override
	public void save(Room room) {
		roomDao.save(room);
	}

	@Override
	public List<Room> findRoom(Room room) {
		return roomDao.findRoom(room);
	}
	
	@Override
	public void delete(String id) {
		roomDao.delete(id);
	}

	@Override
	public void update(Room room) {
		roomDao.update(room);
	}
}
