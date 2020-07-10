package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.History;
import com.senla.rent.entity.User;

import java.util.List;
import java.util.Set;

public interface UserRepository extends GenericRepository<User, Integer> {

    User findByLogin(String username);

    boolean existUser(String username);

   // Integer getUserId(String username);

    User getUserWithAllInfo(Integer id);
}
