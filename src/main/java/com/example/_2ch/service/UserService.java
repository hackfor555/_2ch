package com.example._2ch.service;

import com.example._2ch.entity.User;
import com.example._2ch.Dto.UserDto;
import java.util.List;

public interface UserService {
  void saveUser(UserDto userDto);

    User findByUserEmail(String email);
    List<UserDto> findAllUsers();
}
