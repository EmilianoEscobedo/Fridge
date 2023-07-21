package service;

import dto.RegisterRequest;
import dto.UserDto;

public interface UserService {
    UserDto createUser(RegisterRequest request);
    UserDto getUserById (Long id);
    void deleteUserById (Long id);
    UserDto updateUserById (Long id, UserDto userDto);
}


