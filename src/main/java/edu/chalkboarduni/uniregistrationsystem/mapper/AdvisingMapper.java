package edu.chalkboarduni.uniregistrationsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.chalkboarduni.uniregistrationsystem.model.Advising;

@Mapper
public interface AdvisingMapper {
	public void save(@Param("advising") Advising advising);
	
	public List<Advising> findAdvising(@Param("advising") Advising advising);
	
	public void update(@Param("advising") Advising advising);
	
	public void delete(@Param("id") String id);
}
