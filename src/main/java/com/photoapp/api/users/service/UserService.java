package com.photoapp.api.users.service;

import com.photoapp.api.users.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService extends UserDetailsService {
    public UserDto createUser(UserDto userDto);

    public UserDto getUserDetailsByEmail(String email);

    public UserDetails loadUserByUsername(String email);
}
