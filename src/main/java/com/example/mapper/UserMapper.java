package com.example.mapper;

import com.example.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAllUser();

    User selectUserById(int id);

    int insertUser(User user);

    int deleteUser(int id);
}
