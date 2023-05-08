package com.example.bulletin_board.app.service;

import com.example.bulletin_board.app.entity.User;
import com.example.bulletin_board.app.Dto.UserDto;

import java.util.List;

public interface UserService {
  void saveUser(UserDto userDto);

    User findByUserEmail(String email);
    List<UserDto> findAllUsers();
}
