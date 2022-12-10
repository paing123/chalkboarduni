package edu.chalkboarduni.uniregistrationsystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.chalkboarduni.uniregistrationsystem.mapper.RoomMapper;
import edu.chalkboarduni.uniregistrationsystem.model.Room;

@Repository
public class RoomDaoImpl implements RoomDao {
	
	@Autowired
	private RoomMapper roomMapper;
	
	public void save(Room room) {
		roomMapper.save(room);
	}
	
	public List<Room> findRoom(Room room) {
		List<Room> rooms = roomMapper.findRoom(room);
		return rooms;
	}
	
	public void delete(String id) {
		roomMapper.delete(id);
	}
	
	public void update(Room room) {
		roomMapper.update(room);
	}
}
