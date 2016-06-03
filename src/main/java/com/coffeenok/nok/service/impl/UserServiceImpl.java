package com.coffeenok.nok.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coffeenok.nok.dao.UserDao;
import com.coffeenok.nok.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

private Map<String, String> mapAuthManager = new HashMap<String, String>();
	
	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		mapAuthManager.put("carast", "ADMIN");
		mapAuthManager.put("zaragoza", "ADMIN");
		mapAuthManager.put("josue", "ALMACEN");
		mapAuthManager.put("jorge", "TALLER");
		*/
		System.out.println("username: " + username);
		
		List<com.coffeenok.nok.domain.User> listUsers = null;
		
		listUsers = userDao.findAllUser();
		
		for(com.coffeenok.nok.domain.User user : listUsers){
			System.out.println(user.getId());
			System.out.println(user.getName());
			System.out.println(user.getAuthority());
			mapAuthManager.put(user.getName(), user.getAuthority());
		}
		

		System.out.println("mapAuthManager.get('carast')" + mapAuthManager.get("carast"));
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// fetch user from e.g. DB
		if ("client".equals(username)) {
			authorities.add(new SimpleGrantedAuthority("CLIENT"));
			User user = new User(username, "client", true, true, false, false, authorities);
			return user;
		}
		if ("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			User user = new User(username, "admin", true, true, false, false, authorities);
			return user;
		}
		if ("almacen".equals(username)) {
			authorities.add(new SimpleGrantedAuthority("ALMACEN"));
			User user = new User(username, "almacen", true, true, false, false, authorities);
			return user;
		}
				
		if (mapAuthManager.containsKey(username)) {
			authorities.add(new SimpleGrantedAuthority(mapAuthManager.get(username)));
			User user = new User(username, username, true, true, false, false, authorities);
			return user;
		} else {
			return null;
		}
	}

	public List<com.coffeenok.nok.domain.User> findAllUsers() {
		List<com.coffeenok.nok.domain.User> listUsers = null;
		
		listUsers = userDao.findAllUser();
		
		for(com.coffeenok.nok.domain.User user : listUsers){
			System.out.println(user.getId());
			System.out.println(user.getName());
			System.out.println(user.getAuthority());
		}
		
		return listUsers;
	}

}
