package edu.chalkboarduni.uniregistrationsystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.chalkboarduni.uniregistrationsystem.dao.LoginDao;
import edu.chalkboarduni.uniregistrationsystem.dao.UserDao;
import edu.chalkboarduni.uniregistrationsystem.dto.UserDto;
import edu.chalkboarduni.uniregistrationsystem.model.Login;
import edu.chalkboarduni.uniregistrationsystem.model.User;
import edu.chalkboarduni.uniregistrationsystem.util.DateTimeFormatter;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginDao loginDao;

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void save(UserDto user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.save(user);
	}

	@Override
	public List<UserDto> findUser(UserDto user) {
		return userDao.findUser(user);
	}
	
	@Override
	public UserDto findUserByEmail(String email) {
		Login login = new Login();
		login.setEmail(email);
		login = loginDao.findLogin(login).get(0);
		UserDto user = new UserDto();
		user.setEmail(email);
		user.setUserId(login.getUserId());
		user = userDao.findUser(user).get(0);
		return user;
	}
	
	@Transactional
	@Override
	public void delete(String id) {
		userDao.delete(id);
	}

	@Transactional
	@Override
	public void update(UserDto user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.update(user);
	}
	
	@Override
	public void updateStudentOrFacultyUser(UserDto user) {
		userDao.updateStudentOrFacultyUser(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {		
		UserDto user = findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    String role = user.getUserType();
	    grantedAuthorities.add(new SimpleGrantedAuthority(role));
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}
}
