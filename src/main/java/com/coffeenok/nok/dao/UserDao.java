package com.coffeenok.nok.dao;

import java.util.List;

import com.coffeenok.nok.domain.User;

public interface UserDao {

	public List<User> findAllUser();
}
