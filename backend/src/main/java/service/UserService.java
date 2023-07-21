package service;

import dto.RegisterRequest;
import dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(RegisterRequest request);
    UserDto getUserById (Long id);
    List<UserDto> getUsers();
    void deleteUserById (Long id);
    UserDto updateUserById (Long id, UserDto userDto);
}


