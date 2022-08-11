package com.training.eshop.service.impl;

import com.training.eshop.model.User;
import com.training.eshop.service.UserService;
import com.training.eshop.converter.UserConverter;
import com.training.eshop.dao.UserDAO;
import com.training.eshop.dto.UserDto;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());

    private static final String FIELD_IS_EMPTY = "Login or password shouldn't be empty";
    private static final String INVALID_FIELD = "Login or password shouldn't be less than 4 symbols";
    private static final String USER_IS_PRESENT = "User with login {} is already present";

    private final UserDAO userDAO;
    private final UserConverter userConverter;

    public UserServiceImpl(UserDAO userDAO, UserConverter userConverter) {
        this.userDAO = userDAO;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public void save(UserDto userDto) {
        User user = userConverter.fromUserDto(userDto);

        userDAO.save(user);

        LOGGER.info("New user : {}", user);
    }

    @Override
    @Transactional
    public User getByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    @Transactional
    public boolean isInvalidUser(UserDto userDto) {
        return userDto.getLogin().length() < 4 || userDto.getPassword().length() < 4 || isUserPresent(userDto);
    }

    @Override
    @Transactional
    public String invalidUser(UserDto userDto) {
        String login = userDto.getLogin();
        String password = userDto.getPassword();

        if (login.isEmpty() || password.isEmpty()) {
            LOGGER.error(FIELD_IS_EMPTY);

            return FIELD_IS_EMPTY;
        }

        if (isUserPresent(userDto)) {
            LOGGER.error(USER_IS_PRESENT, login);

            return String.format(USER_IS_PRESENT.replace("{}", "%s"), login);
        }

        LOGGER.error(INVALID_FIELD);

        return INVALID_FIELD;
    }

    @Transactional
    private boolean isUserPresent(UserDto userDto) {
        String login = userDto.getLogin();

        return userDAO.getAll().stream().anyMatch(user -> login.equals(user.getLogin()));
    }
}
