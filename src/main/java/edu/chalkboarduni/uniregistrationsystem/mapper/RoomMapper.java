package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Room;

@Mapper
public interface RoomMapper {
	public void save(@Param("room") Room room);
	
	public List<Room> findRoom(@Param("room") Room room);
	
	public void update(@Param("room") Room room);
	
	public void delete(@Param("id") String id);
}
