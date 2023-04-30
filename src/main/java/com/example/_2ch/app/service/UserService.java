package com.example._2ch.app.service;

import com.example._2ch.app.entity.User;
import com.example._2ch.app.Dto.UserDto;
import java.util.List;

public interface UserService {
  void saveUser(UserDto userDto);

    User findByUserEmail(String email);
    List<UserDto> findAllUsers();
}
