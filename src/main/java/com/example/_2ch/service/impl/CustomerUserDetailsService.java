package com.example._2ch.service.impl;

import com.example._2ch.entity.Role;
import com.example._2ch.entity.User;import com.example._2ch.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomerUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*
   This method is named loadUserByUsername for compatibility with the UserDetailsService
   interface,
   but it actually takes an email as its argument.
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);

    if (user != null) {
      return new org.springframework.security.core.userdetails.User(
          user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    } else {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    Collection<? extends GrantedAuthority> mapRoles =
        roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    return mapRoles;
  }
}
