package com.training.eshop.converter.impl;

import com.training.eshop.converter.UserConverter;
import com.training.eshop.model.User;
import com.training.eshop.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {

    private final PasswordEncoder passwordEncoder;

    public UserConverterImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User fromUserDto(UserDto userDto) {
        User user = new User();

        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;
    }
}
