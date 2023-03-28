package com.example._2ch.service.impl;import org.junit.jupiter.api.AfterEach;import org.junit.jupiter.api.BeforeEach;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
import com.example._2ch.Dto.UserDto;
import com.example._2ch.entity.Role;
import com.example._2ch.entity.User;
import com.example._2ch.repository.RoleRepository;
import com.example._2ch.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = UserServiceImpl.class)
public class UserServiceImplTest {
  @InjectMocks private UserServiceImpl userService;

  @Mock private UserRepository userRepository;

  @Mock private RoleRepository roleRepository;

  @Mock private PasswordEncoder passwordEncoder;

  private UserDto userDto;

  @BeforeEach
  void setUp() {
    userDto = new UserDto();
    userDto.setFirstName("John");
    userDto.setLastName("Doe");
    userDto.setEmail("john.doe@example.com");
    userDto.setPassword("password");
    //  }@AfterEach
    //  void tearDown() {
    //
    //  }
  }

  @Test
  void saveUser() {
    when(userRepository.findByEmail(userDto.getEmail())).thenReturn(null);
    //    when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(new Role("ROLE_ADMIN"));
    when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");

    userService.saveUser(userDto);

    verify(userRepository, times(1)).findByEmail(userDto.getEmail());
    verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
    verify(passwordEncoder, times(1)).encode(userDto.getPassword());
    verify(userRepository, times(1)).save(any(User.class));
  }
}

//      }@Test
//      void findByUserEmail() {
//        }@Test
//        void findAllUsers() {
//          }}