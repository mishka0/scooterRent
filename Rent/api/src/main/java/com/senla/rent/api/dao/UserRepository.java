package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.User;

public interface UserRepository extends GenericRepository<User, Integer> {

    User findByLogin(String username);

    boolean existUser(String username);

    User getUserWithAllInfo(Integer id);
}
