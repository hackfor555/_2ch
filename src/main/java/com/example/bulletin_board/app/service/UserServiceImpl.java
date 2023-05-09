package com.example.bulletin_board.app.service;

import com.example.bulletin_board.app.entity.Role;
import com.example.bulletin_board.app.entity.User;import com.example.bulletin_board.app.repository.RoleRepository;
import com.example.bulletin_board.app.repository.UserRepository;
import com.example.bulletin_board.app.Dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public void saveUser(UserDto userDto) {
    /*
    check if user already exists
     */
    User userExists = userRepository.findByEmail(userDto.getEmail());
    if (userExists != null) {
      throw new RuntimeException("User already exists!");
    }
    /*
    throw exception if user information is not complete
     */
    if (userDto.getFirstName() == null
        || userDto.getLastName() == null
        || userDto.getEmail() == null
        || userDto.getPassword() == null) {
      throw new RuntimeException("User information is not complete!");
    }

    /*
    Save user to database
     */
    User user = new User();
    user.setName(userDto.getFirstName() + " " + userDto.getLastName());

    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    /*
    encrypt the password using spring security
     */
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Role role = roleRepository.findByName("ROLE_ADMIN");
    if (role == null) {
      role = checkRoleExist();
    }
    user.setRoles(List.of(role));
    userRepository.save(user);
  }

  @Override
  public User findByUserEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<UserDto> findAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
  }

  private UserDto mapToUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setEmail(user.getEmail());
    userDto.setPassword(user.getPassword());
    return userDto;
  }

  private Role checkRoleExist() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");
    roleRepository.save(role);
    return role;
  }
}
